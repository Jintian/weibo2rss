package com.dengjintian.weibo2rss.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.dengjintian.weibo2rss.misc.RedisService;
import com.dengjintian.weibo2rss.weibo4j.Oauth;
import com.dengjintian.weibo2rss.weibo4j.http.AccessToken;
import com.dengjintian.weibo2rss.weibo4j.model.WeiboException;

/**
 * User: jintian, Date: 7/1/13
 */
@Controller
public class Weibo2RssController {

    private static final Logger logger = LoggerFactory.getLogger(Weibo2RssController.class);
    private static Oauth        oauth  = new Oauth();
    @Autowired
    RedisService                redisService;

    /**
     * 欢迎页面.
     * 
     * @return
     */
    @RequestMapping(value = { "/index", "/" })
    public ModelAndView index() {
        ModelAndView modelAndView = new ModelAndView("index");
        modelAndView.addObject("userCount", redisService.getUserCount());
        if (StringUtils.isBlank(redisService.getAccessToken())) modelAndView.addObject("needOauth", true);
        return modelAndView;
    }

    /**
     * 重导向到weibo进行oauth认证.
     * 
     * @return
     * @throws WeiboException
     */
    @RequestMapping(value = "/oauth", method = RequestMethod.GET)
    public String oauth() throws WeiboException {
        return "redirect:" + oauth.authorize("code", "weibo2rss", "friendships_groups_read,statuses_to_me_read");
    }

    /**
     * 生成rss.
     * 
     * @param userId
     * @param response
     * @param request
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "/rss/{userId}", method = RequestMethod.GET)
    public ModelAndView generateRss(@PathVariable("userId")
    String userId, HttpServletResponse response, HttpServletRequest request) throws IOException {
        if (StringUtils.isBlank(redisService.getAccessToken())) {
            // 没有通过oauth认证，跳回首页.
            ModelAndView modelAndView = new ModelAndView("redirect:/");
            request.getSession().setAttribute("originalUserId", userId);
            return modelAndView;
        }

        response.setContentType("application/rss+xml;charset=UTF-8");
        ModelAndView modelAndView = new ModelAndView("rss");

        // 获取用户最新的微博
        if (StringUtils.isNotBlank(userId)) {
            if (!StringUtils.isNumeric(userId)) {
                // 是domain id，进行转换
                userId = redisService.getUserIdFromDomain(userId);
                if(StringUtils.isBlank(userId)) return modelAndView;
            }
            redisService.checkAndUpdateCache(userId);

            modelAndView.addObject("statusList", redisService.getStatuses(userId));
            modelAndView.addObject("user", redisService.getUser(userId));
        }
        return modelAndView;

    }

    /**
     * 对首页的表单提交请求进行内部跳转.
     * 
     * @param userId
     * @return
     */
    @RequestMapping(value = "/generate")
    public ModelAndView formHandling(@RequestParam("userId")
    String userId) {
        return new ModelAndView(new RedirectView("/rss/" + userId));
    }

    /**
     * 提供给weibo oauth认证后回调.
     * 
     * @param code
     * @param request
     * @return
     */
    @RequestMapping(value = "/callback")
    public ModelAndView callback(@RequestParam("code")
    String code, HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView();
        try {
            // 获取access token，保存到redis里面
            AccessToken accessTokens = oauth.getAccessTokenByCode(code);
            logger.warn("Get new accessTokens which will be expired in " + accessTokens.getExpireIn());
            redisService.saveAccessToken(accessTokens.getAccessToken());

            // 判断是否需要跳转
            String userId = (String) request.getSession().getAttribute("originalUserId");
            if (!StringUtils.isBlank(userId)) modelAndView.setView(new RedirectView("rss/" + userId));
            else modelAndView.addObject("result", "success!");
        } catch (WeiboException e) {
            logger.error("Fail to retrieve accessToken with code(" + code + ")!", e);
            modelAndView.addObject("result", "fail!" + e);
        }
        return modelAndView;
    }

}
