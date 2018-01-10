package com.fnd.blogger.manager.controller;

import com.fnd.blogger.manager.entity.User;
import com.fnd.blogger.manager.utils.RedisUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Api(value = "blogger/redis" , description = "redis缓存相关api")
@Controller
@RequestMapping("blogger/redis")
public class RedisController {
    @Autowired
    private RedisUtil redisUtil;
    /**设置key和value到redis*/
    @ApiOperation(value = "往redis存储数据")
    @RequestMapping(value = "/set", method = RequestMethod.GET)
    @ResponseBody
    public String set(@RequestParam("key") @ApiParam(value = "key", required = true) String key, @RequestParam("value") @ApiParam(value = "value", required = true) String value) throws Exception{
        redisUtil.set(key, value, 10);
        return "success";
    }
    /**根据key获取value*/
    @ApiOperation(value = "根据key获取value")
    @RequestMapping(value = "/get", method = RequestMethod.GET)
    @ResponseBody
    public String get(@RequestParam("key") @ApiParam(value = "key", required = true) String key) throws Exception {
        return redisUtil.get(key);
    }

    /**设置object对象到redis*/
    @ApiOperation(value = "设置object对象到redis")
    @RequestMapping(value = "/setObj", method = RequestMethod.POST)
    @ResponseBody
    public String setObj(@RequestBody User user) throws Exception {
        redisUtil.setObj(user);
        return "success";
    }
    /**根据key获取Object对象*/
    @ApiOperation(value = "根据key获取Object对象")
    @RequestMapping(value = "/getObj", method = RequestMethod.POST)
    @ResponseBody
    public Object getObj(@RequestBody User user) throws Exception {
        return redisUtil.getObj(user,User.class);
    }
    /**根据key获取set集合*/
    @ApiOperation(value = "获取用户")
    @RequestMapping(value = "/setSet", method = RequestMethod.GET)
    @ResponseBody
    public String setSet(@RequestParam("id") @ApiParam(value = "用户id", required = true) String key) throws Exception {
        Set<String> set = new HashSet<String>();
        set.add("a");
        set.add("b");
        set.add("c");
        redisUtil.setSetCollections(key,set);
        return "success";
    }

   /* *//**根据key获取set集合*//*
    @RequestMapping("/getSet")
    @ResponseBody
    public String getSet(String key) throws Exception {
        String resultStr = redisUtil.getSetCollections(key);
        List<String> setList = GsonUtils.chanageList(resultStr, List.class);
        Set<String> set = new HashSet<String>(setList);
        return resultStr+"-------"+GsonUtils.changeStr(set);
    }
    *//**设置map集合到redis*//*
    @RequestMapping("/setMap")
    @ResponseBody
    public String setMap(String key) throws Exception {
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("a","hanson");
        map.put("b","loly");
        map.put("c","zhangsan");
        redisUtil.setMapCollections(key,map);
        return "success";
    }
    *//**根据key获取map集合*//*
    @RequestMapping("/getMap")
    @ResponseBody
    public String getMap(String key) throws Exception {
        String resultStr = redisUtil.getMapCollections(key);
        Set<String> keySets = redisUtil.getMapKeys(key);
        Map<String,Object> setList = GsonUtils.chanageList(resultStr, Map.class);

        return resultStr+"-------"+GsonUtils.changeStr(setList)+"---------"+GsonUtils.changeStr(keySets);
    }
    *//**设置list集合到redis*//*
    @RequestMapping("/setList")
    @ResponseBody
    public String setList(String key) throws Exception {
        List<String> stringList = new ArrayList<String>();
        stringList.add("d");
        stringList.add("e");
        stringList.add("f");
        redisUtil.setLists(key,stringList);
        return "success";
    }
    *//**获取key对应的list集合*//*
    @RequestMapping("/getList")
    @ResponseBody
    public String getList(String key) throws Exception {
        String resultStr = redisUtil.getListStartEnd(key,0,-1);
        List<String> setList = GsonUtils.chanageList(resultStr, List.class);
        return resultStr+"------ ----"+GsonUtils.changeStr(setList);
    }
    *//**删除key*//*
    @RequestMapping("/del")
    @ResponseBody
    public String del(String key) throws Exception {
        redisUtil.del(key);
        return "被删除的key："+redisUtil.get(key);
    }
    @RequestMapping("/expire")
    @ResponseBody
    *//**查询key的剩余存活时间*//*
    public Response getKeyExpireTime(String key){
        long expireTime = redisUtil.getKeyExpireTime(key);
        return  Response.ok(200,key+"剩余存活时间："+expireTime);
    }
    @RequestMapping("/exitsKey")
    @ResponseBody
    *//**判断key是否存在*//*
    public Response exitsKey(String key){
        boolean exitsFlag = redisUtil.exitsKey(key);
        return  Response.ok(200,key+"是否存在："+exitsFlag);
    }

    @RequestMapping("/setKeyExpireTime")
    @ResponseBody
    *//**判断key是否存在*//*
    public Response setKeyExpireTime(String key,int time){
        long beforeExpireTime = redisUtil.getKeyExpireTime(key);
        redisUtil.setKeyExpireTime(key,time);
        long afterExpireTime = redisUtil.getKeyExpireTime(key);
        return  Response.ok(200,key+"设置之前剩余时间："+beforeExpireTime+"----设置之后剩余时间："+afterExpireTime);
    }*/
}
