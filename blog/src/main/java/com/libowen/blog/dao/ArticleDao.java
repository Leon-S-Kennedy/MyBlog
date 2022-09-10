package com.libowen.blog.dao;

import com.libowen.blog.model.Article;
import com.libowen.blog.utill.DBUtil;
import lombok.SneakyThrows;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ArticleDao {
    @SneakyThrows
    public int selectArticleCountByUid(int uid){
        String sql="select count(*) from articles where uid=?";
        try(Connection c= DBUtil.connection()){
            try(PreparedStatement ps =c.prepareStatement(sql)){
                ps.setInt(1,uid);
                try(ResultSet rs=ps.executeQuery()){
                    rs.next();
                    return rs.getInt(1);
                }
            }
        }
    }
    @SneakyThrows
    public int selectTypeCountByUid(int uid){
        String sql="select count(distinct type) from articles where uid=?";
        try(Connection c= DBUtil.connection()){
            try(PreparedStatement ps =c.prepareStatement(sql)){
                ps.setInt(1,uid);
                try(ResultSet rs=ps.executeQuery()){
                    rs.next();
                    return rs.getInt(1);
                }
            }
        }
    }
    @SneakyThrows
    public List<Article> selectListByUid(int uid){
        List<Article> list=new ArrayList<>();
        String sql="select aid,uid,title,published_at,content,type from articles where uid =? order by published_at desc";
        try(Connection c=DBUtil.connection()){
            try(PreparedStatement ps = c.prepareStatement(sql)){
                ps.setInt(1,uid);
                try(ResultSet rs = ps.executeQuery()){
                    while (rs.next()){
                        Article article = new Article();
                        article.aid=rs.getInt("aid");
                        article.uid=rs.getInt("uid");
                        article.title=rs.getString("title");
                        article.type=rs.getString("type");
                        article.publishedAt=rs.getDate("published_at");
                        article.content=rs.getString("content");
                        list.add(article);

                    }
                }
            }
        }
        return list;
    }
    @SneakyThrows
    public Article selectOneByAid(int aid) {
        String sql="select aid,title,published_at,content from articles where aid= ?";
        try(Connection c=DBUtil.connection()){
            try(PreparedStatement ps = c.prepareStatement(sql)){
                ps.setInt(1,aid);
                try(ResultSet rs = ps.executeQuery()){
                    rs.next();
                    Article article = new Article();
                    article.aid=rs.getInt("aid");
                    article.title=rs.getString("title");
                    article.publishedAt=rs.getDate("published_at");
                    article.content=rs.getString("content");
                    return article;
                }
            }
        }
    }
    @SneakyThrows
    public int insert(int uid,String title, String type, Date date, String content) {
        String sql="insert into articles (uid,title,type,published_at,content) values (?,?,?,?,?)";
        try (Connection c = DBUtil.connection()){
            try(PreparedStatement ps= c.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){
                ps.setInt(1,uid);
                ps.setString(2,title);
                ps.setString(3,type);
                DateFormat Format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String publishedAt = Format.format(date);
                ps.setString(4,publishedAt);
                ps.setString(5,content);
                ps.executeUpdate();
                try ( ResultSet rs = ps.getGeneratedKeys()){
                    rs.next();
                    return rs.getInt(1);
                }
            }
        }
    }
}
