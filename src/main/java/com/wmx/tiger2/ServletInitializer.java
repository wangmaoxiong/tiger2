package com.wmx.tiger2;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

/**
 * 自定义类继承 SpringBootServletInitializer，自定义类名字可以自己随便取
 * 作用是应用启动时，会自动执行 configure 方法，从而初始化 Spring Boot的 Servlet
 *
 * @author wangmaoxiong
 */
public class ServletInitializer extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        //传入SpringBoot应用的主程序
        return application.sources(Tiger2Application.class);
    }

}
