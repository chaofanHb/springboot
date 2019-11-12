package cn.spring.webscokt.version1.dto;

/**
 * Created by Hebin on 2018/5/15.
 */
public class ResponseMessage {
    private String responseMessage;

    public ResponseMessage(String responseMessage) {
        this.responseMessage = responseMessage;
    }

    public String getResponseMessage() {
        return responseMessage;
    }
}
