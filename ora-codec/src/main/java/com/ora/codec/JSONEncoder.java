package com.ora.codec;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import java.nio.charset.StandardCharsets;

/**
 * @author wzy
 * @version 1.0
 * @description: json序列化
 * @date 2021/11/18 9:01
 */
public class JSONEncoder implements Encoder {

    @Override
    public byte[] encode(Object obj) {
        return JSON.toJSONBytes(obj);
    }
}
