package com.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;

import javax.annotation.Resource;
import java.net.URL;

/**
 * Created by hnzb on 15/12/15.
 */
public class RedisExample {

    @Autowired
    private RedisTemplate<String, String> template;

    @Resource(name="redisTemplate")
    private ListOperations<String, String> listOperations;

    public void addLink(String userId, URL url) {
        listOperations.leftPush(userId, url.toExternalForm());//url.toExternalForm()构造此url字符串形式
    }
}
