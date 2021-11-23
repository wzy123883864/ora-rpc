package com.ora.server;

import com.ora.Request;
import com.ora.Response;
import com.ora.codec.Decoder;
import com.ora.codec.Encoder;
import com.ora.common.utils.ReflectionUtils;
import com.ora.transport.RequestHandler;
import com.ora.transport.TransportServer;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * @author wzy
 * @version 1.0
 * @description: TODO
 * @date 2021/11/18 11:01
 */
@Slf4j
public class RpcServer {
    private RpcServerConfig config;
    private TransportServer net;
    private Encoder encoder;
    private Decoder decoder;
    private ServiceManager serviceManager;
    private ServiceInvoker serviceInvoker;

    public RpcServer(){
        this(new RpcServerConfig());
    }
    public RpcServer(RpcServerConfig config){
        this.config = config;

        this.net = ReflectionUtils.newInstance(config.getTransportClass());
        this.net.init(config.getPort(),this.handler);
        this.encoder = ReflectionUtils.newInstance(config.getEncoderClass());
        this.decoder = ReflectionUtils.newInstance(config.getDncoderClass());
        this.serviceManager = new ServiceManager();
        this.serviceInvoker = new ServiceInvoker();
    }

    public <T> void register(Class<T> interfaceClass,Object bean){
        serviceManager.register(interfaceClass,bean);
    }

    public void start(){
        this.net.start();;
    }
    public void stop(){
        this.net.stop();
    }

    private RequestHandler handler = new RequestHandler() {
        @Override
        public void onRequest(InputStream recive, OutputStream toResp) {
            Response response = new Response();
            try {
                byte[] inBytes = IOUtils.toByteArray(recive);
                Request request = decoder.decode(inBytes, Request.class);

                log.info("get request : {}",request);
                ServiceInstance sis = serviceManager.lookup(request);
                Object ret = serviceInvoker.invoke(sis, request);

                response.setData(ret);
            } catch (Exception e) {
                log.warn(e.getMessage(),e);
                response.setCode(1);
                response.setMessage("RpcServer got error:" + e.getClass().getName()
                + ":" + e.getMessage());
            }finally {
                byte[] outBytes = encoder.encode(response);
                try {
                    toResp.write(outBytes);

                    log.info("Response client");
                } catch (IOException e) {
                    log.warn(e.getMessage(),e);
                }
            }

        }
    };
}
