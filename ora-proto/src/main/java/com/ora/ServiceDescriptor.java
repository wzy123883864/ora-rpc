package com.ora;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.lang.reflect.Method;

/**
 * @author wzy
 * @version 1.0
 * @description: 表示服务
 * @date 2021/11/17 16:56
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ServiceDescriptor {
    private String clazz;
    private String method;
    private String returnType;
    private String[] paramterTypes;

    @Override
    public int hashCode() {
        return toString().hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj==null || getClass() != obj.getClass()) {
            return false;
        }
        ServiceDescriptor that = (ServiceDescriptor)obj;
        return this.toString().equals(that.toString());
    }

    public static ServiceDescriptor form(Class clazz, Method method){
        ServiceDescriptor sdp = new ServiceDescriptor();
        sdp.setClazz(clazz.getName());
        sdp.setMethod(method.getName());
        sdp.setReturnType(method.getReturnType().getName());

        Class<?>[] parameterClasses = method.getParameterTypes();
        String[] parameterTypes = new String[parameterClasses.length];
        for (int i = 0;i<parameterClasses.length;i++){
            parameterTypes[i] = parameterClasses[i].getName();
        }
        sdp.setParamterTypes(parameterTypes);
        return sdp;
    }


}
