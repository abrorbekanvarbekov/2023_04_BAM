package Basic_Articile_Manager;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class Main {
    static List<Article> articleList;
    static int articlesId;

    static {
        articleList = new ArrayList<>();
        articlesId = 0;
    }

    public static void main(String[] args) {
        System.out.println("== 프로그램 시작 ==");
        maketestDate();
        Scanner sc = new Scanner(System.in);

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
                String regDate = Util.getDateStr();
                Article article = new Article(id, regDate ,title, body);
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
                if (foundArticle == null) {
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
                for (Article article : articleList) {
                    if (article.id == id) {
                        foundArticle = article;
                        break;
                    }
                }

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
            } else if (cmd.startsWith("article delete ")) {
                String[] cmdBist = cmd.split(" ");
                int id = Integer.parseInt(cmdBist[2]);
                Article foundArticle = null;
                for (Article article : articleList) {
                    if (article.id == id) {
                        foundArticle = article;
                        break;
                    }
                }
                if(foundArticle == null){
                    System.out.printf("%d번 개시물은 존재하지 않습니다!\n", id);
                    continue;
                }
                articleList.remove(foundArticle);
                System.out.printf("%d번 게시물이 삭제 되었습니다!\n", id);
            } else {
                System.out.println("존재하지 않는 명령어 입니다!!");
            }

        }
        sc.close();
        System.out.println("=== 프로그램 끝 ===");
    }

    private static void maketestDate() {
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
}

class Article {
    int id;
    String title;
    String body;
    String regDate;

//    public Article(int id, String dateStr, String title, String body) {
//        SimpleDateFormat formatter = new SimpleDateFormat("yyyy년 MM월 dd날 HH시 mm분 ss초");
//        Date now = new Date();
//        regDate = formatter.format(now);
//    }

    public Article(int id, String regDate, String title, String body) {
//        this(id, Util.getDateStr(), title, body);
        this.regDate = regDate;
        this.id = id;
        this.title = title;
        this.body = body;
    }
}