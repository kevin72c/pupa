package com.c.j.w.net.tcp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class TCPServer {

    public static void main(String[] args) throws Exception {
        TCPServer tcpServer = new TCPServer();
        tcpServer.server();
    }
    
    public void server() {
        ServerSocket serverSocket;
        try {
            serverSocket = new ServerSocket(6666);
            while (true) {
                listen(serverSocket);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public void listen(ServerSocket serverSocket) {
        Socket accept;
        try {
            accept = serverSocket.accept();
            handle(accept);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public void handle(Socket accept) {
        InputStreamReader isr = null;
        BufferedReader br = null;
        try {
            isr = new InputStreamReader(accept.getInputStream());
            br = new BufferedReader(isr);
            String buffer;
            while ((buffer = br.readLine()) != null) {
                System.out.println(buffer);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (isr != null)
                    isr.close();
                if (br != null)
                    br.close();
            } catch (IOException e) {
            }
        }
    }
    
    
}
