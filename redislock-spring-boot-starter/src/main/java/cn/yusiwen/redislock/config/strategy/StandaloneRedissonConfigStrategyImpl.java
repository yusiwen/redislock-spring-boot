package cn.yusiwen.redislock.config.strategy;

import org.apache.commons.lang3.StringUtils;
import org.redisson.config.Config;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.yusiwen.redislock.config.RedissonProperties;
import cn.yusiwen.redislock.constant.GlobalConstant;

/**
 * 单机方式Redisson配置
 *
 * @author Siwen Yu (yusiwen@gmail.com)
 */
public class StandaloneRedissonConfigStrategyImpl implements RedissonConfigStrategy {

    /**
     * Logger
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(StandaloneRedissonConfigStrategyImpl.class);

    @Override
    public Config createRedissonConfig(RedissonProperties redissonProperties) {
        Config config = new Config();
        try {
            String address = redissonProperties.getAddress();
            String password = redissonProperties.getPassword();
            int database = redissonProperties.getDatabase();
            String redisAddr = GlobalConstant.REDIS_CONNECTION_PREFIX.getValue() + address;
            config.useSingleServer().setAddress(redisAddr);
            config.useSingleServer().setDatabase(database);
            if (StringUtils.isNotBlank(password)) {
                config.useSingleServer().setPassword(password);
            }
            LOGGER.info("初始化[standalone]方式Config, redisAddress: {}", address);
        } catch (Exception e) {
            LOGGER.error("standalone Redisson init error", e);
        }
        return config;
    }
}
