package cn.yusiwen.redislock.config.strategy;

import org.redisson.config.Config;

import cn.yusiwen.redislock.config.RedissonProperties;

/**
 * Redisson配置上下文
 *
 * @author Siwen Yu (yusiwen@gmail.com)
 */
public class RedissonConfigContext {

    /**
     * RedissonConfigStrategy
     */
    private final RedissonConfigStrategy redissonConfigStrategy;

    public RedissonConfigContext(RedissonConfigStrategy redissonConfigStrategy) {
        this.redissonConfigStrategy = redissonConfigStrategy;
    }

    /**
     * 上下文根据构造中传入的具体策略产出真实的Redisson的Config
     *
     * @param redissonProperties RedissonProperties
     * @return Config
     */
    public Config createRedissonConfig(RedissonProperties redissonProperties) {
        return this.redissonConfigStrategy.createRedissonConfig(redissonProperties);
    }
}
