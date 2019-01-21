package hb.RabitMQ;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by hb on 2018/8/21.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class LastLoginDto {
    @JsonProperty(value = "Event_Category")
    private String eventCategory;

    @JsonProperty(value = "Event_Name")
    private String eventName;

    private String user;
    @JsonProperty(value = "login_time")
    private String loginTime;

    private String ip;
    @JsonProperty(value = "user_agent")
    private String userAgent;

    public String getEventCategory() {
        return eventCategory;
    }

    public void setEventCategory(String eventCategory) {
        this.eventCategory = eventCategory;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(String loginTime) {
        this.loginTime = loginTime;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getUserAgent() {
        return userAgent;
    }

    public void setUserAgent(String userAgent) {
        this.userAgent = userAgent;
    }
}
