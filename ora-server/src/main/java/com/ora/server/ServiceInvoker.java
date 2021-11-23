package com.ora.server;

import com.ora.Request;
import com.ora.common.utils.ReflectionUtils;

/**
 * @author wzy
 * @version 1.0
 * @description: TODO
 * @date 2021/11/18 11:00
 */
public class ServiceInvoker {
    public Object invoke(ServiceInstance service, Request request){
        return ReflectionUtils.invoke(service.getTarget(),service.getMethod(),
                request.getParameters());
    }
}
