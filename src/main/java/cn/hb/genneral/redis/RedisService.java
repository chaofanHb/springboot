/*
 * Copyright (c) 2017 <l_iupeiyu@qq.com> All rights reserved.
 */

package cn.hb.genneral.redis;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPool;

import java.util.ArrayList;
import java.util.List;

@Service
public class RedisService {

    private static Logger logger = LoggerFactory.getLogger(RedisService.class);
    private final static String orderPrefix = "order_";
    @Autowired
    private ShardedJedisPool shardedJedisPool;


    public void set(String key, String value) {
        ShardedJedis shardedJedis = null;
        try {
            shardedJedis = shardedJedisPool.getResource();
            shardedJedis.set(key, value);
            logger.info("Redis set success - " + key + ", value:" + value);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("Redis set error: " + e.getMessage() + " - " + key + ", value:" + value);
        } finally {
            if (shardedJedis != null)
                shardedJedisPool.returnResource(shardedJedis);
        }
    }


    public String get(String key) {
        String result = null;
        ShardedJedis shardedJedis = null;
        try {
            shardedJedis = shardedJedisPool.getResource();
            result = shardedJedis.get(key);
            logger.info("Redis get success - " + key + ", value:" + result);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("Redis set error: " + e.getMessage() + " - " + key + ", value:" + result);
        } finally {
            if (shardedJedis != null)
                shardedJedisPool.returnResource(shardedJedis);
        }
        return result;
    }

    public boolean exists(String key) {
        ShardedJedis shardedJedis = null;
        try {
            shardedJedis = shardedJedisPool.getResource();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (shardedJedis != null)
                shardedJedisPool.returnResource(shardedJedis);
        }
        return shardedJedis.exists(key);
    }


    public void lPush(String json) {
        ShardedJedis shardedJedis = null;
        try {
            shardedJedis = shardedJedisPool.getResource();
            shardedJedis.lpush(orderPrefix, json);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (shardedJedis != null)
                shardedJedisPool.returnResource(shardedJedis);
        }
    }

    public List<String> lRangeAll() {
        List<String> jsons = new ArrayList<String>();
        ShardedJedis shardedJedis = null;
        try {
            shardedJedis = shardedJedisPool.getResource();
            jsons = shardedJedis.lrange(orderPrefix, 0, -1);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (shardedJedis != null)
                shardedJedisPool.returnResource(shardedJedis);
        }
        return jsons;
    }

    public void lRem(String value) {
        ShardedJedis shardedJedis = null;
        try {
            shardedJedis = shardedJedisPool.getResource();
            shardedJedis.lrem(orderPrefix, 0, value);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (shardedJedis != null)
                shardedJedisPool.returnResource(shardedJedis);
        }
    }

    public void lset(String json) {
        ShardedJedis shardedJedis = null;
        try {
            shardedJedis = shardedJedisPool.getResource();
            shardedJedis.lset(orderPrefix, 0, json);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (shardedJedis != null)
                shardedJedisPool.returnResource(shardedJedis);
        }
    }
}
