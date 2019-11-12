package cn.hb.genneral.controller;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Cookie;
import okhttp3.CookieJar;
import okhttp3.HttpUrl;

public class LocalCookieJar implements CookieJar {

    List<Cookie> cookies;
    @Override
    public List<Cookie> loadForRequest(HttpUrl arg0) {
         if (cookies != null)
                return cookies;
            return new ArrayList<Cookie>();
    }

    @Override
    public void saveFromResponse(HttpUrl arg0, List<Cookie> cookies) {
        this.cookies = cookies;
    }
    

}
