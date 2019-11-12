package cn.spring.security;

import com.alibaba.druid.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;


/**
 * Created by cn.hb on 2018/8/16.
 */
/*public class WebBasicConfig extends WebSecurityConfigurerAdapter {
    @Overrides
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.authorizeRequests()
                .antMatchers("/no").authenticated()
                .anyRequest().permitAll()
                .and().httpBasic();
    }

}*/
