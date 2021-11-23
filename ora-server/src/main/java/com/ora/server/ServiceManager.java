package com.ora.server;

import com.ora.Request;
import com.ora.ServiceDescriptor;
import com.ora.common.utils.ReflectionUtils;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Method;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author wzy
 * @version 1.0
 * @description: 管理rpc暴露的服务
 * @date 2021/11/18 10:17
 */
@Slf4j
public class ServiceManager {
    private Map<ServiceDescriptor,ServiceInstance> services;
    public ServiceManager(){
        this.services = new ConcurrentHashMap<>();
    }

    public <T> void register(Class<T> interfaceClass,Object bean){
        Method[] methods = ReflectionUtils.getPublicMethods(interfaceClass);
        for (Method method: methods) {
            ServiceInstance sis = new ServiceInstance(bean,method);
            ServiceDescriptor sdp = ServiceDescriptor.form(interfaceClass, method);
            services.put(sdp,sis);
            log.info("register service:{}{}",sdp.getClazz(),sdp.getMethod());
        }
    }

    public ServiceInstance lookup(Request request){
        ServiceDescriptor sdp = request.getService();
        return services.get(sdp);
    }
}
