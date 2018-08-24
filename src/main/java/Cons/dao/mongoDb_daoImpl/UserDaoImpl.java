package Cons.dao.mongoDb_daoImpl;

import Cons.dao.basicDao.MongoCustomerDao;
import Cons.dao.mongoDb_dao.UserDao;
import Cons.entity.Companys;
import Cons.entity.CustomerBill;
import Cons.entity.GroupUser;
import Cons.entity.User;
import com.mongodb.AggregationOutput;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.util.JSON;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


@Component
public class UserDaoImpl implements UserDao {
    @Autowired
    private MongoTemplate mongoTemplate;
    //更新需要用$set函数  否则只保留更新字段，删除其他字段
    @Autowired
    private MongoCustomerDao mongoCustomerDao;

    public void test(){
        org.mongodb.morphia.query.Query<CustomerBill> q = mongoCustomerDao.createQuery();
        q.offset(1);
        q.limit(2);
        List<CustomerBill> customerBills= mongoCustomerDao.find(q).asList();
        for(CustomerBill customerBill:customerBills){
            System.out.println(customerBill.toString());
        }
    }

    @Override
    public User findUserByUserName(String userName) {
        Query query = new Query(Criteria.where("username").is(userName));
        selectAggrega();
        return mongoTemplate.findOne(query, User.class);
    }
    public void insertCustomer(List<CustomerBill> list){
        for (CustomerBill bill:list){
            mongoTemplate.save(bill);
        }
    }
    @Override
    public void saveUser(User user) {
        mongoTemplate.save(user);
    }

    @Override
    public void updateUser(User user) {
        Query query = new Query(Criteria.where("id").is(user.getId()));
        Update update = new Update().set("username", user.getUsername()).set("title", null);
        //更新查询返回结果集的第一条
        mongoTemplate.updateFirst(query, update, User.class);
        //更新查询返回结果集的所有
        // mongoTemplate.updateMulti(query,update,UserEntity.class);
    }

    public void updateUserLt(User user) {
        Query query = new Query(Criteria.where("id").lt(user.getId()));
        Update update = new Update().set("username", user.getUsername()).set("company", user.getCompany());
        //更新查询返回结果集的第一条
        //mongoTemplate.updateFirst(query, update, User.class);
        //更新查询返回结果集的所有
         mongoTemplate.updateMulti(query,update,User.class);
    }

    @Override
    public void deleteUserById(Long id) {
        Query query = new Query(Criteria.where("id").is(id));
        mongoTemplate.remove(query, User.class);
    }

    public List<User> searchPage(){
        List<User> list=new ArrayList<User>();
        DBCursor limit=mongoTemplate.getCollection("user").find();//.skip(5).limit(10);
        while (limit.hasNext()){
            list.add(User.parse(limit.next()));
        }
        return list;
    }

    public void innerSelect(){
        //多对一
        Query query1=new Query(Criteria.where("companys.$id").is(new ObjectId("59b62aaf209b46f0bc005d93")));
        List<User> results1=mongoTemplate.find(query1,User.class);
        for (User user:results1){
            System.out.println(user.toString());
        }
        //一对多
        Query query2=new Query(Criteria.where("cid").is("C"));
        Companys companys= mongoTemplate.findOne(query2, Companys.class);
        Query query3=new Query(Criteria.where("_id").in(companys.getIds()));
        List<User> results2=mongoTemplate.find(query3,User.class);
        for (User user:results2){
            System.out.println(user.toString());
        }
        //
    }

    public void selectAggrega(){
        //db.getCollection('user').aggregate({$group:{_id:"$company",company:{$sum:"$_id"}}},{$match:{company:{$gt:10}}},{$sort:{company:1}})
        String group="{$group:{_id:\"$company\",totalPop:{$sum:\"$_id\"}}}";
        String match="{$match:{totalPop:{\"$gt\":10}}}";
        DBObject grou= (DBObject) JSON.parse(group);
        DBObject mat= (DBObject) JSON.parse(match);
        DBObject sort= (DBObject) JSON.parse("{$sort:{totalPop:1}}");
        AggregationOutput output= mongoTemplate.getCollection("user").aggregate(grou,mat,sort);
        Iterator<DBObject> iterator= output.results().iterator();
        List<User> list=new ArrayList<User>();
        while (iterator.hasNext()){
            list.add(User.parseAppreUser(iterator.next()));
        }
        for(User user:list){
            System.out.println(user.getCompany()+"\t"+user.getTotalPop());
        }
    }
    public void selectGroup(){
        //db.getCollection('user').group({key:{company:true},condition:{_id:{$gt:10}},initial:{num:0},$reduce:function(doc,prev){prev.num++}})

        //String reduce="function(obj, prev) {prev.isum += obj._id}";
        //DBObject dbObject= mongoTemplate.getCollection("user").group(new BasicDBObject("company",true),new BasicDBObject("username",new BasicDBObject("$exists",false)),new BasicDBObject("isum",0),reduce );

        String reduce="function(obj, prev) {prev.isum += obj._id}";
        DBObject dbObject= mongoTemplate.getCollection("user").group(new BasicDBObject("_id",true).append("company",true),new BasicDBObject("username",new BasicDBObject("$exists",false)),new BasicDBObject("isum",0),reduce );
        new GroupUser().parseGroupUser(dbObject);
    }
}
