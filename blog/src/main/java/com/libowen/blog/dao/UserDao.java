package com.libowen.blog.dao;

import com.libowen.blog.model.User;
import com.libowen.blog.utill.DBUtil;
import lombok.SneakyThrows;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UserDao {
    @SneakyThrows
    public User selectOneByUsernameAndPassword(String username, String password){
        String sql="select uid,avatar,git_repo from users where username= ? and password= ?";
        try(Connection c=DBUtil.connection()){
            try(PreparedStatement ps=c.prepareStatement(sql)){
                ps.setString(1,username);
                ps.setString(2,password);
                try(ResultSet rs=ps.executeQuery()){
                    if(!rs.next()){
                        return null;
                    }
                    User user=new User();
                    user.uid=rs.getInt("uid");
                    user.username=username;
                    user.password=password;
                    user.avatar=rs.getString("avatar");
                    user.gitRepo=rs.getString("git_repo");

                    return user;
                }
            }
        }

    }
}
