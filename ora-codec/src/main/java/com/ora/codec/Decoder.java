package com.ora.codec;

/**
 * 反序列化
 * @author ora
 */
public interface Decoder {
    /**
     * 反序列化
     * @param bytes
     * @param <T>
     * @return
     */
    <T> T decode(byte[] bytes,Class<T> clazz);
}
