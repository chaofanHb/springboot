package cn.hb.genneral.init;

import cn.hb.genneral.util.DisableSSLCertificateCheckUtil;
import cn.hb.genneral.controller.EhCacheTestService;
import cn.hb.genneral.dto.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * springboot容器加载完成后执行
 * Created by Hebin on 2018/5/16.
 */
@Order(value = 1)
@Component
public class SystemInit implements CommandLineRunner {
	//@Autowired
    //private User testUser;
	
    @Autowired  
    private EhCacheTestService ehCacheTestService;
	
    //@Autowired
    //private User1Mapper user1Mapper;

    @Override
    public void run(String... args) throws Exception {
        System.out.println(">>>>>>>>>>>>>>>服务启动执行，执行加载数据等操作<<<<<<<<<<<<<");
    	//System.out.println(">>>>>>>>>>>>>>>"+testUser.toString()+"<<<<<<<<<<<<<");
        //System.out.println(">>>>>>>>>>>>>>>"+user1Mapper.getOne(1l).toString()+"<<<<<<<<<<<<<");
        
        //System.out.println("第一次调用：" + ehCacheTestService.getTimestamp("param"));
        //Thread.sleep(2000);
        //System.out.println("2秒之后调用：" + ehCacheTestService.getTimestamp("param"));
        //Thread.sleep(11000);
        //System.out.println("再过11秒之后调用：" + ehCacheTestService.getTimestamp("param"));
    }
}
