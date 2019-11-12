package cn.hb.genneral.controller;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.ProtocolException;
import org.apache.http.client.RedirectHandler;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.cookie.Cookie;
import org.apache.http.entity.InputStreamEntity;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpParams;
import org.apache.http.protocol.HTTP;
import org.apache.http.protocol.HttpContext;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import cn.hb.genneral.dto.Upload;

@Service
public class SSOLogin {
	final String server = "http://authserver.jhun.edu.cn/authserver/login";
	private String username = "HL0010000";
	private String password = "888888";
	
	private String castgc = "";
	
	private DefaultHttpClient httpclient = new DefaultHttpClient();
	
	private String temp = null;
 
	public String ssologin() throws Exception {
		//execute();
		return "success";
	}
	
	public static void main(String[] args) throws Exception {
		SSOLogin ss= new SSOLogin();
		String temp = ss.requestUpload("","", null);
	}
	
	public String requestUpload(String url, String temp, MultipartFile file) throws Exception {
		
		//http://authserver.jhun.edu.cn/authserver/login?service=http://ehall.jhun.edu.cn/hqfw/sys/emapcomponent/file/uploadTempFile.do
		HttpPost post = new HttpPost(url);
		System.err.println(temp);
		post.setHeader("Cookie", temp);
		/*InputStreamEntity reqEntity = new InputStreamEntity(file.getInputStream());
		reqEntity.setContentType("binary/octet-stream");
        reqEntity.setChunked(true);
        post.setEntity(reqEntity);*/
		
		httpclient.setRedirectHandler(new RedirectHandler() {
			private String location = null;
			@Override
			public boolean isRedirectRequested(HttpResponse arg0, HttpContext arg1) {
				// TODO Auto-generated method stub
				/*if(arg0.getStatusLine().getStatusCode() ==302) {
					location= arg0.getHeaders("Location")[0].getValue();
					System.out.println(arg0.getStatusLine().getStatusCode());
					return true;
				}*/
				return false;
			}
			
			@Override
			public URI getLocationURI(HttpResponse arg0, HttpContext arg1)
					throws ProtocolException {
				// TODO Auto-generated method stub
				Header[] headers = arg0.getAllHeaders();
				for(int i =0; i<headers.length; i++) {
					System.out.println(headers[i].getName()+"="+headers[i].getValue());
				}
				try {
				      InputStream is = arg0.getEntity().getContent();
				      BufferedReader br = new BufferedReader(new InputStreamReader(is));
				      String line = "";
				      while((line = br.readLine())!=null){
				          System.out.println("----"+line);
				      }
				      
					return new URI(location);
				} catch (URISyntaxException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (UnsupportedOperationException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return null;
			}
		});
		
		if("http://ehall.jhun.edu.cn/hqfw/sys/emapcomponent/file/uploadTempFile.do".equals(url)) {
			File tempFile = new File("F:\\"+file.getOriginalFilename());
			file.transferTo(tempFile);
			FileBody bin = new FileBody(tempFile);
			StringBody scope = new StringBody("155503687442745");
			StringBody fileToken = new StringBody("1555036874427451");
			StringBody size = new StringBody("0");
			StringBody type = new StringBody("jpg,jpeg,png");
			StringBody storeId = new StringBody("image");
			StringBody isSingle = new StringBody("0");
			StringBody fileName = new StringBody("");
			
			
			MultipartEntity reqEntity = new MultipartEntity();
			reqEntity.addPart("files",bin);
			reqEntity.addPart("scope", scope);
			reqEntity.addPart("fileToken", fileToken);
			reqEntity.addPart("size", size);
			reqEntity.addPart("type", type);
			reqEntity.addPart("storeId", storeId);
			reqEntity.addPart("isSingle", isSingle);
			reqEntity.addPart("fileName", fileName);
			post.setEntity(reqEntity);
		}
		HttpResponse response1 = httpclient.execute(post);

		StringBuffer sb = new StringBuffer();
		System.out.println(response1.getStatusLine().getStatusCode());
	      InputStream is = response1.getEntity().getContent();
	      BufferedReader br = new BufferedReader(new InputStreamReader(is));
	      String line = "";
	      while((line = br.readLine())!=null){
	          System.out.println("----"+line);
	          sb.append(line);
	      }
	      
			if(response1.getStatusLine().getStatusCode() == 302) {
				String location = response1.getHeaders("Location")[0].getValue();
				//post.abort();
				return requestUpload(location,temp, file);
				
			}
			
		return sb.toString();
	}	
	
//	@Override
	public String getFile(HttpServletResponse response, String id) throws Exception {
			try {
				String temp = getTicketGrantingTicket(server, username, password);
				System.err.println(temp);
				DefaultHttpClient myHttpclient = new DefaultHttpClient();
				HttpGet post = new HttpGet("http://ehall.jhun.edu.cn/hqfw/sys/emapcomponent/file/getTempFile/156533595005325/1565335950053251/"+id+".do");
				post.setHeader("Cookie", temp);
				
				HttpResponse response1 = httpclient.execute(post);
				System.out.println(response1.getStatusLine().getStatusCode());
				BufferedReader reader = new BufferedReader(new InputStreamReader(response1.getEntity().getContent()));
				
				Header[] headers = response1.getAllHeaders();
				for(int i =0; i<headers.length; i++) {
					System.out.println(headers[i].getName()+"="+headers[i].getValue());
				}
				response.addHeader("Content-Type", "image/*");
				response.addHeader("Content-Disposition", "attachment; filename=\"201904091601737.png\"; filename*=UTF-8''201904091601737.png");
				
				
				InputStream  in = response1.getEntity().getContent();
				OutputStream out = response.getOutputStream();
				byte[] buffer = new byte[4096];
	            int i = 0;
				while ((i=	in.read(buffer)) != -1) {
					out.write(buffer, 0, i);
				}
				out.flush();
				in.close();
				//inputStreamToFile(response1.getEntity().getContent(), "C:\\Users\\chaofan\\Desktop\\my.jpg");
			} catch (Exception e) {
				e.printStackTrace();
			}
			return "SUCCESS";
	}
	public static void inputStreamToFile(InputStream is, String fileName) throws IOException {
		 OutputStream outputStream=null;
		File file = new File(fileName);
		outputStream = new FileOutputStream(file);
		 int bytesWritten = 0;
		 int byteCount = 0;
		 byte[] bytes = new byte[1024];
		 while ((byteCount = is.read(bytes)) != -1){
			 System.out.println(byteCount);
			 outputStream.write(bytes);
		 }
		 is.close();
		 outputStream.close();
	}
 
	public String getTicketGrantingTicket(final String server,final String username, final String password) throws Exception {
		
		String rs = "";
		
		if(this.temp != null) {
			return temp;
		}
		
		HttpPost post = new HttpPost(server);
//		post.setHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8");
//		post.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.3; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/69.0.3497.100 Safari/537.36");
//		post.setHeader("Host", "authserver.jhun.edu.cn");
//		post.setHeader("Connection", "keep-alive");
//		post.setHeader("Upgrade-Insecure-Requests", "1");
//		post.setHeader("", "");
		
		List<NameValuePair> nvps = new ArrayList <NameValuePair>();
		
		nvps.add(new BasicNameValuePair("username", username));
		nvps.add(new BasicNameValuePair("password", password));
		String[] temp =  doCasLoginRequest(httpclient, server);
		System.out.println(temp[1]+"+++++++++++++++++++++++++++++++++++++++++++++");
		rs +=temp[1];
		rs +=";";
		nvps.add(new BasicNameValuePair("lt", temp[0]));
		nvps.add(new BasicNameValuePair("execution", "e1s1"));
		nvps.add(new BasicNameValuePair("_eventId", "submit"));
		nvps.add(new BasicNameValuePair("submit", "登录"));
		
		post.setEntity(new UrlEncodedFormEntity(nvps, HTTP.UTF_8));  
		try {
			HttpResponse response = httpclient.execute(post);
			HttpEntity entity = response.getEntity();
			Header[] headers = response.getAllHeaders();
			for(int i =0; i<headers.length; i++) {
				System.out.println(headers[i].getName()+"="+headers[i].getValue());
			}
			System.out.println("===============================================");
/*		      //输出返回值
		      InputStream is = response.getEntity().getContent();
		      BufferedReader br = new BufferedReader(new InputStreamReader(is));
		      String line = "";
		      while((line = br.readLine())!=null){
		          System.out.println("----"+line);
		      }
*/
		      
			if(entity!=null){
				Cookie cookieSessionid = getCookieValue(httpclient, "JSESSIONID");
				Cookie cookieCastgc = getCookieValue(httpclient, "CASTGC");
				entity.consumeContent();
				
				Cookie[] ca = {cookieSessionid, cookieCastgc};
				rs+= "CASTGC="+cookieCastgc.getValue();
				rs+=";";
				this.temp = rs;
				return rs;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
 
	
	private Cookie getCookieValue(DefaultHttpClient httpclient, String name) {
		List<Cookie> cookies = httpclient.getCookieStore().getCookies();
		if (cookies.isEmpty()) {
			return null;
		} else {
			for (int i = 0; i < cookies.size(); i++) {
				Cookie cookie = cookies.get(i);
				//System.out.println(cookie.getName()+"="+cookie.getValue());
				if (cookie.getName().equalsIgnoreCase(name)) {
					return cookie;
				}
			}
		}
		return null;
	}
	
	
	private String[] doCasLoginRequest(DefaultHttpClient httpclient, String url) throws IOException {
		
		try {
			List<Cookie> cookies = httpclient.getCookieStore().getCookies();
			for (int i = 0; i < cookies.size(); i++) {
				Cookie cookie = cookies.get(i);
				System.err.println(cookie.getName()+"="+cookie.getValue());
			}
			
		     String result[] = new String[2];  
		     HttpGet httpget = new HttpGet(url);  
		        HttpResponse response = httpclient.execute(httpget);
				Header[] headers = response.getAllHeaders();
				System.out.println("0000000000000000000000000000000000000000");
				for(int i =0; i<headers.length; i++) {					
					System.out.println(headers[i].getName()+"="+headers[i].getValue());	
				}
				System.out.println("0000000000000000000000000000000000000000");
		        
		        HttpEntity entity = response.getEntity();
		        BufferedReader rd = new BufferedReader(new InputStreamReader(entity.getContent(), "UTF-8"));  
		        String tempLine = rd.readLine();
		        String s = "<input type=\"hidden\" name=\"lt\" value=\"";  
		        while (tempLine != null){
		          int index = tempLine.indexOf(s);  
		             if(index != -1) {
		              String s1 = tempLine.substring(index + s.length());  
		             int index1 = s1.indexOf("\"");
		             if(index1 != -1)
		              result[0] = s1.substring(0, index1);  
		             }
		            tempLine = rd.readLine();
		        }
		        if (entity != null) {
		            entity.consumeContent();  
		        }
		        result[1] = response.getHeaders("Set-Cookie")[0].getValue();
		        
				List<Cookie> cookies1 = httpclient.getCookieStore().getCookies();
				for (int i = 0; i < cookies1.size(); i++) {
					Cookie cookie = cookies1.get(i);
					System.err.println(cookie.getName()+"="+cookie.getValue());
				}
		        
		        return result;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
		    }
 
 
	// CASTGC
	private javax.servlet.http.Cookie convertToServletCookie1(Cookie cookie){
		castgc = cookie.getValue();
		javax.servlet.http.Cookie retCookie = new javax.servlet.http.Cookie(cookie.getName(), cookie.getValue());
		
		retCookie.setComment(cookie.getComment());
		retCookie.setDomain(cookie.getDomain());
 
		retCookie.setSecure(true);
		retCookie.setPath(cookie.getPath());
		retCookie.setVersion(cookie.getVersion());
 
		return retCookie;
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
 
	public String getCastgc() {
		return castgc;
	}
 
	public void setCastgc(String castgc) {
		this.castgc = castgc;
	}
}
