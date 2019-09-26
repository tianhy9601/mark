package com.thy.Controller;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

@WebServlet("/ImageServlet.do")
public class ImageServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int width=55;//宽度
        int height=30;//高度

        BufferedImage bi=new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB);//设置图片
        //设置画笔
        Graphics gs=bi.getGraphics();
        gs.setColor(Color.white);
        //填充画布
        gs.fillRect(0,0,width,height);
        //设置图片边框
        gs.setColor(Color.BLACK);
        gs.drawRect(0,0,width-1,height-1);

        //生成随机数

        Character[] array = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', '0', '1', '2', '3', '4', '5', '6', '7', '8',
                '9', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'}; // 62

        Random rd=new Random();
        StringBuffer sb=new StringBuffer();
        for (int i = 1; i <= 4; i++) {
            int index=rd.nextInt(62);
            String c=array[index].toString();

            int red= rd.nextInt(256);
            int green= rd.nextInt(256);
            int blue= rd.nextInt(256);
            Color color=new Color(red,green,blue);
            gs.setColor(color);
            Font font=new Font("黑体",Font.BOLD,18);
            gs.setFont(font);
            gs.drawString(c,(50/5)*i,22);
            sb.append(c);
        }
        System.out.println("系统生成的验证码是："+sb.toString());
        HttpSession session=req.getSession();
        session.setAttribute("syscode",sb.toString());

        for (int i = 1; i < 5; i++) {
            int x1=rd.nextInt(width);
            int y1=rd.nextInt(height);
            int x2=rd.nextInt(width);
            int y2=rd.nextInt(height);
            int red= rd.nextInt(256);
            int green= rd.nextInt(256);
            int blue= rd.nextInt(256);
            Color color=new Color(red,green,blue);
            gs.setColor(color);
            gs.drawLine(x1,y1,x2,y2);
        }
        resp.setContentType("image/jepg");
        ServletOutputStream out=resp.getOutputStream();
        ImageIO.write(bi,"jpeg",out);

    }
}
