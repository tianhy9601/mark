<%@ page import="com.thy.Bean.Bills" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <title>超市账单管理系统</title>
    <link rel="stylesheet" href="css/public.css"/>
    <link rel="stylesheet" href="css/style.css"/>
    <script src="js/jquery.js"></script>
    <script type="text/javascript">
        function logout() {
            if(confirm("确认退出吗？")){
                location.href='/User.do?action=logout';
            }
        }
        function deleteBillList(idss) {
            if(confirm("确定删除吗")){
                var xmlHttpRequest=new XMLHttpRequest();
                xmlHttpRequest.onreadystatechange=function () {
                    if (xmlHttpRequest.readyState==4&&xmlHttpRequest.status==200){
                        var result=xmlHttpRequest.responseText;
                        if (result=="1"){
                            location.href='/BillController.do?action=TobillList';
                        }else{
                            alert("删除出现异常");
                        }
                    }
                }

                var url="/BillController.do";
                xmlHttpRequest.open("POST",url,true);
                xmlHttpRequest.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
                var data="action=deleteBill&id="+idss;
                xmlHttpRequest.send(data);
            }
        }

        function selectPname() {

            var xmlHttpRequest=new XMLHttpRequest();
            xmlHttpRequest.onreadystatechange=function () {
                if(xmlHttpRequest.status==200&&xmlHttpRequest.readyState==4){
                    var result=xmlHttpRequest.responseText;
                    var pros=JSON.parse(result);
                    var s="<option value='-1'>--请选择--</option>";
                    for(var i=0;i<pros.length;i++){
                        s=s+"<option value='"+pros[i].pid+"'>"+pros[i].providerName+"</option>"
                    }
                    $("#pros_id").append(s);
                    // document.getElementById("pros_id").innerHTML=s;
                    //下拉框加载完后（下拉框特性）回显
                    document.getElementById("pros_id").value=<%=request.getAttribute("pid")%>;
                }
            }
            var url="/BillController.do?action=xzkjiazai";
            xmlHttpRequest.open("POST",url,true);
            xmlHttpRequest.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
            xmlHttpRequest.send();

        }
    </script>
</head>
<body onload="selectPname();">

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
                <li id="active"><a href="/BillController.do?action=TobillList">账单管理</a></li>
                <li><a href="/Provider.do?action=ToproviderList">供应商管理</a></li>
                <li><a href="/User.do?action=TouserView">用户管理</a></li>
                <li><a href="/User.do?action=ToupdatePassword">密码修改</a></li>
                <li><a href="javascript:logout();">退出系统</a></li>
            </ul>
        </nav>
    </div>
    <div class="right">
        <div class="location">
            <strong>你现在所在的位置是:</strong>
            <span>账单管理页面</span>
        </div>
        <div class="search">
            <form action="BillController.do?action=TobillList" method="post" id="bbb">
                <span>商品名称：</span>
                <input type="text" name="title" placeholder="请输入商品的名称" value="<%=request.getAttribute("title")%>"/>

                <span>供应商：</span>
                <select name="pid" id="pros_id">

                </select>

                <span>是否付款：</span>
                <select name="pay">
                    <option value="" selected>--请选择--</option>
                    <option value="1" <%=request.getAttribute("pay").equals("1")?"selected":""%>>已付款</option>
                    <option value="0" <%=request.getAttribute("pay").equals("0")?"selected":""%>>未付款</option>
                </select>

                <input type="button" value="查询" onclick="document.getElementById('bbb').submit()"/>
            </form>
            <a href="billAdd.jsp">添加订单</a>
        </div>
        <!--账单表格 样式和供应商公用-->
        <table class="providerTable" cellpadding="0" cellspacing="0">
            <tr class="firstTr">
                <th width="10%">账单编码</th>
                <th width="20%">商品名称</th>
                <th width="10%">供应商</th>
                <th width="10%">账单金额</th>
                <th width="10%">是否付款</th>
                <th width="10%">创建时间</th>
                <th width="30%">操作</th>
            </tr>
            <%
                List<Bills> list=(List<Bills>)request.getAttribute("list");
                if (list!=null){
                    for (Bills b: list) {
                        %>

            <tr id="xx_<%=b.getId()%>">
                <td><%=b.getId()%></td>
                <td><%=b.getTitle()%></td>
                <td><%=b.getProviderName()%></td>
                <td><%=b.getMoney()%></td>
                <td><%=b.getPay()==1?"已付款":"未付款"%></td>
                <td><%=b.getTime()%></td>
                <td>
                    <a href="/BillController.do?action=ToBillListView&id=<%=b.getId()%>"><img src="img/read.png" alt="查看" title="查看"/></a>
                    <a href="/BillController.do?action=ToUpdateBillInfo&id=<%=b.getId()%>"><img src="img/xiugai.png" alt="修改" title="修改"/></a>
                    <a href="javascript:deleteBillList(<%=b.getId()%>)" class="removeBill"><img src="img/schu.png" alt="删除" title="删除"/></a>
                </td>
            </tr>

            <%
                    }
                }
            %>

        </table>
    </div>
</section>

<!--点击删除按钮后弹出的页面
<div class="zhezhao"></div>
<div class="remove" id="removeBi">
    <div class="removerChid">
        <h2>提示</h2>
        <div class="removeMain">
            <p>你确定要删除该订单吗？</p>
            <a href="#" id="yes">确定</a>
            <a href="#" id="no">取消</a>
        </div>
    </div>
</div>
-->
<footer class="footer">
</footer>

<script src="js/jquery.js"></script>
<script src="js/js.js"></script>
<script src="js/time.js"></script>

</body>
</html>
