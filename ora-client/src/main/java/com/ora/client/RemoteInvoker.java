package com.ora.client;

import com.ora.Request;
import com.ora.Response;
import com.ora.ServiceDescriptor;
import com.ora.codec.Decoder;
import com.ora.codec.Encoder;
import com.ora.transport.TransportClient;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @author wzy
 * @version 1.0
 * @description: 调用远程服务的代理类
 * @date 2021/11/18 14:04
 */
@Slf4j
public class RemoteInvoker implements InvocationHandler {

    private Class clazz;
    private Encoder encoder;
    private Decoder decoder;
    private TransportSelector selector;

    RemoteInvoker(Class clazz, Encoder encoder, Decoder decoder,
                  TransportSelector selector){
        this.clazz = clazz;
        this.decoder = decoder;
        this.encoder = encoder;
        this.selector = selector;
    }
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Request request = new Request();
        request.setService(ServiceDescriptor.form(clazz,method));
        request.setParameters(args);

        Response resp = invokeRemote(request);
        if (resp == null || resp.getCode() != 0){
            throw new IllegalStateException("fail to invoke remote:" + resp.getMessage());
        }
        return resp.getData();
    }

    public Response invokeRemote(Request request) {
        Response resp = null;
        TransportClient client = null;
        try {
            client = selector.select();
            byte[] outBytes = encoder.encode(request);
            InputStream revice = client.write(new ByteArrayInputStream(outBytes));

            byte[] inBytes = IOUtils.toByteArray(revice);
            resp = decoder.decode(inBytes, Response.class);
            return resp;
        } catch (IOException e) {
            log.warn(e.getMessage(),e);
            resp = new Response();
            resp.setCode(1);
            resp.setMessage("RpcClient got erroe:" + e.getClass()+":"+e.getMessage());
            return resp;
        } finally {
            if(client != null){
                selector.release(client);
            }
        }
    }
}
