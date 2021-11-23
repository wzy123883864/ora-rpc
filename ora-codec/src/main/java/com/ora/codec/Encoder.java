package com.ora.codec;

/**
 * @author wzy
 * @version 1.0
 * @description: 序列化
 * @date 2021/11/18 8:56
 */
public interface Encoder {
    /**
     * 序列化
     * @param obj 序列化对象
     * @return
     */
    byte[] encode(Object obj);
}
