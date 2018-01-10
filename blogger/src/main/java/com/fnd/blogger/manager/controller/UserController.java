package com.fnd.blogger.manager.controller;

import com.fnd.blogger.manager.entity.User;
import com.fnd.blogger.manager.service.UserService;
import com.fnd.blogger.manager.utils.rsaUtils.RSAUtil;
import com.fnd.blogger.pojo.CommonResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.aspectj.util.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.List;

@Api(value = "blogger/user" , description = "用户管理相关api")
@Controller
@RequestMapping("blogger/user")
public class UserController {
    @Autowired
    private UserService userService;

    @ApiOperation(value = "用户登录")
    @RequestMapping(value = "/login",method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<CommonResponse<String>> login(HttpServletRequest request,@RequestParam("username") @ApiParam(value = "用户名", required = true) String userName, @RequestParam("Despassword") @ApiParam(value = "密码", required = true) String Despassword,@ApiParam(value = "验证码", required = true) String verifyCode) throws Exception {
        HttpSession session=request.getSession();
        String sessionVerifyCode=(String)session.getAttribute("verifyCode");
        if(!verifyCode.equalsIgnoreCase(sessionVerifyCode)){
            return CommonResponse.buildRespose4Fail("验证码输入错误","操作失败").toResponseEntity();
        }
        File privateFile = new File("src\\main\\resources\\privateKey.txt");
        String privateKey= FileUtil.readAsString(privateFile);
        String password=RSAUtil.decryptDataOnJava(Despassword,privateKey);
        String token=userService.login(userName,password);
        File publicFile = new File("src\\main\\resources\\publicKey.txt");
        String publicKey= FileUtil.readAsString(publicFile);
        String desToken=RSAUtil.encryptedDataOnJava(token,publicKey);
        return CommonResponse.buildRespose4Success(desToken,"操作成功").toResponseEntity();
    }

    @ApiOperation(value = "用户登录1")
    @RequestMapping(value = "/loging",method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<CommonResponse<String>> loging(HttpServletRequest request,@RequestParam("username") @ApiParam(value = "用户名", required = true) String userName, @RequestParam("password") @ApiParam(value = "密码", required = true) String password) throws Exception {
        String token=userService.login(userName,password);
        File publicFile = new File("src\\main\\resources\\publicKey.txt");
        String publicKey= FileUtil.readAsString(publicFile);
        String desToken=RSAUtil.encryptedDataOnJava(token,publicKey);
        return CommonResponse.buildRespose4Success(desToken,"操作成功").toResponseEntity();
    }
}
