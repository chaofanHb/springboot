package hb.entity;

import java.util.List;

/**
 * Created by Administrator on 2017/9/11.
 */
public class Companys {
    private String cid;
    private String uid;
    private String displayName;

    private List<Double> ids;

    public Companys(){}
    public Companys(String cid, String uid, String displayName) {
        this.cid = cid;
        this.uid = uid;
        this.displayName = displayName;
    }

    public List<Double> getIds() {
        return ids;
    }

    public void setIds(List<Double> ids) {
        this.ids = ids;
    }

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    @Override
    public String toString() {
        return "Companys{" +
                "cid='" + cid + '\'' +
                ", uid='" + uid + '\'' +
                ", displayName='" + displayName + '\'' +
                ", id='" + ids + '\'' +
                '}';
    }

    public static void main(String[] args) {
        System.out.println(new Companys().toString());
    }
}
