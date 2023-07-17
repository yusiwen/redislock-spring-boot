package cn.yusiwen.redislock;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

import cn.yusiwen.redislock.config.EnableRedissonLock;

/**
 * @author Siwen Yu (yusiwen@gmail.com)
 */
@EnableRedissonLock
@EnableScheduling
@SpringBootApplication
public class RedisLockStarterDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(RedisLockStarterDemoApplication.class, args);
    }
}
