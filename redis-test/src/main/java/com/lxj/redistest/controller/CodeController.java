package com.lxj.redistest.controller;

import com.lxj.redistest.dto.VerifyCodeParam;
import com.lxj.redistest.service.CodeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class CodeController {
    @Autowired
    private CodeService codeService;
    private static final Logger LOGGER = LoggerFactory.getLogger(CodeController.class);

    @RequestMapping(value = "/getCode/{iphoneNo}", method = RequestMethod.GET)
    public String getCode(@PathVariable("iphoneNo") String iphoneNo)
    {
        if(iphoneNo == null)
        {
            return "PhoneNo is empty,failed";
        }
        return codeService.getCode(iphoneNo);
    }

    @RequestMapping(value = "/verifyCode", method = RequestMethod.POST)
    public String verifyCode(@RequestBody VerifyCodeParam codeParam)
    {
        return codeService.verifyCode(codeParam);
    }

    @RequestMapping(value = "/test", method = RequestMethod.POST)
    public String test()
    {
        return "test";
    }
}
