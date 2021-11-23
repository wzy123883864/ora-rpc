package com.ora.transport;


import lombok.extern.slf4j.Slf4j;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

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
public class HttpJettyTransportServer implements TransportServer{
    private RequestHandler handler;
    private Server server;
    @Override
    public void init(int port, RequestHandler handler) {
        this.handler=handler;
        this.server=new Server(port);

        //servlet接受请求
        ServletContextHandler ctx = new ServletContextHandler();
        server.setHandler(ctx);

        ServletHolder holder = new ServletHolder(new RequestServlet());
        ctx.addServlet(holder,"/*");
    }

    @Override
    public void start() {
        try {
            server.start();
            server.join();;
            log.info("jetty启动中");
        } catch (Exception e) {
            log.error(e.getMessage(),e);
        }

    }

    @Override
    public void stop() {
        try {
            server.stop();
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
