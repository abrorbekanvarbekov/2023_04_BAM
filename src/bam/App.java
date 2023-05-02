package bam;

import Bam_Dto.Article;
import Bam_Util.Util;
import bam_Controller.MemberController;
import bam_User.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class App {
    private List<Article> articleList;
    private int articlesId;
    private List<User> userList;

    public App() {
        articleList = new ArrayList<>();
        articlesId = 0;
        userList = new ArrayList<>();
    }

    public void run() {
        System.out.println("== 프로그램 시작 ==");
        makeTestDate();
        Scanner sc = new Scanner(System.in);
        MemberController memberController = new MemberController(userList, sc);

        while (true) {
            System.out.println("명령어)");
            String cmd = sc.nextLine();
            if (cmd.equals("exit")) {
                break;
            }
            if (cmd.equals("member join")) {
                memberController.doJoin();
            } else if (cmd.equals("member list")) {
                System.out.println("== 회원 목록 ==");
                if (userList.size() == 0) {
                    System.out.println("회원 가입자가 없습니다!");
                }
                for (int i = userList.size() - 1; i >= 0; i--) {
                    User user = userList.get(i);
                    System.out.printf("%d   |   %s  |   %s    |    %s\n", user.id, user.userId, user.regDate, user.userName);
                }
            } else if (cmd.equals("article write")) {
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

            } else if (cmd.startsWith("article list")) {
                if (articleList.size() == 0) {
                    System.out.println("존재하는 개시물이 없습니다!");
                    continue;
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
                        continue;
                    }
                }
                System.out.println("=== 게시물 목록 ===");
                System.out.println("번호  |   제목  |   작성일   ");
                for (int i = printArticles.size() - 1; i >= 0; i--) {
                    Article article = printArticles.get(i);
                    System.out.printf("%d   |   %s  |   %s\n", article.id, article.title, article.regDate);
                }
            } else if (cmd.startsWith("article detail ")) {
                String[] cmdBist = cmd.split(" ");
                int id = Integer.parseInt(cmdBist[2]);
                Article foundArticle = getArticleById(id);
                if (foundArticle == null) {
                    System.out.printf("%d번 개시물은 존재하지 않습니다!\n", id);
                    continue;
                }
                System.out.println("=== 게시물 상세보기 ===");
                System.out.printf("번호   |   %d\n", foundArticle.id);
                System.out.printf("제목   |   %s\n", foundArticle.title);
                System.out.printf("내용   |   %s\n", foundArticle.body);
                System.out.printf("시간   |   %s\n", foundArticle.regDate);

            } else if (cmd.startsWith("article modify ")) {
                String[] cmdBist = cmd.split(" ");
                int id = Integer.parseInt(cmdBist[2]);
                Article foundArticle = getArticleById(id);
                if (foundArticle == null) {
                    System.out.printf("%d번 개시물은 존재하지 않습니다!\n", id);
                    continue;
                }
                System.out.println("=== 게시물 수정 ===");
                System.out.printf("수정할 제목   :\n");
                String title = sc.nextLine();
                System.out.printf("수정할 내용   :\n");
                String body = sc.nextLine();

                foundArticle.title = title;
                foundArticle.body = body;
                System.out.printf("%s번 게시물이 수정 되었습니다!\n", id);

            } else if (cmd.startsWith("article delete ")) {
                String[] cmdBist = cmd.split(" ");
                int id = Integer.parseInt(cmdBist[2]);
                Article foundArticle = getArticleById(id);
                if (foundArticle == null) {
                    System.out.printf("%d번 개시물은 존재하지 않습니다!\n", id);
                    continue;
                }
                articleList.remove(foundArticle);
                System.out.printf("%s번 게시뮬은 삭제 되었습니다!\n", id);
            } else {
                System.out.println("존재하지 않는 명령어 입니다!!");
            }

        }
        sc.close();
        System.out.println("=== 프로그램 끝 ===");
    }


    private void makeTestDate() {
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
