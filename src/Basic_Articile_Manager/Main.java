package Basic_Articile_Manager;

import java.awt.geom.Arc2D;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("== 프로그램 시작 ==");

        Scanner sc = new Scanner(System.in);
        List<Article> articleList = new ArrayList<>();

        int articlesId = 0;
        while (true) {
            System.out.println("명령어)");
            String cmd = sc.nextLine();
            if (cmd.equals("exit")) {
                break;
            }
            if (cmd.equals("article write")) {
                int id = articlesId + 1;
                articlesId = id;

                System.out.println("=== 게시물  작성 ===");
                System.out.println("내용  :   ");
                String title = sc.nextLine();
                System.out.println("제목  :   ");
                String body = sc.nextLine();
//                String regDate = Util.getDateStr();
                Article article = new Article(id, title, body);
                articleList.add(article);
                System.out.printf("%d 번 글이 생성되었습니다.\n", id);

            } else if (cmd.equals("article list")) {
                if (articleList.size() == 0) {
                    System.out.println("존재하는 개시물이 없습니다!");
                    continue;
                }

                System.out.println("=== 게시물 목록 ===");
                System.out.println("번호  |   제목  |   작성일   ");
                for (int i = articleList.size() - 1; i >= 0; i--) {
                    Article article = articleList.get(i);
                    System.out.printf("%d   |   %s  |   %s\n", article.id, article.title, article.regDate);
                }
            } else if (cmd.startsWith("article detail ")) {
                String[] cmdBits = cmd.split(" ");
                int id = Integer.parseInt(cmdBits[2]);
                Article foundArticle = null;
                for (Article article : articleList) {
                    if (article.id == id) {
                        foundArticle = article;
                        break;
                    }
                }
                if (foundArticle == null){
                    System.out.printf("%d 번 개시물은 존재하지 않습니다.\n", id);
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
                Article foundArticle = null;
                for (Article article: articleList) {
                    if (article.id == id){
                        foundArticle = article;
                        break;
                    }
                }

                if (foundArticle == null){
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
            } else if (cmd.startsWith("article delete ")) {
                String[] cmdBist = cmd.split(" ");
                int id = Integer.parseInt(cmdBist[2]);

                for (Article article : articleList){
                    if (article.id == id){
                        articleList.remove(article);
                        System.out.printf("%d번 개시물이 삭제 되었습니다!\n", id);
                        break;
                    }else {
                        System.out.printf("%d번 개시물은 존재하지 않습니다!\n", id);
                        continue;
                    }
                }
            } else {
                System.out.println("존재하지 않는 명령어 입니다!!");
            }

        }
        sc.close();
        System.out.println("=== 프로그램 끝 ===");
    }
}

class Article {
    int id;
    String title;
    String body;
    static String regDate;
    public Article(){
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy년 MM월 dd날 HH시 mm분 ss초");
        Date now = new Date();
        regDate = formatter.format(now);
    }
    public Article(int id, String title, String body) {
        this();
        this.id = id;
        this.title = title;
        this.body = body;
    }
}