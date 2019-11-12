package cn.spring.RabitMQ;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * Created by Administrator on 2017/8/2.
 */
@Component
@RabbitListener(queues = "topic.message")
public class TopicReceiver1 {
    @RabbitHandler
    public void process(String suser) {
        System.out.println("TopicReceiver1  : " +suser);
    }
}
