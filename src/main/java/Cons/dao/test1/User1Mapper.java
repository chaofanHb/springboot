package Cons.dao.test1;


import Cons.entity.User;
import org.springframework.stereotype.Repository;

/**
 * Created by Administrator on 2017/7/31.
 */

//注意此处编译时xml是不会编译并导入到class
@Repository
public interface User1Mapper {

    User getOne(Long id);

    void delete(Long id);
}
