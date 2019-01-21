package hb.RabitMQ;


import hb.entity.User;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * Created by Administrator on 2017/7/21.
 */
@Component
@RabbitListener(queues = "s")
public class HelloReceiver {

    @RabbitHandler
    public void process(User suser) {
        System.err.println("Receiver1  : " +suser.toString());
    }



}