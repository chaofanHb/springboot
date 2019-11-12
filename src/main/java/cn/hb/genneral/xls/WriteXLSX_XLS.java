package cn.hb.genneral.xls;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/7/27.
 */
public class WriteXLSX_XLS {
    @Test
    public void testWriteXLSX() throws InvalidFormatException, IOException {
        File file = new File("E:/testxlsx.xlsx");
        if (file.exists()) {
            file.delete();
        }
        UserWriter writer = new UserWriter(file);

        User user1 = new User("admin", "110.0", "管理员");
        User user2 = new User("user1", "108.5", "用户1");
        User user3 = new User("user2", "1000001564632000000000000.00", "用户2");

        writer.Write(user1);
        writer.Write(user2);
        writer.Write(user3);
        writer.Extract();
    }
    @Test
    public void testBatchWriteXLS() throws InvalidFormatException, IOException {
        File file = new File("E:/testxls.xls");
        if(file.exists()) {
            file.delete();
        }
        UserWriter writer = new UserWriter(file);

        User user1 = new User("admin", "110.0", "管理员");
       System.out.println(user1.toString());
        User user2 = new User("user1", "108.5", "用户1");
        User user3 = new User("user2", "2000.00", "用户2");

        List<User> users = new ArrayList<User>();

        users.add(user1);
        users.add(user2);
        users.add(user3);

        writer.Write(users);
        writer.Extract();
    }
}
