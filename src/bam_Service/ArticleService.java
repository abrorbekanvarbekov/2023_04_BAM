package bam_Service;

import Bam_Dto.Article;
import bam_Dao.ArticleDao;
import bam_container.Container;

import java.util.List;

public class ArticleService {
    private ArticleDao articleDao;

    public ArticleService(){
        this.articleDao = Container.articleDao;
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
