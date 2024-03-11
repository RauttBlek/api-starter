package com.ink.apistarter.client;

import cn.hutool.core.util.RandomUtil;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONUtil;
import com.ink.apistarter.utils.SignUtil;

import java.util.HashMap;


public class CallApiClient {

    private final String accessKey;
    private final String secretKey;
    private final String token;

    public CallApiClient(String accessKey, String secretKey, String token) {
        this.accessKey = accessKey;
        this.secretKey = secretKey;
        this.token = token;
    }


    public String getPoisonByGet(String path) {
        //可以单独传入http参数，这样参数会自动做URL编码，拼接在URL中
        HashMap<String, Object> paramMap = getParamMap(token);
        String url = "http://localhost:8090" + path;
        return HttpUtil.get(url, paramMap);
    }

    public String getPoisonByPost(String path) {
        HashMap<String, Object> paramMap = getParamMap(token);
        String url = "http://localhost:8090" + path;
        return HttpUtil.post(url, paramMap);
    }

    private HashMap<String, Object> getParamMap(String token) {
        HashMap<String, Object> paramMap = new HashMap<>();
        paramMap.put("format", "json");
        paramMap.put("token", token);
        paramMap.put("accessKey", accessKey);
        paramMap.put("nonce", RandomUtil.randomNumbers(4));
        paramMap.put("timestamp", String.valueOf(System.currentTimeMillis() / 1000));

        String jsonStr = JSONUtil.toJsonStr(paramMap);
        String sign = SignUtil.genSign(jsonStr, secretKey);
        paramMap.put("sign", sign);
        return paramMap;
    }
}
