package com.cmy.desn.factory.multiMethod;

public class FactoryTest {

    public static void main(String[] args) {
        Sender sender = new SenderFactory().produceMailSender();
        sender.send();
    }
}
