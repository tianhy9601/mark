package com.thy.Filter;

import com.thy.Bean.User;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LoginFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("登录过滤器初始化了。。。");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req=(HttpServletRequest)request;
        HttpServletResponse resp=(HttpServletResponse)response;
        User user=(User)req.getSession().getAttribute("user");
        if (user!=null){
            filterChain.doFilter(request,response);
        }else{
            String uri = req.getRequestURI();
            System.out.println("地址为："+uri);
            String action=req.getParameter("action");
            if(uri.contains("ImageServlet.do")||uri.contains("login.jsp")||uri.equals("/")||"login".equals(action)){
                filterChain.doFilter(request,response);
            }else{
                resp.getWriter().write("<script>alert('请先登录！');location.href='login.jsp'</script>");
            }


        }
    }

    @Override
    public void destroy() {
        System.out.println("登录过滤器销毁了了。。。");
    }
}
