package cn.yusiwen.redislock;

import java.util.concurrent.TimeUnit;

import org.redisson.api.RLock;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 分布式锁实现基于Redisson
 *
 * @author Siwen Yu (yusiwen@gmail.com)
 */
public class RedissonLock {

    /**
     * Logger
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(RedissonLock.class);

    /**
     * RedissonManager
     */
    RedissonManager redissonManager;

    public RedissonLock(RedissonManager redissonManager) {
        this.redissonManager = redissonManager;
    }

    public RedissonLock() {}

    /**
     * 加锁操作
     *
     * @param lockName Name of lock
     * @param expireSeconds Expire time in seconds
     * @return 是否成功加锁
     */
    public boolean lock(String lockName, long expireSeconds) {
        RLock rLock = redissonManager.getRedisson().getLock(lockName);
        boolean getLock;
        try {
            getLock = rLock.tryLock(0, expireSeconds, TimeUnit.SECONDS);
            if (getLock) {
                LOGGER.info("获取Redisson分布式锁[成功],lockName={}", lockName);
            } else {
                LOGGER.info("获取Redisson分布式锁[失败],lockName={}", lockName);
            }
        } catch (InterruptedException e) {
            LOGGER.error("获取Redisson分布式锁[异常]，lockName=%{}", lockName, e);
            Thread.currentThread().interrupt();
            return false;
        }
        return getLock;
    }

    /**
     * 解锁
     *
     * @param lockName Name of lock
     */
    public void release(String lockName) {
        redissonManager.getRedisson().getLock(lockName).unlock();
    }

    public RedissonManager getRedissonManager() {
        return redissonManager;
    }

    public void setRedissonManager(RedissonManager redissonManager) {
        this.redissonManager = redissonManager;
    }

}
