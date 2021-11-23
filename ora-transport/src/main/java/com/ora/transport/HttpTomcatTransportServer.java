package com.ora.transport;


import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.Context;
import org.apache.catalina.connector.Connector;
import org.apache.catalina.startup.Tomcat;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * @author wzy
 * @version 1.0
 * @description: TODO
 * @date 2021/11/18 9:52
 */
@Slf4j
public class HttpTomcatTransportServer implements TransportServer{
    private RequestHandler handler;

    private Tomcat tomcat;
    @Override
    public void init(int port, RequestHandler handler) {
        this.handler=handler;
        this.tomcat=new Tomcat();
        //2.构建Connector对象(连接器),负责协议配置,端口配置等
        Connector conn=new Connector("HTTP/1.1");
        conn.setPort(port);
        tomcat.getService().addConnector(conn);
        RequestServlet requestServlet = new RequestServlet();
        //注册servlet
        Context ctx=tomcat.addContext("/",null);
        tomcat.addServlet("/","helloServlet",requestServlet);
               //映射servlet
        ctx.addServletMappingDecoded("/", "helloServlet");
        //servlet接受请求

    }

    @Override
    public void start() {
        try {
            tomcat.start();
            tomcat.getServer().await();;
            log.info("tomcat启动中");
        } catch (Exception e) {
            log.error(e.getMessage(),e);
        }

    }

    @Override
    public void stop() {
        try {
            tomcat.stop();
        } catch (Exception e) {
            log.error(e.getMessage(),e);
        }
    }

    class RequestServlet extends HttpServlet {

        @Override
        protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
            log.info("client connect");
            InputStream inputStream = req.getInputStream();
            OutputStream outputStream = resp.getOutputStream();

            if(handler!=null){
                handler.onRequest(inputStream,outputStream);
            }
            outputStream.flush();
        }
    }
}
