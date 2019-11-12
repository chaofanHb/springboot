package cn.spring.RabitMQ;

/**
 * Created by cn.hb on 2018/8/21.
 */
public class IApiCoreDto {
    private String moduleName;
    private String sourceIp;
    private String totalDate;
    private Integer totalHour;
    private String uri;
    private Integer callCnt;

    public String getModuleName() {
        return moduleName;
    }

    public void setModuleName(String moduleName) {
        this.moduleName = moduleName;
    }

    public String getSourceIp() {
        return sourceIp;
    }

    public void setSourceIp(String sourceIp) {
        this.sourceIp = sourceIp;
    }

    public String getTotalDate() {
        return totalDate;
    }

    public void setTotalDate(String totalDate) {
        this.totalDate = totalDate;
    }

    public Integer getTotalHour() {
        return totalHour;
    }

    public void setTotalHour(Integer totalHour) {
        this.totalHour = totalHour;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public Integer getCallCnt() {
        return callCnt;
    }

    public void setCallCnt(Integer callCnt) {
        this.callCnt = callCnt;
    }
}
