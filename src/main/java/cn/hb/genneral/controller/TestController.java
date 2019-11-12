package cn.hb.genneral.controller;

import cn.hb.genneral.dto.Upload;
import cn.hb.genneral.redis.RedisService;
import cn.hb.genneral.threadSession.RestAuthUtils;
import cn.hb.genneral.threadSession.Session;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.apache.http.HttpResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/7/21.
 */
@Controller
public class TestController {
	final String server = "http://authserver.jhun.edu.cn/authserver/login";
	private final String username = "HL0010000";
	private final String password = "888888";

    @Autowired
    private RedisService redisService;

   /* @Autowired
   db.coll.group({
    {key : {a:true, b:true}},
     cond: {active : 1},
     reduce : function(obj, prev) {
                prev.csum += obj.c
              },
     initial : {csum : 0}
})
    private UserMapper userMapper;*/

    /*@Autowired
    private User1Mapper user1Mapper;
    @Autowired
    private User2Mapper user2Mapper;*/
    @Autowired
    private SSOLogin sSOLogin;
    @Autowired
    private MyOkHttp myOkHttp;
    
    @RequestMapping("/myUpload")
    @ResponseBody
    public String upload(Upload upload, HttpServletRequest request) throws Exception {
    	System.out.println(upload.toString());
    	Map<String, Object> map = new HashMap<>();
    	map.put("scope", upload.getScope());
    	map.put("fileToken", upload.getFileToken());
    	map.put("size", upload.getSize());
    	map.put("type", upload.getType());
    	map.put("storeId", upload.getStoreId());
    	map.put("isSingle", upload.getIsSingle());
    	map.put("fileName", upload.getFileName());
    	
    	MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
		Iterator<String> iter = multiRequest.getFileNames();
		System.out.println();
		while (iter.hasNext()) {
			MultipartFile file = multiRequest.getFile((String) iter.next());
			System.out.println(file.getSize());
			String temp = sSOLogin.getTicketGrantingTicket(server, username, password);
			return sSOLogin.requestUpload("http://authserver.jhun.edu.cn/authserver/login?service=http://ehall.jhun.edu.cn/hqfw/sys/emapcomponent/file/uploadTempFile.do", temp, file);
			}
		
        return "";
    }
    
    @RequestMapping("/mytest")
    @ResponseBody
    public String toCascader(HttpServletResponse response, String id) throws Exception {
    	sSOLogin.getFile(response, id);
    	
        return "";
    }

    @RequestMapping("/toCascader")
    public String toCascader(HttpServletRequest request) {
    	System.out.println(request.getUserPrincipal().getName());
    	//User user = user1Mapper.getOne(1l);
        return "cascader";
    }
    
    @RequestMapping("/uploadServlet")
    @ResponseBody
    public String upload(HttpServletRequest request) {
    	 //MultipartHttpServletRequest mRequest = (MultipartHttpServletRequest) request;//request强制转换注意
    	 
        return "cascader";
    }

    //多级下拉菜单
    @RequestMapping("/cascader")
    @ResponseBody
    public Map<String , Object> getDate() throws JsonProcessingException {
        Map<String , Object> rsData = new HashMap<>();
        List<Map<String , Object>> data = new ArrayList<>();
        Map<String , Object> rs1 = new  HashMap<>();
        Map<String , Object> rs1_rs1 = new  HashMap<>();
        Map<String , Object> rs1_rs2 = new  HashMap<>();
        Map<String , Object> rs1_rs1_rs1 = new  HashMap<>();
        Map<String , Object> rs1_rs1_rs2 = new  HashMap<>();
        List<Map<String , Object>> temp1 = new ArrayList<>();
        List<Map<String , Object>> temp2 = new ArrayList<>();
        List<Map<String , Object>> temp3 = new ArrayList<>();
        Map<String , Object> rs2 = new  HashMap<>();
        Map<String , Object> rs2_rs1 = new  HashMap<>();
        rs1.put("id", 1);
        rs1.put("label", "安徽省");
        rs1.put("hasChildren", true);
        rs1.put("disabled", true);
        rs1_rs1.put("id", 2);
        rs1_rs1.put("label", "马鞍山市");
        rs1_rs1.put("disabled", true);

        rs1_rs1_rs1.put("id", 3);
        rs1_rs1_rs1.put("label", "和县");
        rs1_rs1_rs2.put("id", 4);
        rs1_rs1_rs2.put("label", "花山区");
        temp2.add(rs1_rs1_rs1);
        temp2.add(rs1_rs1_rs2);
        rs1_rs1.put("children", temp2);

        temp1.add(rs1_rs1);
        //rs1.put("children", temp1);

        rs2.put("id", 5);
        rs2.put("label", "河南省");
        rs2.put("hasChildren", true);
        rs2_rs1.put("id", 6);
        rs2_rs1.put("label", "郑州市");
        temp3.add(rs2_rs1);
        //rs2.put("children", temp3);

        data.add(rs1);
        data.add(rs2);
        rsData.put("data", data);
        rsData.put("code", 0);
        rsData.put("Msg", "success");
        return rsData;
    }

    //多级下拉菜单
    @RequestMapping("/cascader/getData")
    @ResponseBody
    public List<Map<String , Object>> getDate(@RequestParam Integer id){
        List<Map<String , Object>> rs = new ArrayList<>();
        if(id == 1) {
            Map<String , Object> rs1_rs1 = new  HashMap<>();
            rs1_rs1.put("id", 2);
            rs1_rs1.put("label", "马鞍山市");
            rs1_rs1.put("disabled", true);
            rs1_rs1.put("hasChildren", true);
            rs.add(rs1_rs1);
        }else if(id == 2) {
            Map<String , Object> rs1_rs1_rs1 = new  HashMap<>();
            Map<String , Object> rs1_rs1_rs2 = new  HashMap<>();
            rs1_rs1_rs1.put("id", 3);
            rs1_rs1_rs1.put("label", "和县");
            rs1_rs1_rs1.put("hasChildren", false);
            rs1_rs1_rs2.put("id", 4);
            rs1_rs1_rs2.put("label", "花山区");
            rs1_rs1_rs2.put("hasChildren", false);
            rs.add(rs1_rs1_rs2);
            rs.add(rs1_rs1_rs1);
        }else if(id == 5) {
            Map<String , Object> rs2_rs1 = new  HashMap<>();
            rs2_rs1.put("id", 6);
            rs2_rs1.put("label", "郑州市");
            rs2_rs1.put("hasChildren",  false);
            rs.add(rs2_rs1);
        }
        return rs;
    }

    @RequestMapping(value = "/index")
    public String index(){
        return "webscokt2";
    }

    @RequestMapping(value = "/hybrid")
    public String hybrid(){
        return "hybrid";
    }

    @ModelAttribute
    public void mod(@RequestParam(value = "company", defaultValue = "张三") String company, Model model) {
        model.addAttribute("company", company);
    }

    @RequestMapping(value = "/get")
    @ResponseBody
    public String get() {
        return redisService.get("faji");
    }

    @RequestMapping(value = "/set")
    @ResponseBody
    public String set() {
        redisService.set("faji", "99999999999999999999999");
        return "OK";
    }

    @RequestMapping(value = "/haha")
    @ResponseBody
    public String getAll(Model model, HttpServletRequest request) {
        //model.addAttribute("users",userMapper.getAll());
       /* List list = new ArrayList<User>();
        User user = user1Mapper.getOne(Long.valueOf("110"));
        if (user == null) {
            user =user2Mapper.getOne(Long.valueOf("110"));
        }
        list.add(user);
        model.addAttribute("users", list);*/
        //System.out.println(request.getScheme()+"//"+request.getServerName()+":"+request.getServerPort());
        ObjectMapper ob=new ObjectMapper();
        String str="result=";
        try {
            str+= ob.writeValueAsString(System.getenv());
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return str;
    }

    @RequestMapping(value = "/getUser")
    public String get(Model model, String name, boolean test) {
        System.out.println(test);
        //model.addAttribute("company",userMapper.get(name).getCompany());
        return "user";
    }

//    @RequestMapping(value = "/save")
//    public String save(Model model, Credit credit) {
//        // userMapper.save(credit);
//        return "update";
//    }
//
//    @RequestMapping(value = "/update")
//    @ResponseBody
//    public String update(Model model, Credit credit,HttpServletRequest request) {
//        //userMapper.update(credit);
//        Session.getSession();
//        return "delete";
//    }
//
//    @RequestMapping(value = "/delete")
//    @ResponseBody
//    public String delete(Model model, Credit credit) throws ParseException {
//        //userMapper.delete(credit.getUserId());
//        Session s=new Session();
//        s.setDate(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse("2017-01-21 12:12:12"));
//        s.setTimestamp(1111111111111l);
//        RestAuthUtils.setSession(s);
//        Session.getSession();
//        return "user";
//    }
}
