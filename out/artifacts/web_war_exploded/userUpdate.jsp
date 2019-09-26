<%@ page import="com.thy.Bean.User" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <title>超市账单管理系统</title>
    <link rel="stylesheet" href="css/public.css"/>
    <link rel="stylesheet" href="css/style.css"/>
    <script type="text/javascript">
        function logout() {
            if(confirm("确认退出吗？")){
                location.href='/User.do?action=logout';
            }
        }
    </script>
</head>
<body>
<%@include file="checklogin.jsp"%>
<%
    User user=(User)request.getAttribute("user");
%>
<!--头部-->
<header class="publicHeader">
    <h1>超市账单管理系统</h1>

    <div class="publicHeaderR">
        <p><span>下午好！</span><span style="color: #fff21b"> <%=usersss.getUserName()%></span> , 欢迎你！</p>
        <a href="javascript:logout();">退出</a>
    </div>
</header>
<!--时间-->
<section class="publicTime">
    <span id="time">2015年1月1日 11:11  星期一</span>
    <a href="#">温馨提示：为了能正常浏览，请使用高版本浏览器！（IE10+）</a>
</section>
<!--主体内容-->
<section class="publicMian ">
    <div class="left">
        <h2 class="leftH2"><span class="span1"></span>功能列表 <span></span></h2>
        <nav>
            <ul class="list">
                <li><a href="/BillController.do?action=TobillList">账单管理</a></li>
                <li ><a href="/Provider.do?action=ToproviderList">供应商管理</a></li>
                <li id="active"><a href="/User.do?action=TouserView">用户管理</a></li>
                <li><a href="/User.do?action=ToupdatePassword">密码修改</a></li>
                <li><a href="javascript:logout();">退出系统</a></li>
            </ul>
        </nav>
    </div>
    <div class="right">
        <div class="location">
            <strong>你现在所在的位置是:</strong>
            <span>用户管理页面 >> 用户修改页面</span>
        </div>
        <div class="providerAdd">
            <form action="/User.do">
                <!--div的class 为error是验证错误，ok是验证成功-->
                <div>
                    <label for="userName">用户名称：</label>
                    <input type="text" name="userName" id="userName" value="<%=user.getUserName()%>"/>
                    <input type="hidden" name="ids" value="<%=user.getId()%>">
                    <input type="hidden" name="action" value="updateUser">
                    <span >*</span>
                </div>

                <div>
                    <label >用户性别：</label>

                    <select name="sex">
                        <option value="男" <%="男".equals(user.getSex())?"selected":""%>>男</option>
                        <option value="女" <%="女".equals(user.getSex())?"selected":""%>>女</option>
                    </select>
                </div>
                <div>
                    <label for="data">出生日期：</label>
                    <input type="text" name="birthday" id="data" value="<%=user.getBirthday()%>"/>
                    <span >*</span>
                </div>
                <div>
                    <label for="userphone">用户电话：</label>
                    <input type="text" name="userphone" id="userphone" value="<%=user.getUserphone()%>"/>
                    <span >*</span>
                </div>
                <div>
                    <label for="userAddress">用户地址：</label>
                    <input type="text" name="userAddress" id="userAddress" value="<%=user.getUserAddress()%>"/>
                </div>
                <div>
                    <label >用户类别：</label>
                    <input type="radio" name="userlei" value="管理员" <%="管理员".equals(user.getUserlei())?"checked":""%>/>管理员
                    <input type="radio" name="userlei" value="经理" <%="经理".equals(user.getUserlei())?"checked":""%>/>经理
                    <input type="radio" name="userlei" value="普通用户" <%="普通用户".equals(user.getUserlei())?"checked":""%>/>普通用户

                </div>
                <div class="providerAddBtn">
                    <!--<a href="#">保存</a>-->
                    <!--<a href="userList.html">返回</a>-->
                    <input type="submit" value="保存" onclick="history.back(-1)"/>
                    <a href="/User.do?action=TouserView">返回</a>
                </div>
            </form>
        </div>

    </div>
</section>
<footer class="footer">
</footer>
<script src="js/time.js"></script>

</body>
</html>