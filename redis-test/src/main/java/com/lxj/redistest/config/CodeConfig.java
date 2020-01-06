package com.lxj.redistest.config;

import org.springframework.context.annotation.Configuration;

@Configuration
public class CodeConfig {
    public static String PHONE_PREFIX = "phoneno:";
    public static String PHONE_SUFFIX = ":code";
    public static String COUNT_SUFFIX = ":count";
    public static int CODE_LEN = 6;//验证码长度
    public static int ONE_DAY = 24 * 60 * 60;
    public static int COUNT_TIMES_1DAY = 3;
    public static int TWO_MINUTE = 60*2;
}
