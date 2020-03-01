package com.wmx.tiger2.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date;

/**
 * @author wangmaxoiong
 */
public class WorldServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String lastAccessTimeMessage = "-";

        //1）遍历所有 Cookie 对象，根据名称获取浏览器传来的 cookie 值。注意：如果未发送cookie，则此方法返回null.
        Cookie[] cookies = request.getCookies();
        if (cookies != null && cookies.length > 0) {
            for (Cookie cookie : cookies) {
                if ("lastAccessTime".equals(cookie.getName())) {
                    long lastAccessTime = Long.parseLong(cookie.getValue());
                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    lastAccessTimeMessage = dateFormat.format(new Date(lastAccessTime));
                    break;
                }
            }
        }
        //2）往浏览器发送 Cookie 值。名称一样时，值会自动覆盖.
        Cookie lastAccessTimeCookie = new Cookie("lastAccessTime", Instant.now().toEpochMilli() + "");
        //2.1、设置本 Cookie 失效时间为 30 分钟，30分钟内，即使用于关闭浏览器也不会影响下一次传值进来，因为浏览器会存储到磁盘
        //相反默认未设置失效时间时，默认浏览器关闭时，Cookie 就被清除了，因为只是被存储在浏览器缓存中，只在当前会话内有效
        //cookie.setMaxAge(Integer.MAX_VALUE) 设置超时时间为无限大
        //cookie.setMaxAge(0)：0 表示删除 Cookie
        lastAccessTimeCookie.setMaxAge(30 * 60);

        //2.2、设置Cookie适用的路径，如果不指定路径，默认浏览器会将Cookie返回给当前目录及其子孙目录，以及同级目录及其子孙目录。
        //如下所示表示当前请求下设置的 lastAccessTimeCookie，整个应用下的其它所有请求路径都能获取到 lastAccessTimeCookie 值.
        lastAccessTimeCookie.setPath(request.getContextPath());

        //设置的 Cookie 最好必须发送给客户端才能生效
        response.addCookie(lastAccessTimeCookie);

        //3）服务器端跳转前，设置传递的请求参数用于页面取值.
        request.setAttribute("lastAccessTime", lastAccessTimeMessage);

        request.getRequestDispatcher("WEB-INF/pages/hello/world.jsp").forward(request, response);
    }
}
