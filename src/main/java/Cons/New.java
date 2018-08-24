package Cons;

import Cons.dao.basicDao.MongoCustomerDao;
import com.mongodb.MongoClient;
import org.mongodb.morphia.DatastoreImpl;
import org.mongodb.morphia.Morphia;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.jpa.JpaRepositoriesAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.io.IOException;


/**
 * Created by Administrator on 2017/7/21.
 */
//@EnableWebMvc
@SpringBootApplication
@EnableAutoConfiguration(exclude={JpaRepositoriesAutoConfiguration.class//禁止springboot自动加载持久化bean 便于创建JPA数据持久层原型(多数据源时)
})
//@EnableScheduling  //启动定时任务
//@MapperScan("cons.mapper")    //扫描接口
public class New extends SpringBootServletInitializer {
    public static void main(String[] args) throws IOException {
        SpringApplication.run(New.class,args);
        /*Properties properties=new Properties();
        InputStream in= new FileInputStream("/app.properties");
        properties.load(in);
        SpringApplication app=new SpringApplication(New.class);
        app.setDefaultProperties(properties);
        app.run(args);*/

    }
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(New.class);
    }
    @Autowired
    private Environment env;

    @Bean
    public MongoClient mongoClient(){
        return new MongoClient(env.getProperty("mongodb.host"),Integer.valueOf(env.getProperty("mongodb.port")));
    }

    @Bean
    public Morphia morphia(){
        return new Morphia();
    }

    @Bean
    public DatastoreImpl datastoreImpl(Morphia morphia, MongoClient mongoClient){
        return new  DatastoreImpl( morphia,  mongoClient, "zflow");
    }
    @Bean
    public MongoCustomerDao mongoCustomerDao(DatastoreImpl datastoreImpl){
        return new MongoCustomerDao(datastoreImpl);
    }
 /*   @Bean
    public DataSource dataSource() {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setUrl(env.getProperty("spring.datasource.url"));
        dataSource.setUsername(env.getProperty("spring.datasource.username"));//用户名
        dataSource.setPassword(env.getProperty("spring.datasource.password"));//密码
        dataSource.setInitialSize(2);
        dataSource.setMaxActive(20);
        dataSource.setMinIdle(0);
        dataSource.setMaxWait(60000);
        dataSource.setValidationQuery("SELECT 1");
        dataSource.setTestOnBorrow(false);
        dataSource.setTestWhileIdle(true);
        dataSource.setPoolPreparedStatements(false);
        return dataSource;
    }*/
}
