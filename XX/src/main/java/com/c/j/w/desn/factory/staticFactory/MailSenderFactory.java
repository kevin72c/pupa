package com.c.j.w.desn.factory.staticFactory;

public class MailSenderFactory implements Provider {

    public Sender produce() {
        return new MailSender();
    }
    
}
