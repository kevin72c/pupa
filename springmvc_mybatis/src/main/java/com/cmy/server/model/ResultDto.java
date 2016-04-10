package com.cmy.server.model;

public class ResultDto {

    private Integer status; // 请求状态
    private String message; // 消息提示
    private Object infobean; // 请求结果
//    private Long responseTime; // 响应时间

    public static int STATUS_SESSION_EXPIRE = 100; // 用户未登录
    public static int STATUS_SUCCESS = 200; // 操作成功
    public static int STATUS_FAILURE = 300; // 操作失败
    
    public ResultDto(Integer status, String message, Object infobean) {
        this.status = status;
        this.message = message;
        this.infobean = infobean;
//        this.responseTime = System.currentTimeMillis() /1000;
    }
    public ResultDto() {}
    
    public Object getInfobean() {
        return infobean;
    }

    public void setInfobean(Object infobean) {
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

//    public Long getResponseTime() {
//        return responseTime;
//    }
//
//    public void setResponseTime(Long responseTime) {
//        this.responseTime = responseTime;
//    }

}
