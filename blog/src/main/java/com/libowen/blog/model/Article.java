package com.libowen.blog.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.util.Date;
@Data
public class Article {
    public Integer aid;
    @JsonIgnore
    public Integer uid;
    public String title;
    @JsonIgnore
    public String type;
    public String content;
    @JsonFormat(pattern = "yyyy/MM/dd HH:mm:ss",timezone = "GMT+8")
    public Date publishedAt;
    public String getSummary(){
        int len=Integer.min(content.length(),256);
        return content.substring(0,len);
    }
}
