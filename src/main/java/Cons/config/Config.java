package Cons.config;

import Cons.dto.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;

/**
 * Created by Administrator on 2018/2/3.
 */
@Configuration
@Conditional(MyCondition.class)
public class Config {
    @Bean(value = "user")
    public User getUser(){
        return new User("110","119");
    }

    @Bean
    public User hrt(){
        return getUser();
    }
}
