<%@ page import="com.thy.Bean.User" %><%--
  Created by IntelliJ IDEA.
  User: tianhaoyu
  Date: 2019-08-19
  Time: 19:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    long id=0;
    User usersss=(User)session.getAttribute("user");
    if (usersss!=null){
        id=usersss.getId();
        System.out.println(id);
    }else{
        usersss=new User();
        out.print("<script>alert('登录失效，请先登录');location.href='login.jsp'</script>");
    }
%>
