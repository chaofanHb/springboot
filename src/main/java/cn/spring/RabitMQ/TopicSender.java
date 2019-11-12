package cn.spring.RabitMQ;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by Administrator on 2017/8/2.
 */
@Component
public class TopicSender {

    @Autowired
    private AmqpTemplate rabbitTemplate;

    public void send1() {
        String context = "hi, i am message 1";
        System.out.println("Sender : " + context);

        IApiCoreDto iApiCoreDto = new IApiCoreDto();
        iApiCoreDto.setCallCnt(10);
        iApiCoreDto.setModuleName("cn.com");
        iApiCoreDto.setSourceIp("127.0.0.1");
        iApiCoreDto.setTotalDate("2017-05-06");
        iApiCoreDto.setTotalHour(1);
        iApiCoreDto.setUri("/health");
        ObjectMapper objectMapper = new ObjectMapper();
        String p = null;
        try {
            p = objectMapper.writeValueAsString(iApiCoreDto);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        this.rabbitTemplate.convertAndSend("BACKEND_EXCHANGE", "api_core_total", p);
    }

    public void send2() {
        String context = "hi, i am messages 2";
        System.out.println("Sender : " + context);

        LastLoginDto lastLoginDto = new LastLoginDto();
        lastLoginDto.setEventCategory("1");
        lastLoginDto.setEventName("2");
        lastLoginDto.setIp("127.0.0.1");
        lastLoginDto.setLoginTime("2017-05-02");
        lastLoginDto.setUser("hebin");
        lastLoginDto.setUserAgent("emp");
        String p = null;
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            p = objectMapper.writeValueAsString(lastLoginDto);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        this.rabbitTemplate.convertAndSend("MESSAGE_EXCHANGE", "SYSINFO", p);
    }
}
