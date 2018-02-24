package Cons.dao.basicDao;

import Cons.entity.CustomerBill;
import org.mongodb.morphia.DatastoreImpl;

/**
 * Created by Administrator on 2017/11/22.
 */
public class MongoCustomerDao extends MongoDao<CustomerBill,String> {
    public MongoCustomerDao(DatastoreImpl ds) {
        super(ds);
    }
}
