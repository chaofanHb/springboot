package cn.hb.genneral.controller;
import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.Set;

import org.springframework.stereotype.Service;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

@Service
public class MyOkHttp {
	private final OkHttpClient client = new OkHttpClient().newBuilder()
            .followRedirects(false)  //禁制OkHttp的重定向操作，我们自己处理重定向
            .followSslRedirects(false)
            .cookieJar(new LocalCookieJar())   //为OkHttp设置自动携带Cookie的功能
            .build();
	
	public String upload(final Map<String, Object> map) {
		 //构造一个POST请求
        RequestBody body = new FormBody.Builder().add("UserStyle", "student").build();
                
        Request request = new Request.Builder()
        		.addHeader("Content-Type", "application/x-www-form-urlencoded")
        		.addHeader("Cookie", "route=967d6b0b007ff44d6755831c427b718b;CASTGC=TGT-16136-ndaRsFf3UF1XbEwWzi0Sf1jd2ZTxMVJ10XnVynUoOQLSAGWMrj1555036488007-gctD-cas;")
        		.url("http://ehall.jhun.edu.cn/hqfw/sys/emapcomponent/file/uploadTempFile.do").post(body).build();
        
        client.newCall(request).enqueue(new Callback() {  
            @Override
            public void onResponse(Call call, Response response) throws IOException {
            	System.out.println(response.code());
            	File file=new File("F:\\aaa.png");
                // form 表单形式上传
                MultipartBody.Builder requestBody = new MultipartBody.Builder().setType(MultipartBody.FORM);
                if (file != null) {
                    // MediaType.parse() 里面是上传的文件类型。
                    RequestBody body = RequestBody.create(MediaType.parse("image/*"), file);
                    String filename = file.getName();
                    // 参数分别为， 请求key(随机字符用于前后包夹流) ，文件名称 ， RequestBody
                    requestBody.addFormDataPart("avata", filename+";Content-Type:application/octet-stream", body);
                }
                if (map != null) {
                    // map 里面是请求中所需要的 key 和 value
                    Set<Map.Entry<String, Object>> entries = map.entrySet();
                    for (Map.Entry entry : entries) {
                        String key = String.valueOf(entry.getKey());
                        String value = String.valueOf(entry.getValue());
                        //Log.d("HttpUtils", "key=="+key+"value=="+value);
                        requestBody.addFormDataPart(key,value);
                    }
                }
            	
                /**
                 * 如果不用CookieJar,那么就要自己去解析返回的Set-Cookie字段,解析之后通过addHeader("Cookie", cookie)
                 * 添加Cookie请求头
                 */
//              List<String> cookies =  response.headers("Set-Cookie");
//              String cookie = "";
//              for(int i=cookies.size()-1; i>=0; i--){
//                  cookie = cookie+ cookies.get(i).replace("path=/", "") + " ";
//              }
                
                //做GET请求
                Request redirectRequest = new Request.Builder().url("http://ehall.jhun.edu.cn/hqfw/sys/emapcomponent/file/uploadTempFile.do")
//                    .addHeader("Cookie", cookie)
                		.addHeader("Cookie", "route=967d6b0b007ff44d6755831c427b718b;CASTGC=TGT-16136-ndaRsFf3UF1XbEwWzi0Sf1jd2ZTxMVJ10XnVynUoOQLSAGWMrj1555036488007-gctD-cas;")
                		.post(requestBody.build())
                      .build();
                //拿到登陆后操作的某个网页的内容
                Response response2 = client.newCall(redirectRequest).execute();
                System.out.println(response2.code());
                String result = response2.body().string();
                System.out.println(result);
            }
            
            @Override
            public void onFailure(Call arg0, IOException arg1) {
                
            }
        });
        return "";
	}
}
