/*
 * Copyright 1999-2004 Alibaba.com All right reserved. This software is the confidential and proprietary information of
 * Alibaba.com ("Confidential Information"). You shall not disclose such Confidential Information and shall use it only
 * in accordance with the terms of the license agreement you entered into with Alibaba.com.
 */
package com.dengjintian.weibo2rss.misc;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.SerializationException;

import com.alibaba.fastjson.JSON;
import com.dengjintian.weibo2rss.weibo4j.model.Status;

/**
 * 类StatusRedisSerializer的描述：
 * 
 * @author: jintian, Date: 13/1/13
 */
public class StatusRedisSerializer implements RedisSerializer<Status> {

    private static final Logger logger = LoggerFactory.getLogger(StatusRedisSerializer.class);

    @Override
    public byte[] serialize(Status status) throws SerializationException {
        if (status != null) {
            try {
                return JSON.toJSONString(status).getBytes("UTF-8");
            } catch (Exception e) {
                logger.error("Fail to convert status(" + ToStringBuilder.reflectionToString(status) + ") to byte[]!", e);
            }
        }
        return null;
    }

    @Override
    public Status deserialize(byte[] bytes) throws SerializationException {
        if (bytes != null) {
            String tmp = "";
            try {
                tmp = new String(bytes, "UTF-8");
                return (Status) JSON.parseObject(tmp, Status.class);
            } catch (Exception e) {
                logger.error("Fail to convert json string(" + tmp + ") to status!", e);
            }
        }
        return null;
    }
}
