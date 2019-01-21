package hb.RabitMQ;

import hb.New;
import hb.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by Administrator on 2017/7/21.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = New.class)
public class RabbitMqHelloTest {

    @Autowired
    private HelloSender helloSender;
    @Autowired
    private TopicSender topicSender;

    @Test
    public void hello() throws Exception {
        User user = new User();
        user.setId(Long.valueOf("100"));
        user.setUsername("何斌");
        user.setCompany("武汉迈异有限公司");
        helloSender.send(user);
    }

    @Test
    public void topic() throws Exception {
        //topicSender.send1();
        topicSender.send1();
        topicSender.send2();
    }
}
