package com.thy.Controller;

import JDBCUtils.BaseDao;
import com.thy.Bean.Providers;
import com.thy.Bean.User;
import com.thy.Service.UserService;
import com.thy.Service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.net.URLEncoder;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/User.do")
public class UserController extends HttpServlet {

    UserService us=new UserServiceImpl();
    BaseDao bd=new BaseDao();

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

//        req.setCharacterEncoding("UTF-8");
//        resp.setContentType("text/html;charset=utf-8");

        String action=req.getParameter("action");

        if ("login".equals(action)){
            login(req, resp);
        }else if("ToupdatePassword".equals(action)){
            req.getRequestDispatcher("updatePassword.jsp").forward(req,resp);
        }else if("updatePassword".equals(action)){
            updatePassword(req, resp);
        }else if("TouserView".equals(action)){


            List<User> list=us.selectUserInfo();
            req.setAttribute("list",list);
            req.getRequestDispatcher("userView.jsp").forward(req,resp);

        }else if("TobillList".equals(action)){
            //待写
            req.getRequestDispatcher("billList.jsp").forward(req,resp);

        }else if("TouserList".equals(action)){
            int idss=Integer.parseInt(req.getParameter("ids"));
            User user=us.selectUserSingle(idss);
            if (user!=null){
                req.setAttribute("user",user);
                req.getRequestDispatcher("userList.jsp").forward(req,resp);
            }
        }else if("TouserUpdate".equals(action)){
            int ids=Integer.parseInt(req.getParameter("ids"));
            User user=us.selectUserSingle(ids);
            if (user!=null){
                req.setAttribute("user",user);

                req.getRequestDispatcher("userUpdate.jsp").forward(req,resp);
            }
        }else if("updateUser".equals(action)){

            updateUser(req, resp);
        }else if ("deleteUser".equals(action)){
            deleteUser(req, resp);
        }else if("ToAddUser".equals(action)){
            req.getRequestDispatcher("userAdd.jsp").forward(req,resp);
        } else if("addUser".equals(action)){
            addUser(req, resp);

        }else if("selectedUserByName".equals(action)){
            selectedUserByName(req, resp);
        }else if("logout".equals(action)){
            HttpSession session=req.getSession();
            session.invalidate();
            resp.sendRedirect("login.jsp");
        }else if("checkUser".equals(action)){
            String userName=req.getParameter("userName");
            System.out.println("带检测的值为："+userName);
            boolean b=us.checkName(userName);
            if (b){
                resp.getWriter().write("yes");
            }else{
                resp.getWriter().write("no");
            }
        }





    }

    private void selectedUserByName(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String uname=req.getParameter("uname");
        int id=Integer.parseInt(req.getParameter("id"));
        req.setAttribute("id",id);

        List<User> lists=us.selectUserByName(uname);

        req.setAttribute("lists",lists);
        req.getRequestDispatcher("userView.jsp").forward(req,resp);
    }

    private void addUser(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        String userName=req.getParameter("userName");
        String userpassword=req.getParameter("userpassword");
        String sex=req.getParameter("sex");
        String birthday=req.getParameter("birthday");
        String userphone=req.getParameter("userphone");
        String userAddress=req.getParameter("userAddress");
        String userlei=req.getParameter("userlei");

        User user=new User(userName,userpassword,sex,birthday,userphone,userAddress,userlei);
        System.out.println(user);

        int count=us.addUser(user);

        if(count>0){
            resp.getWriter().write("<script>alert('新增用户成功');location.href='/User.do?action=TouserView'</script>");
        }else{
            resp.getWriter().write("<script>alert('新增用户失败');location.href='/User.do?action=TouserView'</script>");
        }
    }

    private void deleteUser(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        long ids=Long.parseLong(req.getParameter("ids"));

        int count=us.deleteById(ids);


        if (count>0){
            resp.getWriter().write("<script>alert('删除成功');location.href='/User.do?action=TouserView'</script>");
        }else{
            resp.getWriter().write("<script>alert('删除失败');location.href='/User.do?action=TouserView'</script>");
        }
    }

    private void updateUser(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        int ids=Integer.parseInt(req.getParameter("ids"));

        String userName=req.getParameter("userName");

        String sex=req.getParameter("sex");

        String birthday=req.getParameter("birthday");

        String userphone=req.getParameter("userphone");

        String userAddress=req.getParameter("userAddress");

        String userlei=req.getParameter("userlei");

        User user=new User(ids,userName,sex,birthday,userphone,userAddress,userlei);

        int count=us.updateInfo(user);
        if (count>0){
            resp.getWriter().write("<script>alert('更新成功');location.href='/User.do?action=TouserView'</script>");
        }else{
            resp.getWriter().write("<script>alert('更新失败');location.href='/User.do?action=updateUser&ids="+ids+"'</script>");
        }
    }

    private void updatePassword(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        int id=Integer.parseInt(req.getParameter("id"));
        String newPassword = req.getParameter("newPassword");

        int count=us.updatePassword(id,newPassword);

        if (count>0){
            resp.getWriter().write("<script>alert('修改成功');location.href='/User.do?action=TouserView&id="+id+"'</script>");
        }else{
            resp.getWriter().write("<script>alert('修改失败');location.href='/User.do?action=TouserView&id="+id+"'</script>");
        }
    }

    private void login(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        String username=req.getParameter("username");
        String  password=req.getParameter("password");
        String  code=req.getParameter("code");

        HttpSession session = req.getSession();
        String syscode =(String) session.getAttribute("syscode");
        if (syscode.equalsIgnoreCase(code)){
            User users=new User(username,password,null,null,null,null,null);

            ResultSet rs=us.selectUser(users);

            try {
                if (rs.next()){


                    //设置Cookie
                    Cookie c_name=new Cookie("cookie_name", URLEncoder.encode(username,"UTF-8"));
                    Cookie c_pass=new Cookie("cookie_pass",URLEncoder.encode(password,"UTF-8"));
                    c_name.setPath("/");
                    c_pass.setPath("/");
                    c_name.setMaxAge(60);
                    c_pass.setMaxAge(60);
                    resp.addCookie(c_name);
                    resp.addCookie(c_pass);

                    int id=rs.getInt(1);
                    String usernames=rs.getString(2);
                    String passwords=rs.getString(3);
                    String sex=rs.getString(4);
                    String birthday=rs.getString(5);
                    String userphone=rs.getString(6);
                    String userAddress=rs.getString(7);
                    String userlei=rs.getString(8);

                    User user=new User(id,usernames,passwords,sex,birthday,userphone,userAddress,userlei);


                    //秒为单位
                    session.setMaxInactiveInterval(-1);
                    session.setAttribute("user",user);

                    req.getRequestDispatcher("index.jsp").forward(req,resp);

                }else{
                    resp.getWriter().write("<script>alert('登录失败,请重试！');location.href='login.jsp'</script>");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                try {
                    rs.close();
                    bd.myClos();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }else{
            resp.getWriter().write("<script>alert('验证码错误,请重试！');location.href='login.jsp'</script>");
        }


    }
}
