package bam_container;

import bam_Dao.ArticleDao;
import bam_Dao.UserDao;
import bam_Service.ArticleService;
import bam_Service.UserService;

public class Container {
    public static ArticleDao articleDao;
    public static ArticleService articleServise;
    public static UserDao userDao;
    public static UserService userService;

    static {
        articleDao = new ArticleDao();
        articleServise = new ArticleService();
        userDao = new UserDao();
        userService = new UserService();
    }

}

