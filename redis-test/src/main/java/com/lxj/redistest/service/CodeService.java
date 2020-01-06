package com.lxj.redistest.service;

import com.lxj.redistest.dto.VerifyCodeParam;

public interface CodeService {
    String getCode(String iphoneNo);

    String verifyCode(VerifyCodeParam codeParam);
}
