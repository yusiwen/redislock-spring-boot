package cn.yusiwen.redislock;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

@Configuration
@EnableScheduling
@ComponentScan("cn.yusiwen.redislock")
public class ScheduleConfig {
}
