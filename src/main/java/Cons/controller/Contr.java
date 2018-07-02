package Cons.controller;

import Cons.dao.test1.User1Mapper;
import Cons.dao.test2.User2Mapper;
import Cons.entity.Credit;
import Cons.redis.RedisService;
import Cons.threadSession.RestAuthUtils;
import Cons.threadSession.Session;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * Created by Administrator on 2017/7/21.
 */
@Controller
public class Contr {

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

    @Autowired
    private User1Mapper user1Mapper;
    @Autowired
    private User2Mapper user2Mapper;

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

    @RequestMapping(value = "/save")
    public String save(Model model, Credit credit) {
        // userMapper.save(credit);
        return "update";
    }

    @RequestMapping(value = "/update")
    @ResponseBody
    public String update(Model model, Credit credit,HttpServletRequest request) {
        //userMapper.update(credit);
        Session.getSession();
        return "delete";
    }

    @RequestMapping(value = "/delete")
    @ResponseBody
    public String delete(Model model, Credit credit) throws ParseException {
        //userMapper.delete(credit.getUserId());
        Session s=new Session();
        s.setDate(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse("2017-01-21 12:12:12"));
        s.setTimestamp(1111111111111l);
        RestAuthUtils.setSession(s);
        Session.getSession();
        return "user";
    }
}
