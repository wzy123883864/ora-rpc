package com.ora.codec;

import com.alibaba.fastjson.JSON;

/**
 * @author wzy
 * @version 1.0
 * @description: TODO
 * @date 2021/11/18 9:08
 */
public class JSONDecoder implements Decoder{

    /**
     * 反序列化
     *
     * @param bytes
     * @param clazz
     * @return
     */
    @Override
    public <T> T decode(byte[] bytes, Class<T> clazz) {
        return JSON.parseObject(bytes,clazz);
    }
}
