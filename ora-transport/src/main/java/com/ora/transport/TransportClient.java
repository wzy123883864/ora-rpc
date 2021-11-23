package com.ora.transport;

import com.ora.Peer;

import java.io.InputStream;

/**
 * @author wzy
 * @version 1.0
 * @description:
 * 1、创建连接
 * 2、发送数据，并且等待响应
 * 3、关闭连接
 * @date 2021/11/18 9:29
 */
public interface TransportClient {
    void connect(Peer peer);
    InputStream write(InputStream data);
    void close();
}
