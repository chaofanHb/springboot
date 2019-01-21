package hb.webscokt.version2;

/**
 * Created by Hebin on 2018/5/24.
 */
public class User {
    private String username;  //发送人或者接收人
    private String msg;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
