package com.ink.apistarter.utils;

import cn.hutool.crypto.digest.DigestAlgorithm;
import cn.hutool.crypto.digest.Digester;

public class SignUtil {

    public static final String SALT = "salt";

    /**
     * 使用请求体与密钥生成签名
     *
     * @param body      请求体
     * @param secretKey 密钥
     * @return 签名
     */
    public static String genSign(String body, String secretKey) {
        Digester md5 = new Digester(DigestAlgorithm.MD5);
        String content = body + "." + secretKey + SALT;
        return md5.digestHex(content);
    }
}
