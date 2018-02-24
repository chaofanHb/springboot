package Cons.xls;

import java.io.IOException;
import java.io.Serializable;
import java.util.regex.Pattern;

/**
 * Created by Administrator on 2017/7/27.
 */
public class User implements Serializable {
    private String username;
    private String password;
    private String nickname;


    public User() {
        super();
    }

    public User(String username, String password, String nickname) {
        this.username = username;
        this.password = password;
        this.nickname = nickname;
    }

    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getNickname() {
        return nickname;
    }
    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();

        sb.append("username=");
        sb.append(username);
        sb.append(";password=");
        sb.append(password);
        sb.append(";nickname=");
        sb.append(nickname);

        return sb.toString();
    }

    public static String createName(String fileName) {
        if (fileName != null) {
            if(fileName.lastIndexOf(")")>=0){
                String v= fileName.substring(fileName.lastIndexOf("(")+1,fileName.lastIndexOf(")"));
                Pattern pattern = Pattern.compile("[0-9]*");
                if(pattern.matcher(v).matches()){
                    Integer num=Integer.valueOf(v);
                    num++;
                    return fileName.replace(v,num.toString());
                }
            }else {
                return fileName.substring(0,fileName.lastIndexOf("."))+"(1).xlsx";
            }
        }
        return null;
    }
    public static void main(String[] args) throws IOException {

    }
}