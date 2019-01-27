package com.jj0327.practice.base;

import lombok.extern.java.Log;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Log
public class BaseController {

    /**
     * 保存信息到request中
     *
     * @param key
     * @param value
     */
    public void setRequestAttribute(String key, Object value) {
        this.getRequest().setAttribute(key, value);
    }

    protected String getCookie(String cookieName) {
        Cookie[] cookies = getRequest().getCookies();
        for (Cookie cookie : cookies) {
            if (cookieName.equals(cookie.getName())) {
                return cookie.getValue();
            }
        }

        return null;
    }

    /**
     * 设置 cookie
     *
     * @param cookieName
     * @param value
     * @param age
     */
    protected void setCookie(String cookieName, String value, int age) {
        Cookie cookie = new Cookie(cookieName, value);
        cookie.setMaxAge(age);
        // cookie.setHttpOnly(true);
        getResponse().addCookie(cookie);
    }


    public HttpServletRequest getRequest() {
        return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
    }

    public HttpSession getSession() {
        return getRequest().getSession();
    }

    public HttpServletResponse getResponse() {
        return  ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();
    }
}
