package com.libowen.blog.service;

import com.libowen.blog.dao.ArticleDao;
import com.libowen.blog.model.ArticleDetailResult;
import com.libowen.blog.model.ArticleListResult;
import com.libowen.blog.model.User;

public class ArticleService {
    public ArticleListResult articleList(User currentUser){
        ArticleDao articleDao = new ArticleDao();
        ArticleListResult result = new ArticleListResult();

        result.currentUser=currentUser;
        if(currentUser!=null){
            result.articleCount=articleDao.selectArticleCountByUid(currentUser.uid);
            result.typeCount=articleDao.selectTypeCountByUid(currentUser.uid);
            result.articleList=articleDao.selectListByUid(currentUser.uid);
        }
        return result;
    }

    public ArticleDetailResult detail(User currentUser, int aid) {
        ArticleDetailResult result = new ArticleDetailResult();
        if(currentUser==null){
            return result;
        }else{
            result.currentUser=currentUser;

            ArticleDao articleDao = new ArticleDao();
            result.articleCount=articleDao.selectArticleCountByUid(currentUser.uid);
            result.typeCount=articleDao.selectTypeCountByUid(currentUser.uid);
            result.article=articleDao.selectOneByAid(aid);
            return result;
        }
    }
}
