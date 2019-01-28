package com.group2.banking.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.concurrent.*;
/**
 *
 * @author Jing
 */
public class SessionManagement {
	private static ConcurrentHashMap<String,ConcurrentHashMap<String,Object>> sessions = null;
    public static void update(HttpServletRequest request, String key, String value)
    {
        HttpSession session = request.getSession();
	session.removeAttribute(key);
        session.setAttribute(key, value);
	//setting session to expiry in 3 mins
	session.setMaxInactiveInterval(3*60);
    }
    
    public static String check(HttpServletRequest request, String key)
    {
        HttpSession session = request.getSession();
        Object obj = session.getAttribute(key);
        if (obj == null)    return "";
        else    return obj.toString();
    }
    
    public static void addCookie(HttpServletRequest request, HttpServletResponse response, String userId)
    {
        Cookie loginCookie = new Cookie("user",userId);
	//setting cookie to expiry in 100 days
	loginCookie.setMaxAge(100*24*60*60);
	response.addCookie(loginCookie);
    }
    
    public static boolean checkCookie(HttpServletRequest request, String userId)
    {
        Cookie[] cookies = request.getCookies();
        if(cookies !=null){
            for(Cookie cookie : cookies){
                if(cookie.getName().equals("user") && userId.equals(cookie.getValue())) return true;
            }
        }
        return false;
    }
    
}
