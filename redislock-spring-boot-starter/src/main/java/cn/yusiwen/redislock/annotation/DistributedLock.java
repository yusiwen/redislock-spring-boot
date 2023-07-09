package cn.yusiwen.redislock.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Redisson分布式锁注解
 *
 * @author Siwen Yu (yusiwen@gmail.com)
 */
@Documented
@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD})
public @interface DistributedLock {

    /**
     * 分布式锁名称
     *
     * @return 分布式锁名称
     */
    String value() default "distributed-lock-redisson";

    /**
     * 锁超时时间, 默认十秒
     *
     * @return 锁超时时间（单位：秒）
     */
    int expireSeconds() default 10;
}
