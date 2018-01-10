package com.fnd.blogger.manager.controller;

import com.fnd.blogger.manager.utils.rsaUtils.RSAUtil;
import com.fnd.blogger.pojo.CommonResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.aspectj.util.FileUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.*;
import java.util.Map;

@Api(value = "blogger/rsa" , description = "密钥对相关api")
@Controller
@RequestMapping("blogger/rsa")
public class RSAUtilController {

    @ApiOperation(value = "生成私钥公钥")
    @RequestMapping(value = "/generateKeyPair",method = RequestMethod.GET)
    public void generateKeyPair() throws Exception {
        Map<String, Object> map= RSAUtil.genKeyPair();
        String publicKey=RSAUtil.getPublicKey(map);
        String privateKey=RSAUtil.getPrivateKey(map);
        String publicPath=this.getClass().getResource("/publicKey.txt").getPath();
        String privatePath=this.getClass().getResource("/privateKey.txt").getPath();
        File publicFile = new File("src\\main\\resources\\publicKey.txt");
        FileUtil.writeAsString(publicFile,publicKey);
        File privateFile = new File("src\\main\\resources\\privateKey.txt");
        FileUtil.writeAsString(privateFile,privateKey);
        PrintWriter pw1 = new PrintWriter(new FileWriter(publicPath));
        pw1.print(publicKey);
        pw1.close();
        PrintWriter pw2 = new PrintWriter( new FileWriter(privatePath) );
        pw2.print(privateKey);
        pw2.close();

    }

    @ApiOperation(value = "获取公钥")
    @RequestMapping(value = "/getPublicKey",method = RequestMethod.GET)
    public ResponseEntity<CommonResponse<String>> getPublicKey() throws IOException {
        File publicFile = new File("src\\main\\resources\\publicKey.txt");
        String publicKey=FileUtil.readAsString(publicFile);
        return CommonResponse.buildRespose4Success(publicKey,"操作成功").toResponseEntity();
    }

    @ApiOperation(value = "获取密码")
    @RequestMapping(value = "/getpassword",method = RequestMethod.GET)
    public ResponseEntity<CommonResponse<String>> getpassword() throws Exception {
        File publicFile = new File("src\\main\\resources\\publicKey.txt");
        String publicKey=FileUtil.readAsString(publicFile);
        File privateFile = new File("src\\main\\resources\\privateKey.txt");
        String privateKey=FileUtil.readAsString(privateFile);
        String password1=RSAUtil.encryptedDataOnJava("1",publicKey);
        String password2=RSAUtil.decryptDataOnJava(password1,privateKey);
        return CommonResponse.buildRespose4Success(password2,"操作成功").toResponseEntity();
    }
}
