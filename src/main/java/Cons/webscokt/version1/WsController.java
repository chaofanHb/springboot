package Cons.webscokt.version1;

import Cons.webscokt.version1.dto.RequestMessage;
import Cons.webscokt.version1.dto.ResponseMessage;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.annotation.SubscribeMapping;
import org.springframework.stereotype.Controller;

/**
 * Created by Hebin on 2018/5/24.
 */
@Controller
public class WsController {
    @MessageMapping("/welcome")
    @SendTo("/topic/getResponse")
    public ResponseMessage say(RequestMessage message) {
        System.out.println(message.getName());
        return new ResponseMessage("welcome," + message.getName() + " !");
    }

    @SubscribeMapping("/subscribeTest")
    public ResponseMessage sub() {
        System.out.println("XXX用户订阅了我。。。");
        return new ResponseMessage("感谢你订阅了我。。。");
    }
}