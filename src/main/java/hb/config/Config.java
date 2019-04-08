package hb.config;

import hb.dto.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;

/**
 * Created by Administrator on 2018/2/3.
 */
@Configuration
//当MyCondition返回true时初始化下列bean
@Conditional(MyCondition.class)
public class Config {
    @Bean(value = "testUser")
    public User getUser(){
        return new User("110","119");
    }
}
