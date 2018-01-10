package com.fnd.blogger.constant;

import org.springframework.stereotype.Component;

@Component
public class SystemConstant {
    //Token过期时间
    public static final int TOKENTIMEOUT=1;
    //用于生成的Token长度
    public static final int TOKEN_BYTE_LEN=16;
}
