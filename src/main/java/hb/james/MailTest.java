package hb.james;

import hb.New;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by Administrator on 2017/9/13.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = New.class)
public class MailTest {
    @Autowired
    private MailService mailService;
   /* @Autowired
    private TemplateEngine templateEngine;*/

    @Test
    public void sendSimple(){
        mailService.sendSimpleMail("hebin@mye.hk","测试邮件","这是一封测试邮件！！！");
    }
    @Test
    public void sendHtmlMail(){
        String content="<html>" +
                "<body>" +
                "    <h3>hello world ! 这是一封Html邮件!</h3>" +
                "</body>" +
                "</html>";
        //969752912@qq.com
        mailService.sendHtmlMail("hebin@mye.hk","测试邮件",content);
    }
    @Test
    public void sendAttachmentsMail(){
        String content="<html>" +
                "<body>" +
                "    <h3>hello world ! 这是一封Html邮件!</h3>" +
                "</body>" +
                "</html>";
        mailService.sendAttachmentsMail("lisi@mail.com","测试邮件",content,"E:\\file\\template.xls");
    }
    @Test
    public void sendInlineResourceMail(){
        String rscId = "1";
        String content="<html><body>这是有图片的邮件：<img src=\'cid:" + rscId + "\' ></body></html>";
        String imgPath = "E:\\file\\Koala.jpg";
        mailService.sendInlineResourceMail("lisi@mail.com", "主题：这是有图片的邮件", content, imgPath, rscId);
    }

  /*  @Test
    public void sendTemplateMail() {
        //创建邮件正文
        Context context = new Context();
        context.setVariable("id", "006");
        String emailContent = templateEngine.process("emailTemplate", context);

        mailService.sendHtmlMail("lisi@mail.com","主题：这是模板邮件",emailContent);
    }*/

}
