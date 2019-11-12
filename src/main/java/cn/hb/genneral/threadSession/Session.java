package cn.hb.genneral.threadSession;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Administrator on 2017/12/21.
 */
public class Session implements Serializable {
    private static final long serialVersionUID = 6381347343062751902L;

    private static int a=1;
    private Date date;
    private long timestamp;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public static void getSession(){
        System.out.println(RestAuthUtils.getSession().toString());
    }

    public static void main(String[] args) {
        System.out.println(System.currentTimeMillis());
    }

    @Override
    public String toString() {
        return "Session{" +
                "date=" + date +
                ", timestamp=" + timestamp +
                '}';
    }
}
