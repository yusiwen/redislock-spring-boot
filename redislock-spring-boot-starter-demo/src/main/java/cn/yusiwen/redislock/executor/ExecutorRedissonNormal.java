package cn.yusiwen.redislock.executor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import cn.yusiwen.redislock.RedissonLock;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author Siwen Yu (yusiwen@gmail.com)
 */
@Component
public class ExecutorRedissonNormal {

    /**
     * Logger
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(ExecutorRedissonNormal.class);

    /**
     * RedissonLock
     */
    @Autowired
    RedissonLock redissonLock;

    private AtomicInteger count = new AtomicInteger(0);

    @Scheduled(cron = "${redis.lock.cron}")
    public void execute() throws InterruptedException {
        if (redissonLock.lock("redisson", 10)) {
            LOGGER.info("[ExecutorRedisson]--执行定时任务开始，休眠三秒");
            Thread.sleep(3000);
            LOGGER.info("=======================业务逻辑=============================");
            this.count.incrementAndGet();
            LOGGER.info("[ExecutorRedisson]--执行定时任务结束，休眠三秒");
            redissonLock.release("redisson");
        } else {
            LOGGER.info("[ExecutorRedisson]获取锁失败");
        }

    }

    public int getCount() {
        return count.get();
    }

}
