package com.fnd.blogger.manager.controller;

import com.fnd.blogger.manager.utils.VerifyCodeUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Api(value = "blogger/VerifyCodeUtilController" , description = "验证码相关api")
@Controller
@RequestMapping("blogger/VerifyCodeUtilController")
public class VerifyCodeUtilController {

    @ApiOperation(value = "获取验证码")
    @RequestMapping(value = "/getVerifyCode",method = RequestMethod.GET)
    public void getVerifyCode(HttpServletRequest request , HttpServletResponse response) throws IOException {
        //设置图像返回客户端类型
        response.setContentType("image/jpeg");
        ServletOutputStream os = response.getOutputStream();
        String verifyCode = VerifyCodeUtil.generateVerifyCode(4);
        HttpSession session=request.getSession();
        session.setAttribute("verifyCode",verifyCode);
        int w = 60, h = 23;
        VerifyCodeUtil.outputImage(w, h,os,verifyCode);
       /* File dir = new File("F:/verifies");
        int w = 200, h = 80;
        for (int i = 0; i < 50; i++) {
            String verifyCode = VerifyCodeUtil.generateVerifyCode(4);
            File file = new File(dir, verifyCode + ".jpg");
            VerifyCodeUtil.outputImage(w, h, file, verifyCode);
        }*/
    }
}
