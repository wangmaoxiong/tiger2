package com.wmx.tiger2.servlet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Enumeration;

/**
 * @author wangmaoxiong
 */
public class HomeServlet extends HttpServlet {

    private static final Logger logger = LoggerFactory.getLogger(HomeServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //javax.servlet.GenericServlet 类中已经实现了 getServletConfig 方法，继承了直接调用即可获取ServletConfig 对象
        ServletConfig servletConfig = this.getServletConfig();
        String encoding = servletConfig.getInitParameter("encoding");

        Enumeration<String> enumeration = servletConfig.getInitParameterNames();
        while (enumeration.hasMoreElements()) {
            logger.debug(enumeration.nextElement());
        }

        logger.debug("encoding=" + encoding);
        logger.debug("servletName=" + servletConfig.getServletName());

        ServletContext servletContext = getServletContext();
        //获取服务器真实物理路径
        String realPath = servletContext.getRealPath("/");
        logger.debug("realPath=" + realPath);
        //设置应用级别属性，用于所有用户共享
        servletContext.setAttribute("rootEmail", "2468461xxx@pp.com");

        //重定向到首页
        response.sendRedirect(request.getContextPath() + "/index.jsp");
    }
}
