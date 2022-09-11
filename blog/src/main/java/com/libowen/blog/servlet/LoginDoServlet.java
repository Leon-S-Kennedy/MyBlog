package com.libowen.blog.servlet;

import com.libowen.blog.dao.UserDao;
import com.libowen.blog.model.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/login.do")
public class LoginDoServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        UserDao userDao = new UserDao();
        User user = userDao.selectOneByUsernameAndPassword(username, password);
        if(user==null){
            resp.sendRedirect("/login.html");
            return;
        } else {
            HttpSession session = req.getSession();
            session.setAttribute("currentUser",user);

            resp.sendRedirect("/");
        }

    }
}
