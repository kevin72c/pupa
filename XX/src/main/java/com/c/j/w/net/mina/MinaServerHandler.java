package com.c.j.w.net.mina;

import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IoSession;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 简单的消息处理器
 * 
 * @author Kevin
 *
 */
public class MinaServerHandler extends IoHandlerAdapter {

    private AtomicInteger count = new AtomicInteger(0);

    /**
     * 当一个客户端连接进入时
     */
    @Override
    public void sessionOpened(IoSession session) throws Exception {
        System.out.println("client connection : " + session.getRemoteAddress());
    }

    /**
     * 当一个客户端关闭时
     */
    @Override
    public void sessionClosed(IoSession session) throws Exception {
        System.out.println("client disconnection : " + session.getRemoteAddress() + " is Disconnection");
    }

    /**
     * 当接收到客户端的信息
     *
     * @param session
     * @param message
     * @throws Exception
     */
    @Override
    public void messageReceived(IoSession session, Object message) throws Exception {

        String str = (String) message;

        // 打印客户端
        System.out.println("receive client message : [ " + str + " ]");

        // 回写消息给客户端
        session.write(count.incrementAndGet());

    }

}