package com.cmy.pojo;

import java.io.Serializable;

public class ResultDto<E> implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Integer status; // 请求状态
    private String message; // 消息提示
    private E infobean; // 请求结果

    public static int STATUS_SESSION_EXPIRE = 100; // 用户未登录
    public static int STATUS_SUCCESS = 200; // 操作成功
    public static int STATUS_FAILURE = 300; // 操作失败
    
    public ResultDto(Integer status, String message, E infobean) {
        this.status = status;
        this.message = message;
        this.infobean = infobean;
    }
    public ResultDto() {}
    
    public E getInfobean() {
        return infobean;
    }

    public void setInfobean(E infobean) {
        this.infobean = infobean;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
