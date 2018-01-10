package com.fnd.blogger.manager.controller;


import com.fnd.blogger.manager.utils.MailUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Api(value = "blogger/mail",description = "发送邮件")
@Controller
@RequestMapping(value = "blogger/mail")
public class MailController {
    @Autowired
    private MailUtil mailUtil;

    @ApiOperation("发送普通邮件")
    @RequestMapping(value = "/sendSimpleMail",method = RequestMethod.GET)
    public void sendSimpleMail(@RequestParam(value = "to") @ApiParam(value = "to",required = true) String to,@RequestParam(value = "subject") @ApiParam(value = "subject",required = true) String subject){
        String content="你好";
        mailUtil.sendSimpleMail(to,subject,"你好");
    }

    @ApiOperation("发送html邮件")
    @RequestMapping(value = "/sendHtmlMail",method = RequestMethod.GET)
    public void sendHtmlMail(@RequestParam(value = "to") @ApiParam(value = "to",required = true) String to,@RequestParam(value = "subject") @ApiParam(value = "subject",required = true) String subject){
        String content="<html>\n" +
                "<body>\n" +
                "    <h3>hello world ! 这是一封Html邮件!</h3>\n" +
                "</body>\n" +
                "</html>";
        mailUtil.sendHtmlMail(to,subject,content);
    }

    @ApiOperation("发送带附件的邮件邮件")
    @RequestMapping(value = "/sendAttachmentsMail",method = RequestMethod.GET)
    public void sendAttachmentsMail(@RequestParam(value = "to") @ApiParam(value = "to",required = true) String to,@RequestParam(value = "subject") @ApiParam(value = "subject",required = true) String subject){
        String filePath="src\\main\\resources\\privateKey.txt";
        mailUtil.sendAttachmentsMail(to,subject,"附件",filePath);
    }

    @ApiOperation("发送带静态资源的邮件")
    @RequestMapping(value = "/sendInlineResourceMail",method = RequestMethod.GET)
    public void sendInlineResourceMail(@RequestParam(value = "to") @ApiParam(value = "to",required = true) String to,@RequestParam(value = "subject") @ApiParam(value = "subject",required = true) String subject){
        String rscId = "neo006";
        String content="<html><body>这是有图片的邮件：<img src=\'cid:" + rscId + "\' ></body></html>";
        String imgPath = "C:\\Users\\Administrator\\Desktop\\如意logo.jpg";
        mailUtil.sendInlineResourceMail(to,subject,content,imgPath,rscId);
    }
}
