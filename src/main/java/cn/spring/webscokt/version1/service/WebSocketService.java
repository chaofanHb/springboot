package cn.spring.webscokt.version1.service;

/**
 * Created by Hebin on 2018/5/22.
 */
/*
@Service
public class WebSocketService {

    @Autowired
    private SimpMessagingTemplate template;

    */
/**
     * 广播
     * 发给所有在线用户
     *
     * @param msg
     *//*

    public void sendMsg(ResponseMessage msg) {
        template.convertAndSend("/topic/getResponse", msg);
    }

    */
/**
     * 发送给指定用户
     * @param users
     * @param msg
     *//*

    public void send2Users(List<String> users, ResponseMessage msg) {
        users.forEach(userName -> {
            template.convertAndSendToUser(userName, "/msg", msg);
        });
    }
}*/
