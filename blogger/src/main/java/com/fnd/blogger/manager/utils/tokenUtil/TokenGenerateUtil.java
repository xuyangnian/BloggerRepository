package com.fnd.blogger.manager.utils.tokenUtil;


import com.fnd.blogger.constant.SystemConstant;
import com.fnd.blogger.manager.utils.RedisUtil;
import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.UUID;

@Component
public class TokenGenerateUtil {
    @Autowired
    private RedisUtil redisUtil;

    //用于外部调用生成Token字符串的静态方法
    public String getToken(){
        UUID uuid = UUID.randomUUID();
        String token = compressedUUID(uuid);
        //验证是否有相同的Token,为true重新生成
        while(!StringUtils.isEmpty(redisUtil.get(token))){
            uuid = UUID.randomUUID();
            token = compressedUUID(uuid);
        }
        return token;
    }

    //对UUID进行处理，形成想要的Token长度
    private  String compressedUUID(UUID uuid) {
        byte[] byUuid = new byte[SystemConstant.TOKEN_BYTE_LEN];
        long least = uuid.getLeastSignificantBits();
        long most = uuid.getMostSignificantBits();
        long2bytes(most, byUuid, 0);
        long2bytes(least, byUuid, SystemConstant.TOKEN_BYTE_LEN/2);
        String compressUUID = Base64.encodeBase64URLSafeString(byUuid);
        return compressUUID;
    }

    //长度处理
    private  void long2bytes(long value, byte[] bytes, int offset) {
        for (int i = SystemConstant.TOKEN_BYTE_LEN/2-1; i > -1; i--) {
            bytes[offset++] = (byte) ((value >> 8 * i) & 0xFF);
        }
    }

}
