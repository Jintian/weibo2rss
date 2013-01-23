package com.dengjintian.weibo2rss.misc;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;

import com.dengjintian.weibo2rss.weibo4j.Timeline;
import com.dengjintian.weibo2rss.weibo4j.Users;
import com.dengjintian.weibo2rss.weibo4j.model.Paging;
import com.dengjintian.weibo2rss.weibo4j.model.Status;
import com.dengjintian.weibo2rss.weibo4j.model.User;
import com.dengjintian.weibo2rss.weibo4j.model.WeiboException;
import com.dengjintian.weibo2rss.weibo4j.org.json.JSONObject;

/**
 * User: jintian, Date: 8/1/13
 */
public class RedisService {

    private static final Logger   logger          = Logger.getLogger(RedisService.class);
    private static final Timeline timeline        = new Timeline();
    private static final Users    users           = new Users();
    private static int            statusesPerPage = 50;
    @Autowired
    RedisDAO                      redisDAO;

    private List<Status> fetchAllStatuses(String userId) {
        return fetchNewStatuses(userId, "");
    }

    private List<Status> fetchNewStatuses(String userId, String newestStatusIdInCache) {
        Paging paging = new Paging();
        paging.setCount(statusesPerPage);

        if (!StringUtils.isBlank(newestStatusIdInCache) && StringUtils.isNumeric(newestStatusIdInCache)) {
            paging.setSinceId(Long.valueOf(newestStatusIdInCache));
        }

        List<Status> result = new ArrayList<Status>();

        for (int i = 1; i <= 4; i++) {
            try {
                paging.setPage(i);
                List<Status> currentResult = timeline.getUserTimelineByUid(userId, paging, 0, 0).getStatuses();
                if (CollectionUtils.isEmpty(currentResult)) break;
                result.addAll(filter(currentResult, newestStatusIdInCache));
            } catch (WeiboException e) {
                logger.error("Fail to get statuses for userId(" + userId + ")!", e);
            }
        }

        return result;
    }

    private List<Status> filter(List<Status> currentResult, String newestStatusIdInCache) {
        List<Status> result = new ArrayList<Status>();
        for (Status status : currentResult) {
            if (StringUtils.isBlank(newestStatusIdInCache) || !newestStatusIdInCache.equals(status.getId())) result.add(status);
            else continue;
        }
        return result;
    }

    public void checkAndUpdateCache(String userId) {
        String newestStatusIdInCache = getNewestStatusIdFromCache(userId);
        List<Status> statuses = new ArrayList<Status>(200);
        if (StringUtils.isBlank(newestStatusIdInCache)) {
            statuses = fetchAllStatuses(userId);
        } else if (!newestStatusIdInCache.equals(getNewestStatusIdFromWeibo(userId))) {
            statuses = fetchNewStatuses(userId, newestStatusIdInCache);
        }
        if (!CollectionUtils.isEmpty(statuses)) {
            redisDAO.updateUserStatuses(userId, statuses);
            return;
        }
    }

    private String getNewestStatusIdFromCache(String userId) {
        List<Status> result = redisDAO.getNewestUserStatusByUserId(userId);
        if (CollectionUtils.isEmpty(result)) return "";
        else return result.get(0).getId();
    }

    private String getNewestStatusIdFromWeibo(String userId) {
        try {
            JSONObject resultObject = timeline.getUserTimelineIdsByUid(userId, "0");
            return (String) resultObject.getJSONArray("statuses").get(0);
        } catch (Exception e) {
            logger.error("Fail to get timeline ids for user(" + userId + ")!", e);
        }
        return "";
    }

    public List<Status> getStatuses(String userId) {
        return redisDAO.getUserStatusesByUserId(userId);
    }

    public User getUser(String userId) {
        try {
            return users.showUserById(userId);
        } catch (Exception e) {
            logger.error("Fail to get timeline ids for user(" + userId + ")!", e);
        }
        return null;
    }

    public void setAccessToken(String accessToken) {
        timeline.client.setToken(accessToken);
        users.setToken(accessToken);
    }

}
