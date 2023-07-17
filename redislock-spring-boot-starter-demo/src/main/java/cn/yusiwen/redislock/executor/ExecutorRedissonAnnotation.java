package cn.yusiwen.redislock.executor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import cn.yusiwen.redislock.annotation.DistributedLock;

/**
 * @author Siwen Yu (yusiwen@gmail.com)
 */
@Component
public class ExecutorRedissonAnnotation {

    /**
     * Logger
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(ExecutorRedissonAnnotation.class);

    @Scheduled(cron = "${redis.lock.cron}")
    @DistributedLock(value = "redis-lock", expireSeconds = 11)
    public void execute() throws InterruptedException {
        LOGGER.info("[ExecutorRedisson]--执行定时任务开始，休眠三秒");
        Thread.sleep(3000);
        LOGGER.info("=======================业务逻辑=============================");
        LOGGER.info("[ExecutorRedisson]--执行定时任务结束，休眠三秒");
    }
}
