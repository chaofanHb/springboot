package hb.init;

import hb.dao.mongoDb_dao.UserDao;
import hb.dto.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * Created by Hebin on 2018/5/16.
 */
@Order(value = 1)
@Component
public class SystemInit implements CommandLineRunner {
	@Autowired
    private User testUser;

    @Override
    public void run(String... args) throws Exception {
        System.out.println(">>>>>>>>>>>>>>>服务启动执行，执行加载数据等操作<<<<<<<<<<<<<");
    	System.out.println(">>>>>>>>>>>>>>>"+testUser.toString()+"<<<<<<<<<<<<<");
    }
}
