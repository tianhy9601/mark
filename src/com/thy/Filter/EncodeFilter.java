package com.thy.Filter;

import javax.servlet.*;
import java.io.IOException;

public class EncodeFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("编码过滤器初始化了。。。");
    }

    @Override
    public void destroy() {
        System.out.println("编码过滤器销毁了。。。");
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain filterChain) throws IOException, ServletException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html;charset=utf-8");
        filterChain.doFilter(req,resp);
    }
}
