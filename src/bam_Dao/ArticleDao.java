package bam_Dao;

import Bam_Dto.Article;
import Bam_Util.Util;
import bam_Controller.Controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ArticleDao {
    private int articlesId;
    private List<Article> articleList;
    private Scanner sc;
    private int id;

    public ArticleDao(Scanner sc){
        this.articleList = new ArrayList<>();
        this.articlesId = 0;
        this.sc = sc;
    }

    public int setArticleId() {
        int id = articlesId + 1;
        articlesId = id;
        return id;
    }

    public void add(Article article) {
        articleList.add(article);
    }


    public Article getArticleById(int id) {
        for (Article article : articleList) {
            if (article.id == id) {
                return article;
            }
        }
        return null;
    }

    public List<Article> getArticleList(String searchKeyword) {
        if (searchKeyword.length() > 0) {
            System.out.println("검색어    : " + searchKeyword);
            List<Article>printArticles = new ArrayList<>();
            for (Article article : articleList) {
                if (article.title.contains(searchKeyword)) {
                    printArticles.add(article);
                }
            }
            return printArticles;
        }
        return articleList;
    }
    public void makeTestData() {
        for (int i = 1; i <= 5; i++) {
            int id = articlesId + 1;
            articlesId = id;
            String title = "제목" + i;
            String body = "내용" + i;
            Article article = new Article(id, Util.getDateStr(), 2, title, body);
            articleList.add(article);
        }
    }

    public void remove(Article foundArticle) {
        articleList.remove(foundArticle);
    }
}
