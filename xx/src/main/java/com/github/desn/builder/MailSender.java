package com.github.desn.builder;

public class MailSender implements Sender {

    @Override
    public void send() {
        System.out.println("Mail send");
    }

}
