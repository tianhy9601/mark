<%@ page import="com.thy.Bean.Providers" %>
<%@ page import="java.util.List" %>
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
        function deleteProvider(id) {
            if(confirm("确认删除吗？")){
                location.href='/Provider.do?action=ToproviderDelete&ids='+id;
            }
        }
    </script>
</head>
<body>
<%@include file="checklogin.jsp"%>
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
                <li id="active"><a href="/Provider.do?action=ToproviderList">供应商管理</a></li>
                <li><a href="/User.do?action=TouserView">用户管理</a></li>
                <li><a href="/User.do?action=ToupdatePassword">密码修改</a></li>
                <li><a href="javascript:logout();">退出系统</a></li>
            </ul>
        </nav>
    </div>
    <div class="right">
        <div class="location">
            <strong>你现在所在的位置是:</strong>
            <span>供应商管理页面</span>
        </div>
        <div class="search">
            <span>供应商名称：</span>
            <form action="/Provider.do?action=ToproviderList" method="post" id="ccc">
            <input type="text" name="pname" placeholder="请输入供应商的名称" value="<%=request.getAttribute("pname")%>"/>
            <input type="button" value="查询" onclick="document.getElementById('ccc').submit();"/>
            <a href="/Provider.do?action=ToproviderAdd">添加供应商</a>
            </form>
        </div>
        <!--供应商操作表格-->
        <table class="providerTable" cellpadding="0" cellspacing="0">
            <tr class="firstTr">
                <th width="10%">供应商编码</th>
                <th width="10%">供应商名称</th>
                <th width="10%">供应商执照</th>
                <th width="10%">联系人</th>
                <th width="10%">联系电话</th>
                <th width="10%">传真</th>
                <th width="10%">描述</th>
                <th width="30%">操作</th>
            </tr>


            <%

                List<Providers> list=(List<Providers>)request.getAttribute("list");
                if (list!=null){
                    for (Providers p: list) {
                        %>

            <tr>
                <td><%=p.getProviderId()%></td>
                <td><%=p.getProviderName()%></td>
                <td><img src="files/<%=p.getProviderCard()%>" style="width: 100px"></td>
                <td><%=p.getPeople()%></td>
                <td><%=p.getPhone()%></td>
                <td><%=p.getFax()%></td>
                <td><%=p.getDescription()%></td>
                <td>
                    <a href="/Provider.do?action=ToproviderView&ids=<%=p.getProviderId()%>"><img src="img/read.png" alt="查看" title="查看"/></a>
                    <a href="/Provider.do?action=ToproviderUpdate&ids=<%=p.getProviderId()%>"><img src="img/xiugai.png" alt="修改" title="修改"/></a>
                    <a href="javascript:deleteProvider(<%=p.getProviderId()%>)" class="removeProvider"><img src="img/schu.png" alt="删除" title="删除"/></a>
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
<div class="remove" id="removeProv">
    <div class="removerChid">
        <h2>提示</h2>
        <div class="removeMain" >
            <p>你确定要删除该供应商吗？</p>
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
