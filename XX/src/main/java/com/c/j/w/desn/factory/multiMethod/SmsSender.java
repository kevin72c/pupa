package com.c.j.w.desn.factory.multiMethod;

public class SmsSender implements Sender {

    @Override
    public void send() {
        System.out.println("Sms send");
    }

}
