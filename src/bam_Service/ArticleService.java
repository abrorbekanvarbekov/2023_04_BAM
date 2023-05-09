package bam_Service;

import Bam_Dto.Article;
import bam_Dao.ArticleDao;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ArticleService {
    private ArticleDao articleDao;
    private Scanner sc;

    public ArticleService(Scanner sc){
        this.sc = sc;
        this.articleDao = new ArticleDao(sc);
    }


    public int setArticleId() {
        return articleDao.setArticleId();
    }

    public void add(Article article) {
        articleDao.add(article);
    }

    public void makeTestData() {
        articleDao.makeTestData();
    }


    public List<Article> getArticleList(String searchKeyword) {
        return articleDao.getArticleList(searchKeyword);
    }

    public Article getArticleById(int id) {
        return articleDao.getArticleById(id);
    }

    public void remove(Article foundArticle) {
        articleDao.remove(foundArticle);
    }
}
