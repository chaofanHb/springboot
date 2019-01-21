package hb.dao;

import hb.New;
import hb.dao.mongoDb_dao.UserDao;
import hb.entity.User;
import hb.mapper.CustomerBillMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.List;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = New.class)
@MapperScan("hb.mapper")
public class DataTest {
    private static Logger logger= LoggerFactory.getLogger(DataTest.class);
    @Autowired
    private UserDao userDao;
    @Autowired
    private CustomerBillMapper customerBillMapper;
    @Test
    public void transfer(){
        userDao.test();
    }
    @Test
    public void testSaveUser() throws Exception {
        for (int i = 1; i < 15; i++) {
            User user=new User();
            user.setId(0l+i);
            user.setUsername("小明"+i);
            user.setCompany(""+i);
            user.setConmentTime(new Date());
            user.setPid("1");
            userDao.saveUser(user);
        }
    }

    @Test
    public void findUserByUserName(){
        User user= userDao.findUserByUserName("小明");
        logger.info("数据库取得name为小明：{}",user.toString());
    }

    @Test
    public void updateUser(){
        User user=new User();
        user.setId(11l);
        user.setUsername("小小");
        user.setCompany("fffxxxx");
        user.setTitle("x");
        userDao.updateUser(user);
    }

    @Test
    public void deleteUserById(){
        userDao.deleteUserById(2l);
    }
    @Test
    public void search(){
        List<User> list= userDao.searchPage();
        for (User user:list){
            System.out.println(user.toString());
        }
    }

    @Test
    public void ceshi(){
        for (int i = 0; i < 10; i++) {
            logger.info("log log log");
        }
    }

}
