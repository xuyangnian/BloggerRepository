package com.fnd.blogger.manager.controller;

import com.fnd.blogger.manager.entity.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.io.*;

@Api(value = "blogger/SerializableController", description = "序列化测试")
@Controller
@RequestMapping(value = "blogger/SerializableController")
public class SerializableController  {
    @ApiOperation(value = "序列化写文件")
    @RequestMapping(value = "/serializable",method = RequestMethod.GET)
    public void serializable() throws IOException {
        User user=new User();
        user.setName("xuyangnian");
        user.setPassword("123456");
        ObjectOutputStream os=new ObjectOutputStream(new FileOutputStream(new File("src\\main\\resources\\serializable.txt")));
        os.writeObject(user);
    }
}
