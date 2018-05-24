package Cons.webscokt.version2;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by Hebin on 2018/5/23.
 */
@Component
@ServerEndpoint("/webSocket/{param}")
public class WebSocket {

    private static String ONLINE = "已上线";
    private static ConcurrentHashMap<String, Session> webSocktSession = new ConcurrentHashMap<>();

    @OnOpen
    public void onOpen(Session session, @PathParam(value="param") String param) {
        //上线提醒
        sendMessageTopic(ONLINE,param,1);

        webSocktSession.put(param,session);
        System.err.println("【websocket消息】 有新的连接，总数:"+webSocktSession.size());
    }

    @OnClose
    public void onClose(@PathParam(value="param") String param) {
        System.out.println(param);
        webSocktSession.remove(param);
        System.err.println("【websocket消息】 连接断开，总数:"+webSocktSession.size());
    }

    @OnMessage
    public void onMessage(String message,@PathParam(value="param") String param) {
        System.err.println("【websocket消息】收到客户端发来的消息"+message+param);
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            User user = objectMapper.readValue(message, User.class);
            if("all".equals(user.getUsername())){
                sendMessageTopic(message, param, 2);
            }else {
                sendMessageToUser(user.getMsg(), param, user.getUsername());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendMessageToUser(String message, String formUsername, String toUsername) {
        MsgDto msgDto=new MsgDto();
        User user =new User();
        user.setMsg(message);
        user.setUsername(formUsername);
        msgDto.setUser(user);
        msgDto.setType(2);
        ObjectMapper ob =new ObjectMapper();

        try {
            Session session=webSocktSession.get(toUsername);
            if(session !=null) {
                String json = ob.writeValueAsString(msgDto);
                System.out.println(json);
                session.getBasicRemote().sendText(json);
            }else {
                System.err.println("用户已离线：" + toUsername);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendMessageTopic(String message, String formUsername, int type) {
        try {
            MsgDto msgDto = new MsgDto();
            User user =new User();
            user.setMsg(message);
            user.setUsername(formUsername);
            msgDto.setUser(user);
            msgDto.setType(type);
            ObjectMapper ob =new ObjectMapper();
            String json = ob.writeValueAsString(msgDto);
        for(String name : webSocktSession.keySet()) {
            System.err.println("【websocket消息】广播消息,message=" + json);
            webSocktSession.get(name).getBasicRemote().sendText(json);
        }
        }catch(Exception e) {
            e.printStackTrace();
        }
    }

    public String getOnLineUser(){
        List<String> users = new ArrayList<>();
        return Optional.of(webSocktSession.keySet())
                .map(usernames -> {
                    usernames.forEach( username -> {
                               users.add(username);
                            }
                    );
                    String rs = null;
                    try {
                        ObjectMapper objectMapper = new ObjectMapper();
                        rs = objectMapper.writeValueAsString(users);
                    } catch (JsonProcessingException e) {
                        e.printStackTrace();
                    }
                    return rs;
                }).orElse(null);
    }
}
