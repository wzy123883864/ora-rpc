package com.ora.server;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.lang.reflect.Method;

/**
 * @author wzy
 * @version 1.0
 * @description: 表示一个具体服务
 * @date 2021/11/18 10:15
 */
@Data
@AllArgsConstructor
public class ServiceInstance {
    private Object target;
    private Method method;
}
