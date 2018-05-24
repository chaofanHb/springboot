package Cons.webscokt.version2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Hebin on 2018/5/23.
 */
@RestController
public class Controller {

    @Autowired
    WebSocket websocket;

    @RequestMapping("/send/{fromUser}/{msg}")
    public String send(@PathVariable("msg") String msg,@PathVariable("fromUser") String fromUser) {
        websocket.sendMessageTopic(msg,fromUser,2);
        return "测试成功";
    }

    @RequestMapping("/send/{fromUser}/{toUser}/{msg}")
    public String sendtoUser(@PathVariable("fromUser") String fromUser,@PathVariable("toUser") String toUser,@PathVariable("msg") String msg) {
        websocket.sendMessageToUser(msg,fromUser,toUser);
        return "测试成功";
    }

    @RequestMapping("/getOnLineUser")
    public String getOnLineUser(){
        return websocket.getOnLineUser();
    }
}