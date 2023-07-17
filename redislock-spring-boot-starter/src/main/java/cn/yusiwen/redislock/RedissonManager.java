package cn.yusiwen.redislock;

import com.google.common.base.Preconditions;

import org.redisson.Redisson;
import org.redisson.config.Config;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.yusiwen.redislock.config.RedissonProperties;
import cn.yusiwen.redislock.config.strategy.ClusterRedissonConfigStrategyImpl;
import cn.yusiwen.redislock.config.strategy.MasterSlaveRedissonConfigStrategyImpl;
import cn.yusiwen.redislock.config.strategy.RedissonConfigContext;
import cn.yusiwen.redislock.config.strategy.SentinelRedissonConfigStrategyImpl;
import cn.yusiwen.redislock.config.strategy.StandaloneRedissonConfigStrategyImpl;
import cn.yusiwen.redislock.constant.RedisConnectionType;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;

/**
 * Redisson核心配置，用于提供初始化的redisson实例
 *
 * @author Siwen Yu (yusiwen@gmail.com)
 */
public class RedissonManager {

    /**
     * Logger
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(RedissonManager.class);

    /**
     * Redisson instance
     */
    private final Redisson redisson;

    @SuppressFBWarnings({"EXS_EXCEPTION_SOFTENING_NO_CONSTRAINTS", "LEST_LOST_EXCEPTION_STACK_TRACE"})
    public RedissonManager(RedissonProperties redissonProperties) {
        try {
            Config config = RedissonConfigFactory.getInstance().createConfig(redissonProperties);
            redisson = (Redisson)Redisson.create(config);
        } catch (Exception e) {
            LOGGER.error("Redisson init error", e);
            throw new IllegalArgumentException("please input correct configurations,"
                + "connectionType must in standalone/sentinel/cluster/masterslave");
        }
    }

    public Redisson getRedisson() {
        return redisson;
    }

    /**
     * Redisson连接方式配置工厂 双重检查锁
     */
    static class RedissonConfigFactory {

        /**
         * Instance
         */
        private static final RedissonConfigFactory FACTORY = new RedissonConfigFactory();

        private RedissonConfigFactory() {}

        public static RedissonConfigFactory getInstance() {
            return FACTORY;
        }

        /**
         * 根据连接类型获取对应连接方式的配置,基于策略模式
         *
         * @param redissonProperties RedissonProperties
         * @return Config
         */
        Config createConfig(RedissonProperties redissonProperties) {
            Preconditions.checkNotNull(redissonProperties);
            Preconditions.checkNotNull(redissonProperties.getAddress(), "redisson.lock.server.address cannot be NULL!");
            Preconditions.checkNotNull(redissonProperties.getType(), "redisson.lock.server.password cannot be NULL");
            String connectionType = redissonProperties.getType();
            /* 声明配置上下文 */
            RedissonConfigContext redissonConfigContext;
            if (connectionType.equals(RedisConnectionType.STANDALONE.getType())) {
                redissonConfigContext = new RedissonConfigContext(new StandaloneRedissonConfigStrategyImpl());
            } else if (connectionType.equals(RedisConnectionType.SENTINEL.getType())) {
                redissonConfigContext = new RedissonConfigContext(new SentinelRedissonConfigStrategyImpl());
            } else if (connectionType.equals(RedisConnectionType.CLUSTER.getType())) {
                redissonConfigContext = new RedissonConfigContext(new ClusterRedissonConfigStrategyImpl());
            } else if (connectionType.equals(RedisConnectionType.MASTERSLAVE.getType())) {
                redissonConfigContext = new RedissonConfigContext(new MasterSlaveRedissonConfigStrategyImpl());
            } else {
                throw new IllegalArgumentException("创建Redisson连接Config失败！当前连接方式:" + connectionType);
            }
            return redissonConfigContext.createRedissonConfig(redissonProperties);
        }
    }

}
