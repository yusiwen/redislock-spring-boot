package cn.yusiwen.redislock;

import cn.yusiwen.redislock.executor.ExecutorRedissonNormal;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;

import static org.hamcrest.Matchers.greaterThan;
import static org.junit.Assert.assertThat;

@SpringBootTest(properties = "fetch-rate=50")
public class RedisLockStarterDemoApplicationTests {

    @SpyBean
    ExecutorRedissonNormal executorRedissonNormal;

    @Test
    public void givenSleepBy100ms_whenGetCount_thenIsGreaterThanZero() throws InterruptedException {
        Thread.sleep(11000L);

        assertThat(executorRedissonNormal.getCount(), greaterThan(2));
    }

}
