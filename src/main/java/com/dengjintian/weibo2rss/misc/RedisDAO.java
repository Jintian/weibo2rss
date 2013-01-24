package com.dengjintian.weibo2rss.misc;

import java.util.Collections;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

import com.dengjintian.weibo2rss.weibo4j.model.Status;

/**
 * User: jintian, Date: 8/1/13
 */
public class RedisDAO {

    private static final Logger   logger = LoggerFactory.getLogger(RedisDAO.class);
    @Autowired
    RedisTemplate<String, Object> redisTemplate;

    public List<Object> getUserStatusesByUserId(String userId) {
        logger.warn(userId + " has " + redisTemplate.opsForList().size(userId) + " statuses!");
        return redisTemplate.opsForList().range(userId, 0, -1);
    }

    public List<Object> getNewestUserStatusByUserId(String userId) {
        return redisTemplate.opsForList().range(userId, 0, 0);
    }

    public void updateUserStatuses(String userId, List<Status> statuses) {
        if (statuses == null || statuses.isEmpty()) return;
        // in reverse order
        Collections.reverse(statuses);
        for (Status status : statuses) {
            updateUserStatus(userId, status);
        }

        // remove outdated
        long removeCounter = redisTemplate.opsForList().size(userId) - 200;
        if (removeCounter > 0) {
            for (int i = 0; i <= removeCounter; i++) {
            }
            redisTemplate.opsForList().rightPop(userId);
        }
    }

    public void updateUserStatus(String userId, Status status) {
        redisTemplate.opsForList().leftPush(userId, status);
    }

    public int getUserCount() {
        return redisTemplate.keys("*").size();
    }

    public void saveAccessToken(String accessToken) {
        redisTemplate.opsForValue().set("accessToken", accessToken);
    }

    public String getAccessToken() {
        return (String) redisTemplate.opsForValue().get("accessToken");
    }

}
