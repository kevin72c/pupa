package com.cmy.redis;

public enum JedisEnum {

    dict("dict:");
    
    String prefix;

    private JedisEnum(String prefix) {
        this.prefix = prefix;
    }
    
    @Override
    public String toString() {
        return prefix;
    }
    
    
}
