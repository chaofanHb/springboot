package Cons.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.cas.authentication.CasAssertionAuthenticationToken;
import org.springframework.security.core.userdetails.AuthenticationUserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by EricHuang on 2017/10/27.
 * Author list:
 * EricHuang,
 */
/*public class CustomUserDetailsService implements AuthenticationUserDetailsService<CasAssertionAuthenticationToken> {
    Logger logger = LoggerFactory.getLogger(CustomUserDetailsService.class);


    @Override
    public UserDetails loadUserDetails(CasAssertionAuthenticationToken casAssertionAuthenticationToken) throws UsernameNotFoundException {
        String currentUserName = casAssertionAuthenticationToken.getName();
        logger.info("当前登陆的用户名是：" + currentUserName);
        UserInfo userInfo = new UserInfo();
        userInfo.setName("hebin");
        userInfo.setPassword("1234");
        userInfo.setUsername("hb");
        Set<AuthorityInfo> authorities = new HashSet<>();
        authorities.add(new AuthorityInfo("ADMIN"));
        userInfo.setAuthorities(authorities);
        return userInfo;
    }

}*/
