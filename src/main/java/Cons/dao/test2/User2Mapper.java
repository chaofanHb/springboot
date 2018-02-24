package Cons.dao.test2;


import Cons.entity.User;
import org.springframework.stereotype.Repository;

/**
 * Created by Administrator on 2017/7/31.
 */
@Repository
public interface User2Mapper {

    User getOne(Long id);

    void delete(Long id);
}
