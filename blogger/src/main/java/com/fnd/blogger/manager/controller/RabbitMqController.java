package com.fnd.blogger.manager.controller;

import com.fnd.blogger.manager.utils.rabbitmqUtils.Send;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Api(value = "blogger/rabbitMq",description = "rabbitMq管理模板")
@Controller
@RequestMapping("blogger/rabbitMq")
public class RabbitMqController {
    @Autowired
    private Send send;

    @ApiOperation("生产者发送消息")
    @RequestMapping(value = "/send",method = RequestMethod.GET)
    public void send(@RequestParam("content") @ApiParam(value = "content",required = true) String content){
        send.sendMsg(content);
    }
}
