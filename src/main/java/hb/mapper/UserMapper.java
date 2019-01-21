package hb.mapper;

import hb.entity.Credit;
import hb.entity.User;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Administrator on 2017/7/24.
 */
@Repository
public interface UserMapper {
    @Select("select * from sys_user")
    @Results({@Result(property = "username",column = "username"),@Result(property = "company",column = "company")})
    public List<User> getAll();

    @Select("select * from sys_user where username=#{name}")
    @Results({@Result(property = "username",column = "username"),@Result(property = "company",column = "company")})
    public User get(String name);

    @Insert(" insert into sys_credit(user_id, balance, credit_line) values(#{userId}, #{balance}, #{creditLine});")
    public void save(Credit credit);

    @Update("update sys_credit set credit_line =#{creditLine} where user_id=#{userId}")
    public void update(Credit credit);

    @Delete("delete from sys_credit where user_id='${id}'")
    public void delete(@Param("id")String id);
}
