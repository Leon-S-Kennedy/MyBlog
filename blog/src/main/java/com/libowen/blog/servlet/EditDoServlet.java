package com.libowen.blog.servlet;

import com.libowen.blog.dao.ArticleDao;
import com.libowen.blog.model.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.Date;

@WebServlet("/edit.do")
public class EditDoServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        String title = req.getParameter("title");
        String type = req.getParameter("type");
        String content = req.getParameter("editor-markdown-doc");
        User currentUser=null;
        HttpSession session = req.getSession();
        if(session!=null){
            currentUser = (User)session.getAttribute("currentUser");
        }
        if(session==null){
            resp.sendRedirect("/login.html");
            return;
        }
        ArticleDao articleDao = new ArticleDao();
        int aid=articleDao.insert(currentUser.uid, title,type,new Date(),content);
        //重新定向到文章详情页
        resp.sendRedirect("/blog_content.html?aid="+aid);

    }
}
