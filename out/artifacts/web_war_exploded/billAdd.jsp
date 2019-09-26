<%@ page import="com.thy.Bean.Providers" %>
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
        function selectBname() {
            var title=$("#title").val();
            if (title!="") {
                var xmlhrequest=new XMLHttpRequest();
                xmlhrequest.onreadystatechange=function () {
                    if (xmlhrequest.readyState==4&&xmlhrequest.status==200) {
                        var result=xmlhrequest.responseText;
                        if(result=="1"){
                            $("#b_title").css("color","red");
                            $("#b_title").text("该商品名已经存在！");
                        }else{
                            $("#b_title").css("color","green");
                            $("#b_title").text("输入可用")
                        }
                    }
                }
                var url="/BillController.do?action=selectBname&tName="+title;
                xmlhrequest.open("GET",url,true);
                xmlhrequest.send();
            }else{
                $("#b_title").css("color","red");
                $("#b_title").text("请输入商品名称");
            }
        }

        function selectPname() {

            var xmlHttpRequest=new XMLHttpRequest();
            xmlHttpRequest.onreadystatechange=function () {
                if(xmlHttpRequest.status==200&&xmlHttpRequest.readyState==4){
                    var result=xmlHttpRequest.responseText;
                    var pros=JSON.parse(result);
                    var s="";
                    for(var i=0;i<pros.length;i++){
                        s=s+"<option value='"+pros[i].providerId+"'>"+pros[i].providerName+"</option>"
                    }
                    $("#pros_id").append(s);
                   // document.getElementById("pros_id").innerHTML=s;
                }
            }
            var url="/BillController.do?action=addBillsSelected";
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
        <a href="login.html">退出</a>
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
            <span>账单管理页面 >> 订单添加页面</span>
        </div>
        <div class="providerAdd">
            <form action="/BillController.do?action=addBills" method="post">
                <!--div的class 为error是验证错误，ok是验证成功-->
                <div>
                    <label for="title">商品名称：</label>
                    <input type="text" name="title" id="title" onblur="selectBname()" required/>
                    <span id="b_title"></span>
                </div>
                <div>
                    <label for="unit">商品单位：</label>
                    <input type="text" name="unit" id="unit" required/>
                    <span>*请输入商品单位</span>

                </div>
                <div>
                    <label for="number">商品数量：</label>
                    <input type="text" name="number" id="number" required/>
                    <span>*请输入大于0的正自然数，小数点后保留2位</span>
                </div>
                <div>
                    <label for="money">总金额：</label>
                    <input type="text" name="money" id="money" required/>
                    <span>*请输入大于0的正自然数，小数点后保留2位</span>
                </div>
                <div>
                    <label >供应商：</label>
                    <select name="providerName" id="pros_id">
<%--
                        <%
                            List<Providers> list=(List<Providers>)request.getAttribute("list");
                            if (list!=null){
                                for(Providers p:list){
                                    %>
                        <option value="<%=p.getProviderName()%>"><%=p.getProviderName()%></option>
                        <%
                                }

                            }
                        %>

--%>

                    </select>
                    <span>*请选择供应商</span>
                </div>
                <div>
                    <label >是否付款：</label>
                    <input type="radio" name="pay" value="0" checked />未付款
                    <input type="radio" name="pay" value="1"/>已付款
                </div>
                <div class="providerAddBtn">
                    <!--<a href="#">保存</a>-->
                    <!--<a href="billList.html">返回</a>-->
                    <input type="submit" value="保存" onclick="history.back(-1)"/>
                    <a href="/BillController.do?action=TobillList">返回</a>
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
