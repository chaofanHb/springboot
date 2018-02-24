package Cons.dao.mongoDb_dao;

import Cons.entity.CustomerBill;
import Cons.entity.User;

import java.util.List;

/**
 * Created by Administrator on 2017/8/2.
 */
public interface UserDao {
    public User findUserByUserName(String userName);
    public void saveUser(User user);
    public void updateUser(User user);
    public void deleteUserById(Long id);
    public List<User> searchPage();
    public void insertCustomer(List<CustomerBill> list);
    void innerSelect();
    public void test();
}
