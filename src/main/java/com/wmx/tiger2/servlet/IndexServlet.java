package com.wmx.tiger2.servlet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author wangmaoxiong
 */
public class IndexServlet extends HttpServlet {
    private static final Logger logger = LoggerFactory.getLogger(IndexServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取应用级属性值，没有时返回 null
        ServletContext servletContext = this.getServletContext();
        Object rootEmail = servletContext.getAttribute("rootEmail");
        logger.debug("rootEmail=" + rootEmail);
        //操作 web.xml 中 <context-param> 标签中的应用级参数
        logger.debug("adminPhone=" + servletContext.getInitParameter("adminPhone"));

        //服务器端跳转到首页
        request.getRequestDispatcher("/index.jsp").forward(request, response);
    }
}
