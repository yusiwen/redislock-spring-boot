package cn.yusiwen.redislock.config.strategy;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.redisson.config.Config;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.yusiwen.redislock.config.RedissonProperties;
import cn.yusiwen.redislock.constant.GlobalConstant;

/**
 * 主从方式Redisson配置
 * <p>
 * 连接方式：主节点,子节点,子节点 格式为: 127.0.0.1:6379,127.0.0.1:6380,127.0.0.1:6381
 *
 * @author Siwen Yu (yusiwen@gmail.com)
 */
public class MasterSlaveRedissonConfigStrategyImpl implements RedissonConfigStrategy {

    /**
     * Logger
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(MasterSlaveRedissonConfigStrategyImpl.class);

    @Override
    public Config createRedissonConfig(RedissonProperties redissonProperties) {
        Config config = new Config();
        try {
            String address = redissonProperties.getAddress();
            String password = redissonProperties.getPassword();
            int database = redissonProperties.getDatabase();
            String[] addrTokens = address.split(",");
            String masterNodeAddr = addrTokens[0];
            /* 设置主节点ip */
            config.useMasterSlaveServers().setMasterAddress(masterNodeAddr);
            if (StringUtils.isNotBlank(password)) {
                config.useMasterSlaveServers().setPassword(password);
            }
            config.useMasterSlaveServers().setDatabase(database);
            /* 设置从节点，移除第一个节点，默认第一个为主节点 */
            List<String> slaveList = new ArrayList<>(addrTokens.length);
            for (String addrToken : addrTokens) {
                slaveList.add(GlobalConstant.REDIS_CONNECTION_PREFIX.getValue() + addrToken);
            }
            slaveList.remove(0);

            config.useMasterSlaveServers().addSlaveAddress(slaveList.toArray(new String[0]));
            LOGGER.info("初始化[MASTERSLAVE]方式Config, redisAddress: {}", address);
        } catch (Exception e) {
            LOGGER.error("MASTERSLAVE Redisson init error", e);
        }
        return config;
    }

}
