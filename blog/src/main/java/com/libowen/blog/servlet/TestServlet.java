package com.libowen.blog.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.libowen.blog.model.Article;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/test.json")
public class TestServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Map<String, Article> map=new HashMap<>();
        map.put("currentUser",null);
        ObjectMapper objectMapper = new ObjectMapper();
        String s = objectMapper.writeValueAsString(map);

        resp.setCharacterEncoding("utf-8");
        resp.setContentType("application/json");
        resp.getWriter().println(s);

    }
}
