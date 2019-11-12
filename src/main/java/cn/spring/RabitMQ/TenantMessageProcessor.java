package cn.spring.RabitMQ;

import com.rabbitmq.client.MessageProperties;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessagePostProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by cn.hb on 2018/8/21.
 */
@Component
public class TenantMessageProcessor implements MessagePostProcessor {


    @Override
    public Message postProcessMessage(Message message) throws AmqpException {
        return message;
    }

}
