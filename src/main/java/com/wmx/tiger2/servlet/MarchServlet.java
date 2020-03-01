package com.wmx.tiger2.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;

/**
 * @author wangmaoxiong
 */
public class MarchServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1、返回与此请求关联的当前会话，或者如果该请求没有会话，则创建一个会话。
        HttpSession httpSession = request.getSession();
        String id = httpSession.getId();
        System.out.println("sessionId=" + id);
        //设置会话范围内属性，应用下任意地方在会话范围内当前会话用户都能取到值.
        httpSession.setAttribute("token", "9933118980UHL");
        //设置会话超时时间(秒)。表示用户此时间内没有访问本系统时，则会话失效。
        //只要用户访问本应用下的任意资源都是可以的，都能维持会话. 当用户关闭浏览器后会话也会结束。下一次会是一次新的会话.
        httpSession.setMaxInactiveInterval(60);

        //2、默认情况下，HttpSession在浏览器关闭重开再次访问时，因为客户端没有了之前名称为"JSESSIONID"的 Cookie 而创建一个新的会话
        //所以如果想要用户在重开浏览器的情况下还能维持在一个会话内，就要让会话的"JSESSIONID" Cookie 不能随浏览器关闭而消失，必须写入磁盘
        Cookie cookie = new Cookie("JSESSIONID", httpSession.getId());
        cookie.setPath(request.getContextPath());
        cookie.setMaxAge(Integer.MAX_VALUE);
        response.addCookie(cookie);

        response.sendRedirect(request.getContextPath() + "/index.jsp");
    }
}
