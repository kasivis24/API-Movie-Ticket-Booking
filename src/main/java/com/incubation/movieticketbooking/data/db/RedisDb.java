package com.incubation.movieticketbooking.data.db;

import com.incubation.movieticketbooking.data.dbconfig.RedisConnection;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.params.SetParams;

public class RedisDb {

    private static Jedis jedis;

    private static RedisDb redisDb;

    private RedisDb() {
        jedis = RedisConnection.getConnection();
    }

    public static RedisDb getInstance(){
        if (redisDb == null) {
            redisDb = new RedisDb();
        }
        return redisDb;
    }

    public boolean isBooked(String lockKey,String lockValue){

        SetParams params = new SetParams();
        params.nx().ex(10);
        String result = jedis.set(lockKey, lockValue, params);

        return result.equals("OK");

    }

}
