package cn.yusiwen.redislock.config.strategy;

import org.redisson.config.Config;

import cn.yusiwen.redislock.config.RedissonProperties;

/**
 * Redisson配置构建接口
 *
 * @author Siwen Yu (yusiwen@gmail.com)
 */
public interface RedissonConfigStrategy {

    /**
     * 根据不同的Redis配置策略创建对应的Config
     *
     * @param redissonProperties
     * @return Config
     */
    Config createRedissonConfig(RedissonProperties redissonProperties);
}
