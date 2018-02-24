package Cons.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.DBObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/8/23.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class GroupUser {
    private Logger logger = LoggerFactory.getLogger(GroupUser.class);
    @JsonProperty(value = "company")
    private String company;
    @JsonProperty(value = "isum")
    private Double isum;


    public List<GroupUser> parseGroupUser(DBObject dbObject) {
        Map map = dbObject.toMap();
        List<GroupUser> list = new ArrayList<GroupUser>();
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            for (Object object : map.values()) {
                GroupUser user = objectMapper.readValue(object.toString(), GroupUser.class);
                list.add(user);
            }
        } catch (IOException e) {
            logger.info("groupUser实体类转换失败：{}", e.getMessage());
            e.printStackTrace();
        }
        list.sort(new Comparator<GroupUser>() {
            @Override
            public int compare(GroupUser o1, GroupUser o2) {
                return o1.getIsum().compareTo(o2.getIsum());
            }
        });
        for (GroupUser groupUser : list) {
            System.out.println(groupUser.getCompany() + "\t" + groupUser.getIsum());
        }
        return list;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public Double getIsum() {
        return isum;
    }

    public void setIsum(Double isum) {
        this.isum = isum;
    }

}