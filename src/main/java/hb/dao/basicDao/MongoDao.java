package hb.dao.basicDao;

import org.mongodb.morphia.DatastoreImpl;
import org.mongodb.morphia.dao.BasicDAO;

/**
 * Created by Administrator on 2017/11/22.
 */
public class MongoDao<K,T> extends BasicDAO<K,T> {

    public MongoDao(DatastoreImpl ds) {
        super(ds);
    }

}
