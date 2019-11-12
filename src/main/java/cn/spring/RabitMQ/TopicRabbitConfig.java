package cn.spring.RabitMQ;

import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by Administrator on 2017/7/31.
 */
@Configuration
public class TopicRabbitConfig {
    final static String message = "api_core_total";
    final static String messages = "last_log_msg";

    @Bean
    public Queue queueMessage() {
        return new Queue(TopicRabbitConfig.message, false);
    }

    @Bean
    public Queue queueMessages() {
        return new Queue(TopicRabbitConfig.messages, true);
    }

    @Bean("exchange1")
    DirectExchange exchange1() {
        return new DirectExchange("BACKEND_EXCHANGE", false, false);
    }

    @Bean("exchange2")
    DirectExchange exchange2() {
        return new DirectExchange("MESSAGE_EXCHANGE", true, false);
    }

    @Bean
    Binding bindingExchangeMessage(Queue queueMessage, @Qualifier("exchange1")  DirectExchange exchange1) {
        return BindingBuilder.bind(queueMessage).to(exchange1).with("api_core_total");
    }

    @Bean
    Binding bindingExchangeMessages(Queue queueMessages,@Qualifier("exchange2")  DirectExchange exchange2) {
        return BindingBuilder.bind(queueMessages).to(exchange2).with("SYSINFO");
    }

}
