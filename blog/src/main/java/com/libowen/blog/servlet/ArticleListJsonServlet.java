package com.libowen.blog.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.libowen.blog.model.ArticleListResult;
import com.libowen.blog.model.User;
import com.libowen.blog.service.ArticleService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/article-list.json")
public class ArticleListJsonServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User currentUser=null;
        HttpSession session = req.getSession(false);
        if(session!=null){
            currentUser = (User)session.getAttribute("currentUser");
        }

        ArticleService articleService = new ArticleService();
        ArticleListResult result = articleService.articleList(currentUser);

        ObjectMapper objectMapper = new ObjectMapper();
        String jsonString = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(result);

        resp.setCharacterEncoding("utf-8");
        resp.setContentType("application/json");
        resp.getWriter().println(jsonString);
    }
}
