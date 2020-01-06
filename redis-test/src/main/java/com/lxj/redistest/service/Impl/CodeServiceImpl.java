package com.lxj.redistest.service.Impl;

import com.lxj.redistest.config.CodeConfig;
import com.lxj.redistest.dto.VerifyCodeParam;
import com.lxj.redistest.service.CodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class CodeServiceImpl implements CodeService {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    /** 1 手机号，获取验证码，2分钟有效
     *  2 输入验证码，点击验证，返回成功失败
     *  3 每个手机号每天只能发送3次验证
     */
    @Override
    public String getCode(String iphoneNo) {
        String phoneKey = CodeConfig.PHONE_PREFIX+iphoneNo+CodeConfig.PHONE_SUFFIX;
        String code  = generationCode();
        String countKey = CodeConfig.PHONE_PREFIX+iphoneNo+CodeConfig.COUNT_SUFFIX;
        String countValue = stringRedisTemplate.opsForValue().get(countKey);
        if(countValue == null)
        {
            stringRedisTemplate.opsForValue().set(countKey,"1");
            stringRedisTemplate.expire(countKey,CodeConfig.ONE_DAY,TimeUnit.SECONDS);
        }
        else if(countValue.equals("3"))
        {
            return "limit-3";
        }
        else
        {
            stringRedisTemplate.opsForValue().increment(countKey);
        }

        //写入redis
        stringRedisTemplate.opsForValue().set(phoneKey,code);
        stringRedisTemplate.expire(phoneKey,CodeConfig.TWO_MINUTE, TimeUnit.SECONDS);
        return code;
    }

    @Override
    public String verifyCode(VerifyCodeParam codeParam) {
        if(codeParam.getCode() == null || codeParam.getIphoneNo() == null)
        {
            return "empty";
        }
        //redis读出key
        String phoneKey = CodeConfig.PHONE_PREFIX+codeParam.getIphoneNo()+CodeConfig.PHONE_SUFFIX;
        String code = stringRedisTemplate.opsForValue().get(phoneKey);
        if(codeParam.getCode().equals(code))
        {
            return "true";
        }

        return "false";
    }

    public String generationCode()
    {
        return "888888";
    }
}
