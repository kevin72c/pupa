package com.c.j.w.cms.core.pojo;

public enum ResultStatus {

    // URL请求规范，version=1&deviceType=1,userID
    Success(200, "业务处理成功，付款与退款结果表示处理完成"),
    Waiting_For_Payment(210, "等待支付中"),
    Refunding(220, "退款处理中"),
    Payment_Code_Not_Set(230, "未设置支付密码"), // 拉起设置支付密码页面
    Failure(300, "业务处理失败"), // 提示message消息内容
    Security_Code_Required(450, "需要验证码"), // 需要填写图片验证码后请求
    Error(500, "系统异常"), // 显示系统异常占位图
    ServiceUnavailable(503 , "服务器维护"), // 提示message消息内容，内容可能会比较长
    /** 此时infobean返回
     * {version:0.0.1,
     *  title:"抬头",
     *  content:"更新内容",
     *  appName:"app名称",
     *  fileLength: "升级包文件大小",
     *  url:"升级url地址"} */
    VersionNotSupported(505, "App版本过期"),
    Session_Expire(1100, "会话超时"), // 拉起登录页面
    Authentication(1200, "实名认证"); // 拉起实名登记页面

    public final int code;
    public final String name;
    
    ResultStatus(int code, String name) {
        this.code = code;
        this.name = name;
    }
}
