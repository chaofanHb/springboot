package Cons.entity;

import com.mongodb.DBObject;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/7/21.
 */
public class User implements Serializable {
    private Long id;
    private String username;
    private String company;
    private String title;
    private Long totalPop;

    private Companys companys;

    public User() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public Long getTotalPop() {
        return totalPop;
    }

    public void setTotalPop(Long totalPop) {
        this.totalPop = totalPop;
    }

    public Companys getCompanys() {
        return companys;
    }

    public void setCompanys(Companys companys) {
        this.companys = companys;
    }

    @Override
    public String toString() {
        String companystr=companys==null?null:companys.toString();
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", company='" + company + '\'' +
                ", title='" + title + '\'' +
                ", totalPop='" + totalPop + '\'' +
                ", companys='" +companystr+ '\'' +
                '}';
    }

    public static User parse(DBObject object){
        User user=new User();
        user.setId(Long.valueOf(object.get("_id").toString()));
        user.setUsername(object.get("username").toString());
        user.setCompany(object.get("company").toString());
        return  user;
    }

    public static User parseAppreUser(DBObject object){
        User user=new User();
        user.setCompany(object.get("_id").toString());
        user.setTotalPop(Long.valueOf(object.get("totalPop").toString()));
        return  user;
    }
}
