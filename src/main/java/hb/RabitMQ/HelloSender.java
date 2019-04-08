package hb.RabitMQ;

import hb.entity.User;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by Administrator on 2017/7/21.
 */
@Component
public class HelloSender {
    private Logger logger=LoggerFactory.getLogger(HelloSender.class);
    @Autowired
    private AmqpTemplate rabbitTemplate;

    public void send(User user) {
        //无规则发送
        System.err.println("sender:"+user);
        this.rabbitTemplate.convertAndSend("s",user);
    }
}
