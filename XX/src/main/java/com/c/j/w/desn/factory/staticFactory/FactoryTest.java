package com.c.j.w.desn.factory.staticFactory;

public class FactoryTest {

    public static void main(String[] args) {
        Provider provider = new SmsSenderFactory();
        Sender sender = provider.produce();
        sender.send();
    }
}
