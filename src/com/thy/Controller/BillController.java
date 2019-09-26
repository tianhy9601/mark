package com.thy.Controller;

import com.google.gson.Gson;
import com.thy.Bean.Bills;
import com.thy.Bean.Providers;
import com.thy.Service.BillService;
import com.thy.Service.ProviderService;
import com.thy.Service.impl.BillServiceImpl;
import com.thy.Service.impl.ProviderServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/BillController.do")
public class BillController extends HttpServlet {

    BillService bs=new BillServiceImpl();
    ProviderService ps=new ProviderServiceImpl();

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html;charset=utf-8");

        String action=req.getParameter("action");
        if("TobillList".equals(action)){

            //输入的查询条件
            String title=req.getParameter("title");
            String pid=req.getParameter("pid");
            String pay=req.getParameter("pay");
            if (pid==null){
                pid="-1";
            }

            List<Bills> list=bs.selectList(title,pid,pay);
            req.setAttribute("title",title==null?"":title);
            req.setAttribute("pid",pid);
            req.setAttribute("pay",pay==null?"":pay);
            req.setAttribute("list",list);
            req.getRequestDispatcher("billList.jsp").forward(req,resp);



        }else if("xzkjiazai".equals(action)){
            List<Bills> lists=bs.selectAll();
            Gson gson=new Gson();
            String sd = gson.toJson(lists);
            resp.getWriter().write(sd);
        }
        else if("deleteBill".equals(action)){
            int id=Integer.parseInt(req.getParameter("id"));
            int count=bs.deleteById(id);
            if (count>0){
                resp.getWriter().write("1");
            }else{
                resp.getWriter().write("0");
            }
        }else if("ToUpdateBillInfo".equals(action)){
            int id=Integer.parseInt(req.getParameter("id"));
            Bills bl=bs.selectSingle(id);
            System.out.println(bl);
            req.setAttribute("bl",bl);
            req.getRequestDispatcher("billUpdate.jsp").forward(req,resp);

        }else if("updateBillInfoSave".equals(action)){

            int id=Integer.parseInt(req.getParameter("id"));
            int pid=Integer.parseInt(req.getParameter("pid"));
            String title=req.getParameter("title");
            String unit=req.getParameter("unit");
            int number=Integer.parseInt(req.getParameter("number"));
            double money=Double.parseDouble(req.getParameter("money"));
            int pay=Integer.parseInt(req.getParameter("pay"));
            Bills bl=new Bills();
            bl.setId(id);
            bl.setPid(pid);
            bl.setTitle(title);
            bl.setUnit(unit);
            bl.setNumber(number);
            bl.setMoney(money);
            bl.setPay(pay);

            int count=bs.updateBill(bl);
            System.out.println(count);
            if (count>0){
                resp.getWriter().write("<script>alert('更新成功');location.href='/BillController.do?action=TobillList'</script>");
            }else{
                resp.getWriter().write("<script>alert('更新失败，请重试');location.href='/BillController.do?action=ToUpdateBillInfo&id='"+id+"''</script>");
            }

        }else if("ToBillListView".equals(action)){
            int id=Integer.parseInt(req.getParameter("id"));
            Bills bl=bs.selectSingle(id);
            req.setAttribute("bl",bl);
            req.getRequestDispatcher("billView.jsp").forward(req,resp);
        } else if("addBillsSelected".equals(action)){
            //Ajax异步加载选择框
            List<Providers> list=ps.selectName();
            Gson gson=new Gson();
            String s = gson.toJson(list);
            resp.getWriter().write(s);
        }else if("addBills".equals(action)){

            //新增订单
            addBills(req, resp);

        }else if("selectBname".equals(action)){
            //检测是否重名

            String tName=req.getParameter("tName");
            int count=bs.selectBname(tName);
            if (count>0){
                resp.getWriter().write("1");
            }else{
                resp.getWriter().write("0");
            }
        }
    }

    private void addBills(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String title=req.getParameter("title");
        String unit=req.getParameter("unit");
        int number=Integer.parseInt(req.getParameter("number"));
        Double money=Double.parseDouble(req.getParameter("money"));
        int pay=Integer.parseInt(req.getParameter("pay"));
        int pid=Integer.parseInt(req.getParameter("providerName"));

        Bills bill=new Bills(title,unit,number,money,pid,pay);
        int count=bs.insertBill(bill);
        if (count>0){
            resp.getWriter().write("<script>alert('新增成功！');location.href='/BillController.do?action=TobillList'</script>");
        }else{
            resp.getWriter().write("<script>alert('新增失败，请重试！');location.href='/BillController.do?action=addBillsSelected'</script>");
        }
    }
}
