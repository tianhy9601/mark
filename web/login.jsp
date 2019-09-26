<%@ page import="java.net.URLDecoder" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<head lang="en">
    <meta charset="UTF-8">
    <title>系统登录 - 超市账单管理系统</title>
    <link rel="stylesheet" href="css/style.css"/>
    <script type="text/javascript">
        function changeImg() {
            document.getElementById("pp").src="ImageServlet.do?time="+new Date();
        }
    </script>
</head>
<body class="login_bg">
<%

    String username="";
    String password="";
    Cookie[] cookies=request.getCookies();
    if(cookies!=null){
        for (Cookie c:cookies){
            String name=c.getName();
            if(name.equals("cookie_name")){
                username= URLDecoder.decode(c.getValue(),"UTF-8");
            }
            if (name.equals("cookie_pass")){
                password=URLDecoder.decode(c.getValue(),"UTF-8");
            }
        }
    }

%>
<section class="loginBox">
    <header class="loginHeader">
        <h1>超市账单管理系统</h1>
    </header>
    <section class="loginCont">
        <form class="loginForm" action="/User.do" method="post">
            <div class="inputbox">
                <label for="user">用户名：</label>
                <input id="user" type="text" name="username" placeholder="请输入用户名" value="<%=username%>" required/>
            </div>
            <div class="inputbox">
                <label for="mima">密码：</label>
                <input id="mima" type="password" name="password" placeholder="请输入密码" value="<%=password%>" required/>
                <input type="hidden" name="action" value="login">
            </div>
            <div class="inputbox">
                <label for="yanzheng">验证码：</label>
                <input id="yanzheng" type="text" name="code" placeholder="请输入验证码" required/>
                <img src="/ImageServlet.do" style="cursor: pointer" id="pp" onclick="changeImg();">
            </div>
            <div class="subBtn">
                <input type="submit" value="登录" />
                <input type="reset" value="重置"/>
            </div>

        </form>
    </section>
</section>

</body>
</html>
