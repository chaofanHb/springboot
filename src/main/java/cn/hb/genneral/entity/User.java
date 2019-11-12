package cn.hb.genneral.entity;

import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Administrator on 2017/7/21.
 */
public class User implements Serializable {
    private Long id;
    private String username;
    private String company;
    private String title;
    private Long totalPop;
    private Date conmentTime;
    private String pid;

    private Companys companys;

    public User() {
    }

    public Date getConmentTime() {
        return conmentTime;
    }

    public void setConmentTime(Date conmentTime) {
        this.conmentTime = conmentTime;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
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
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", company='" + company + '\'' +
                ", title='" + title + '\'' +
                ", totalPop=" + totalPop +
                ", conmentTime=" + conmentTime +
                ", pid='" + pid + '\'' +
                ", companys=" + companys +
                '}';
    }

   /* public static User parse(Document object){
        User user=new User();
        user.setId(Long.valueOf(object.get("_id").toString()));
        user.setUsername(object.get("username").toString());
        user.setCompany(object.get("company").toString());
        user.setPid(object.get("pid").toString());
        user.setConmentTime((Date)object.get("conmentTime"));
        return  user;
    }

    public static User parseAppreUser(DBObject object){
        User user=new User();
        user.setCompany(object.get("_id").toString());
        user.setTotalPop(Long.valueOf(object.get("totalPop").toString()));
        return  user;
    }*/
}
