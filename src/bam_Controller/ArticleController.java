package bam_Controller;

import Bam_Dto.Article;
import Bam_Util.Util;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ArticleController extends Controller{
    private int articlesId = 0;
    private List<Article> articleList;
    private Scanner sc;
    private String cmd;
    private int id;
    public ArticleController(List<Article> articleList, Scanner sc){
        this.articleList = articleList;
        this.sc = sc;
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
        int id = articlesId + 1;
        articlesId = id;

        System.out.println("=== 게시물  작성 ===");
        System.out.println("내용  :   ");
        String title = sc.nextLine();
        System.out.println("제목  :   ");
        String body = sc.nextLine();
        String regDate = Util.getDateStr();
        Article article = new Article(id, regDate, title, body);
        articleList.add(article);
        System.out.printf("%d 번 글이 생성되었습니다.\n", id);
    }
    private void showList(){
        if (articleList.size() == 0) {
            System.out.println("존재하는 개시물이 없습니다!");
            return;
        }
        List<Article> printArticles = articleList;
        String searchKeyword = cmd.substring("article list".length()).trim();

        if (searchKeyword.length() > 0) {
            System.out.println("검색어    : " + searchKeyword);
            printArticles = new ArrayList<>();
            for (Article article : articleList) {
                if (article.title.contains(searchKeyword)) {
                    printArticles.add(article);
                }
            }
            if (printArticles.size() == 0) {
                System.out.println("검색 결과가 없습니다.");
                return;
            }
        }
        System.out.println("=== 게시물 목록 ===");
        System.out.println("번호  |   제목  |   작성일   ");
        for (int i = printArticles.size() - 1; i >= 0; i--) {
            Article article = printArticles.get(i);
            System.out.printf("%d   |   %s  |   %s\n", article.id, article.title, article.regDate);
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

        Article foundArticle = getArticleById(id);
        if (foundArticle == null) {
            System.out.printf("%d번 개시물은 존재하지 않습니다!\n", id);
            return;
        }
        System.out.println("=== 게시물 상세보기 ===");
        System.out.printf("번호   |   %d\n", foundArticle.id);
        System.out.printf("제목   |   %s\n", foundArticle.title);
        System.out.printf("내용   |   %s\n", foundArticle.body);
        System.out.printf("시간   |   %s\n", foundArticle.regDate);
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

        Article foundArticle = getArticleById(id);
        if (foundArticle == null) {
            System.out.printf("%d번 개시물은 존재하지 않습니다!\n", id);
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

        Article foundArticle = getArticleById(id);
        if (foundArticle == null) {
            System.out.printf("%d번 개시물은 존재하지 않습니다!\n", id);
            return;
        }
        articleList.remove(foundArticle);
        System.out.printf("%s번 게시뮬은 삭제 되었습니다!\n", id);
    }
    public void makeTestDate() {
        System.out.println("테스트용 게시물 데이터 5 개 새성");
        for (int i = 1; i <= 5; i++) {
            int id = articlesId + 1;
            articlesId = id;
            String title = "제목" + i;
            String body = "내용" + i;
            Article article = new Article(id, Util.getDateStr(), title, body);
            articleList.add(article);
        }
    }
    private Article getArticleById(int id) {
        for (Article article : articleList) {
            if (article.id == id) {
                return article;
            }
        }
        return null;
    }


}
