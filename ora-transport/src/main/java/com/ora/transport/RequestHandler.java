package com.ora.transport;

import java.io.InputStream;
import java.io.OutputStream;

/**
 * 处理网络请求的handler
 * @author 王志远
 */
public interface RequestHandler {
    void onRequest(InputStream recive, OutputStream toResp);
}
