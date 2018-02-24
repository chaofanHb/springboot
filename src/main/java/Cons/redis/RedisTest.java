package Cons.redis;

import Cons.New;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by Administrator on 2017/8/30.
 */
@SpringBootTest(classes = New.class)
@RunWith(SpringRunner.class)
public class RedisTest {
    private static final Logger logger= LoggerFactory.getLogger(RedisTest.class);
    @Autowired
   private RedisService redisService;
    @Test
    public void test(){
        logger.info("测试redis:{}",redisService.exists("ss"));
    }
}
