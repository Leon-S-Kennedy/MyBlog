package com.libowen.blog.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.libowen.blog.model.ArticleDetailResult;
import com.libowen.blog.model.User;
import com.libowen.blog.service.ArticleService;
import com.sun.xml.internal.bind.v2.TODO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/article-detail.json")
public class ArticleDetailJsonServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        String aidString = req.getParameter("aid");
        int aid = Integer.parseInt(aidString);
        //TODO

        //从（cookie-session）中获取用户登录信息
        User currentUser=null;
        HttpSession session = req.getSession(false);
        if(session!=null){
            currentUser=(User) session.getAttribute("currentUser");
        }

        //构造一个结果对象（包含currentUser、articleCount、typeCount、article）
        //这个从数据库到jsonString经过了3个阶段，先是dao(数据库获取原始数据)--然后是service(将获取的数据进行处理)--然后是sevlet(最后)
        ArticleService articleService = new ArticleService();
        ArticleDetailResult result=articleService.detail(currentUser,aid);

        //把对象变成json字符串
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonString = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(result);

        //响应这个json字符串
        resp.setCharacterEncoding("utf-8");
        resp.setContentType("application/json");
        resp.getWriter().println(jsonString);

    }
}
