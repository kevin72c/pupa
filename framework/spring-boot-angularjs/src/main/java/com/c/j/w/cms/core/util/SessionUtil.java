package com.c.j.w.cms.core.util;

import com.c.j.w.cms.core.pojo.User;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author chenjw
 * @Date 2016年06月27日
 */
public class SessionUtil {

    private static final String SessionUser = "sessionUser";

    /**
     * 取得当前登录用户
     * @return
     */
    public static User getSessionUser() {
        try {
            HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
                    .getRequest();
            return (User) request.getSession().getAttribute(SessionUser);
        } catch (Exception e) {
            throw new IllegalStateException("获取用户Session信息异常");
        }
    }

    public static void setSessionUser(User User) {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
                .getRequest();
        request.getSession().setAttribute(SessionUser, User);
    }

    public static void clearSession() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
                .getRequest();
        request.getSession().removeAttribute(SessionUser);
    }

}
