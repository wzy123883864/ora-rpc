package com.ora;

import lombok.Data;

/**
 * @author wzy
 * @version 1.0
 * @description: 表示rpc的一个请求
 * @date 2021/11/17 17:04
 */
@Data
public class Request {
    private ServiceDescriptor service;
    private Object[] parameters;
}
