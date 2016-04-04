package com.cmy.net.tcp;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class TCPClient {

    public static void main(String[] args) throws Exception {
        Socket socket = new Socket("127.0.0.1", 6666);
        
        InputStream in = socket.getInputStream();
        OutputStream out = socket.getOutputStream();
        out.write(new String("test").getBytes());
        System.out.println(in);
        out.close();
        socket.close();
    }
}
