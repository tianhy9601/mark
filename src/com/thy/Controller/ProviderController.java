package com.thy.Controller;

import JDBCUtils.BaseDao;
import com.thy.Bean.Providers;
import com.thy.Service.ProviderService;
import com.thy.Service.impl.ProviderServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.UUID;

@WebServlet("/Provider.do")
@MultipartConfig
public class ProviderController extends HttpServlet {

    ProviderService ps=new ProviderServiceImpl();
    BaseDao bd=new BaseDao();

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html;charset=utf-8");

        String action=req.getParameter("action");



        if("ToproviderList".equals(action)){
            String pname=req.getParameter("pname");
            List<Providers> list=ps.selectList(pname);
            req.setAttribute("list",list);
            req.setAttribute("pname",pname==null?"":pname);
            req.getRequestDispatcher("providerList.jsp").forward(req,resp);
        }else if("ToproviderView".equals(action)){
            int id=Integer.parseInt(req.getParameter("ids"));
            Providers providers=ps.selectById(id);
            if (providers!=null){
                req.setAttribute("providers",providers);
                req.getRequestDispatcher("providerView.jsp").forward(req,resp);
            }
        }else if("ToproviderUpdate".equals(action)){
            int id=Integer.parseInt(req.getParameter("ids"));
            Providers providers=ps.selectById(id);
            if (providers!=null){
                req.setAttribute("providers",providers);
                req.getRequestDispatcher("providerUpdate.jsp").forward(req,resp);
            }
        }else if("providerSave".equals(action)){

            int id=Integer.parseInt(req.getParameter("ids"));
            String providerName=req.getParameter("providerName");
            String people=req.getParameter("people");
            String phone=req.getParameter("phone");
            String address=req.getParameter("address");
            String fax=req.getParameter("fax");
            String describe=req.getParameter("describe");
            Providers pds=new Providers(id,providerName,null,people,phone,address,fax,describe);

            int count=ps.updateProvider(pds);
            if(count>0){
                resp.getWriter().write("<script>alert('更新成功');location.href='/Provider.do?action=ToproviderList'</script>");
            }else{
                resp.getWriter().write("<script>alert('更新失败');location.href='/Provider.do?action=ToproviderUpdate&ids="+id+"'</script>");
            }

        }else if("ToproviderDelete".equals(action)){
            int id=Integer.parseInt(req.getParameter("ids"));
            int count=ps.deleteById(id);
            if(count>0){
                resp.getWriter().write("<script>alert('删除成功');location.href='/Provider.do?action=ToproviderList'</script>");
            }else{
                resp.getWriter().write("<script>alert('删除失败');location.href='/Provider.do?action=ToproviderList'</script>");
            }
        }else if("ToproviderAdd".equals(action)){
            req.getRequestDispatcher("providerAdd.jsp").forward(req,resp);
        }else if("ToproviderAddSava".equals(action)){

            //文件上传（图片）
            Part part = req.getPart("providerCard");
            String header = part.getHeader("Content-Disposition");
            String kuozhanmin = header.substring(header.lastIndexOf("."), header.length() - 1);
            String fileName=UUID.randomUUID()+kuozhanmin;

            //获取文件输入流
            InputStream is=part.getInputStream();

            //准备上传文件的文件的服务器真实路径
            String path = req.getServletContext().getRealPath("files") + "/" + fileName;

            //获取输出流
            FileOutputStream fos=new FileOutputStream(path);

            byte[] bty=new byte[1024];
            int len=0;
            while ((len=is.read(bty))!=-1){
                fos.write(bty,0,len);
            }
            fos.close();
            is.close();


            String providerName=req.getParameter("providerName");
            String providerCard=fileName;
            String people=req.getParameter("people");
            String phone=req.getParameter("phone");
            String address=req.getParameter("address");
            String fax=req.getParameter("fax");
            String describe=req.getParameter("description");
            Providers pds=new Providers(providerName,providerCard,people,phone,address,fax,describe);

            int count=ps.addProvider(pds);
            if(count>0){
                resp.getWriter().write("<script>alert('新增用户成功');location.href='/Provider.do?action=ToproviderList'</script>");
            }else{
                resp.getWriter().write("<script>alert('新增用户失败，请重试');location.href='/Provider.do?action=ToproviderAddSava'</script>");
            }

        }
    }
}
