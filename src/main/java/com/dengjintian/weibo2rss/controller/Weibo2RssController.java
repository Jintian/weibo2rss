package com.dengjintian.weibo2rss.controller;

import java.io.IOException;
import java.util.Date;
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

import com.dengjintian.weibo2rss.misc.RedisService;
import com.dengjintian.weibo2rss.weibo4j.Oauth;
import com.dengjintian.weibo2rss.weibo4j.http.AccessToken;
import com.dengjintian.weibo2rss.weibo4j.model.WeiboException;

/**
 * User: jintian, Date: 7/1/13
 */
@Controller
public class Weibo2RssController {

    private static final Logger logger       = LoggerFactory.getLogger(Weibo2RssController.class);
    private static Oauth        oauth        = new Oauth();
    @Autowired
    RedisService                redisService;
    private String              ACCESS_TOKEN = null;

    @RequestMapping(value = { "/index", "/" })
    public ModelAndView index() {
        ModelAndView modelAndView = new ModelAndView("index");
        modelAndView.addObject("tmp", new Date());
        return modelAndView;
    }

    @RequestMapping(value = "/oauth", method = RequestMethod.POST)
    public String oauth() throws WeiboException {
        return "redirect:" + oauth.authorize("code", "weibo2rss", "friendships_groups_read,statuses_to_me_read");
    }

    @RequestMapping(value = "/generate/{userId}", method = RequestMethod.GET)
    public ModelAndView generateRss(@PathVariable("userId")
    String userId, HttpServletResponse response) throws IOException {
        if (StringUtils.isBlank(ACCESS_TOKEN)) {
            ModelAndView modelAndView = new ModelAndView("redirect:/");
            return modelAndView;
        }

        response.setContentType("application/rss+xml;charset=UTF-8");
        ModelAndView modelAndView = new ModelAndView("generate");

        redisService.checkAndUpdateCache(userId);
        modelAndView.addObject("statusList", redisService.getStatuses(userId));
        modelAndView.addObject("user", redisService.getUser(userId));
        return modelAndView;

    }

    @RequestMapping(value = "/callback")
    public ModelAndView callback(@RequestParam("code")
    String code) {

        ModelAndView modelAndView = new ModelAndView();
        try {
            AccessToken accessTokens = oauth.getAccessTokenByCode(code);
            ACCESS_TOKEN = accessTokens.getAccessToken();
            redisService.setAccessToken(ACCESS_TOKEN);
            modelAndView.addObject("result", "success!");
        } catch (WeiboException e) {
            logger.error("Fail to retrieve accessToken with code(" + code + ")!", e);
            modelAndView.addObject("result", "fail!");
        }
        return modelAndView;
    };
}
