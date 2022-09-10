package com.libowen.blog.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.List;
@Data
public class ArticleListResult {
    public User currentUser;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public Integer articleCount;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public Integer typeCount;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public List<Article> articleList;
}
