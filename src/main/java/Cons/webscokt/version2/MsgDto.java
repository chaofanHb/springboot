package Cons.webscokt.version2;

/**
 * Created by Hebin on 2018/5/23.
 */
public class MsgDto {
    private Integer type;  //1上线 2 消息
    private User user;

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
