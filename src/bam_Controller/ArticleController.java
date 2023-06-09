package bam_Controller;

import Bam_Dto.Article;
import Bam_Util.Util;
import bam_Service.ArticleService;
import bam_Service.UserService;
import bam_container.Container;


import java.util.List;
import java.util.Scanner;

public class ArticleController extends Controller{
    private Scanner sc;
    private String cmd;
    private ArticleService articleService;
    private UserService userService;
    private int id;
    public ArticleController(Scanner sc){
        this.sc = sc;
        this.articleService = Container.articleService;
        this.userService = Container.userService;
    }


    @Override
    public void doAction(String cmd, String methodName) {

        this.cmd = cmd;

        switch (methodName){
            case "write" :
                doArticleWrite();
                break;
            case "list":
                showList();
                break;
            case "detail":
                showDetail();
                break;
            case "modify":
                doModify();
                break;
            case "delete":
                doDelete();
                break;
            default:
                System.out.println("명령어를 확인해주세요!");
                break;
        }
    }
    private void doArticleWrite(){
        int id = articleService.setArticleId();

        System.out.println("=== 게시물  작성 ===");
        System.out.println("내용  :   ");
        String title = sc.nextLine();
        System.out.println("제목  :   ");
        String body = sc.nextLine();
        String regDate = Util.getDateStr();
        Article article = new Article(id, regDate,Controller.loginedUser.id, title, body);
        articleService.add(article);
        System.out.printf("%d 번 글이 생성되었습니다.\n", id);
    }
    private void showList(){
        String searchKeyword = cmd.substring("article list".length()).trim();
        List<Article> printArticles = articleService.getArticleList(searchKeyword);

        if (printArticles.size() == 0) {
            System.out.println("존재하는 개시물이 없습니다!");
            return;
        }

        System.out.println("=== 게시물 목록 ===");
        System.out.println("번호    |  제목  |   작성일   |    작성자");
        for (int i = printArticles.size() - 1; i >= 0; i--) {
            Article article = printArticles.get(i);

            String writerName = userService.getWriterName(article.userId);

            System.out.printf("%d   |   %s  |   %s    |    %s\n", article.id, article.title, article.regDate,writerName);
        }
    }

    private void showDetail(){
        String[] cmdBist = cmd.split(" ");

        if (cmdBist.length == 2){
            System.out.println("명령어를 확인해주세요!");
            return;
        }
        try {
            id = Integer.parseInt(cmdBist[2]);
        }catch (Exception e){
            System.out.println("잘 못된 명령어를 입력하였습니다!!");
            return;
        }
        Article foundArticle = articleService.getArticleById(id);
        if (foundArticle == null) {
            System.out.printf("%d번 개시물은 존재하지 않습니다!\n", id);
            return;
        }

        String writerName = userService.getWriterName(foundArticle.userId);

        System.out.println("=== 게시물 상세보기 ===");
        System.out.printf("번호   |   %d\n", foundArticle.id);
        System.out.printf("제목   |   %s\n", foundArticle.title);
        System.out.printf("내용   |   %s\n", foundArticle.body);
        System.out.printf("시간   |   %s\n", foundArticle.regDate);
        System.out.printf("작성자   |   %s\n", writerName);
    }

    private void doModify(){
        String[] cmdBist = cmd.split(" ");

        if (cmdBist.length == 2){
            System.out.println("명령어를 확인해주세요!");
            return;
        }
        try {
            id = Integer.parseInt(cmdBist[2]);
        }catch (Exception e){
            System.out.println("잘 못된 명령어를 입력하였습니다!!");
            return;
        }

        Article foundArticle = articleService.getArticleById(id);

        if (foundArticle == null) {
            System.out.printf("%d번 개시물은 존재하지 않습니다!\n", id);
            return;
        }
        if(foundArticle.userId != Controller.loginedUser.id){
            System.out.println("해당 개시물에 대한 권한이 없습니다.");
            return;
        }


        System.out.println("=== 게시물 수정 ===");
        System.out.printf("수정할 제목   :\n");
        String title = sc.nextLine();
        System.out.printf("수정할 내용   :\n");
        String body = sc.nextLine();

        foundArticle.title = title;
        foundArticle.body = body;
        System.out.printf("%s번 게시물이 수정 되었습니다!\n", id);
    }

    private void doDelete(){
        String[] cmdBist = cmd.split(" ");

        if (cmdBist.length == 2){
            System.out.println("명령어를 확인해주세요!");
            return;
        }

        try {
            id = Integer.parseInt(cmdBist[2]);
        }catch (Exception e){
            System.out.println("잘 못된 명령어를 입력하였습니다!!");
            return;
        }

        Article foundArticle = articleService.getArticleById(id);

        if (foundArticle == null) {
            System.out.printf("%d번 개시물은 존재하지 않습니다!\n", id);
            return;
        }

        if(foundArticle.userId != Controller.loginedUser.id){
            System.out.println("해당 개시물에 대한 권한이 없습니다.");
            return;
        }

        articleService.remove(foundArticle);
        System.out.printf("%s번 게시뮬은 삭제 되었습니다!\n", id);
    }

    @Override
    public void makeTestData() {
        System.out.println("테스트용 게시물 데이터 5 개 새성");
        articleService.makeTestData();
    }

}
