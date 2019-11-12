package cn.hb.genneral.dto;

import org.springframework.util.DigestUtils;

/**
 *
 * Create By Adminstrator on 2018/1/1
 * @author hebin
* */
public class User {
    private String id;
    private String name;

    public User() {
    }
    public User(String id, String name) {
        this.id=id;
        this.name=name;
    }


    public User(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
    User u = new User();
   
    public static void main(String[] args) {
        /*ThreadFactory factory = new ThreadFactoryBuilder().setNameFormat("flow-controller-exec-%d").build();
        SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-DD hh:mm:ss");
        ScheduledExecutorService exec = Executors.newScheduledThreadPool(5, factory);
        for (int i = 0; i <5 ; i++) {
            exec.schedule(new Runnable() {
                @Override
                public void run() {
                    System.out.println(Thread.currentThread().getName());
                    System.out.println(df.format(new Date()));
                }
            },i*5, TimeUnit.SECONDS);
        }*/
        System.out.println(DigestUtils.md5DigestAsHex("hebin".getBytes()));
        //new User();
        
    	

    }
    
    public static void test() {
    	System.err.println("111");
    }
}
