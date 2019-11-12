package cn.hb.genneral.threadSession;

import java.util.Date;

/**
 * Created by Administrator on 2017/12/21.
 */
public class RestAuthUtils {
    private static final ThreadLocal<Session> threadSession = new ThreadLocal();

    public RestAuthUtils() {
    }

    public static void cleanThreadLocalSession() {
        threadSession.remove();
    }

    public static Session getSession() {
        return (Session)threadSession.get();
    }

    public static void setSession(Session s) {
        threadSession.set(s);
    }

    public static Date currentDate() {
        Session s = getSession();
        return s != null?s.getDate():null;
    }

}
