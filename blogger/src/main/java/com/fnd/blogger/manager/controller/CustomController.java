package com.fnd.blogger.manager.controller;

import com.fnd.blogger.manager.entity.Custom;
import com.fnd.blogger.manager.repository.CustomeRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Api(value = "blogger/CustomController",description = "mongodb管理")
@Controller
@RequestMapping("blogger/CustomController")
public class CustomController {
    @Autowired
    private CustomeRepository customeRepository;

    @ApiOperation("mongodb保存")
    @RequestMapping(value = "/saveCustom",method = RequestMethod.POST)
    public void  saveCustom(Custom custom){
        customeRepository.save(custom);
    }

    @ApiOperation("mongodb查询")
    @RequestMapping(value = "/findCustom",method = RequestMethod.GET)
    @ResponseBody
    public Custom findCustom(@RequestParam(value = "id") @ApiParam(value = "id",required = true) String id){
        return customeRepository.findById(id);
    }

}
