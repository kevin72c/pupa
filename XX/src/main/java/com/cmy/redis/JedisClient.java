package com.cmy.redis;

import java.util.Map;

import redis.clients.jedis.Jedis;

public class JedisClient {

    static Jedis jedis;
    static {
        jedis = new Jedis("127.0.0.1", 6380);
//        jedis.auth("");
    }
    
    /**
     * 获取字典
     * 
     * @param jedisEnum
     * @param dictName
     * @return
     */
    public Map<String, String> getDictionary(String dictName) {
        Map<String, String> dict = jedis.hgetAll(JedisEnum.dict + dictName);
        return dict;
    }

}
