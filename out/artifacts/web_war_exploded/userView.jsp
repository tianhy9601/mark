<%@ page import="java.util.List" %>
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
<script type="text/javascript">
    function deleteUser(ids) {
        if(confirm("确认是否删除该条数据?")){
            if (ids==id) {
                alert("当前用户正在使用，无法删除")
            }else{
                location.href='/User.do?action=deleteUser&ids='+ids+'&id='+<%=id%>;
            }
        }
    }

    function selectedUserInfos() {
        var inpu=document.getElementById("ccc");
        var vals=inpu.value;
        location.href='/User.do?action=selectedUserByName&uname='+vals+'&id='+<%=id%>;
    }
</script>
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
            <span>用户管理页面</span>
        </div>
        <div class="search">
            <span>用户名：</span>
            <input type="text" placeholder="请输入用户名" id="ccc"/>
            <input type="button" value="查询" onclick="selectedUserInfos()"/>
            <a href="/User.do?action=ToAddUser">添加用户</a>
        </div>
        <!--用户-->
        <table class="providerTable" cellpadding="0" cellspacing="0">
            <tr class="firstTr">
                <th width="10%">用户编码</th>
                <th width="20%">用户名称</th>
                <th width="10%">性别</th>
                <th width="10%">年龄</th>
                <th width="10%">电话</th>
                <th width="10%">用户类型</th>
                <th width="30%">操作</th>
            </tr>
            <%

                List<User> list=(List<User>)request.getAttribute("list");
                List<User> lists=(List<User>)request.getAttribute("lists");

                if(list!=null){
                        for (User user : list) {
                         %>

            <tr>
                <td><%=user.getId()%></td>
                <td><%=user.getUserName()%></td>
                <td><%=user.getSex()%></td>
                <td><%=user.getBirthday()%></td>
                <td><%=user.getUserphone()%></td>
                <td><%=user.getUserlei()%></td>
                <td>
                    <a href="/User.do?action=TouserList&ids=<%=user.getId()%>"><img src="img/read.png" alt="查看" title="查看"/></a>
                    <a href="/User.do?action=TouserUpdate&ids=<%=user.getId()%>"><img src="img/xiugai.png" alt="修改" title="修改"/></a>
                    <a href="javascript:deleteUser(<%=user.getId()%>)"><img src="img/schu.png" alt="删除" title="删除"/></a>
                </td>
            </tr>

            <%
                    }
                }
                    else if(lists!=null){
                        for (User user : lists) {
            %>

            <tr>
                <td><%=user.getId()%></td>
                <td><%=user.getUserName()%></td>
                <td><%=user.getSex()%></td>
                <td><%=user.getBirthday()%></td>
                <td><%=user.getUserphone()%></td>
                <td><%=user.getUserlei()%></td>
                <td>
                    <a href="/User.do?action=TouserList&ids=<%=user.getId()%>"><img src="img/read.png" alt="查看" title="查看"/></a>
                    <a href="/User.do?action=TouserUpdate&ids=<%=user.getId()%>"><img src="img/xiugai.png" alt="修改" title="修改"/></a>
                    <a href="javascript:deleteUser(<%=user.getId()%>)"><img src="img/schu.png" alt="删除" title="删除"/></a>
                </td>
            </tr>

            <%
                        }
                }

            %>

        </table>

    </div>
</section>

<!--点击删除按钮后弹出的页面-->
<div class="zhezhao"></div>
<div class="remove" id="removeUse">
    <div class="removerChid">
        <h2>提示</h2>
        <div class="removeMain">
            <p>你确定要删除该用户吗？</p>
            <a href="#" id="yes">确定</a>
            <a href="#" id="no">取消</a>
        </div>
    </div>
</div>

<footer class="footer">
</footer>

<script src="js/jquery.js"></script>
<script src="js/js.js"></script>
<script src="js/time.js"></script>

</body>
</html>