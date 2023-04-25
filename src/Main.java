import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("== 프로그램 시작 ==");

        Scanner sc = new Scanner(System.in);
        List<Article> articleList = new ArrayList<>();
        int articlesId = 0;
        while(true){
            System.out.println("명령어)");
            String cmd = sc.nextLine();
            if (cmd.equals("exit")){
                break;
            }

            if (cmd.equals("article write")){
                int id = articlesId + 1;
                articlesId = id;
                System.out.println("내용  :   ");
                String title = sc.nextLine();
                System.out.println("제목  :   ");
                String body = sc.nextLine();

                Article article = new Article(id, title, body);
                articleList.add(article);
                System.out.printf("%d 번 글이 생성되었습니다.\n", id);

            } else if (cmd.equals("article list")) {
                if (articleList.size() == 0){
                    System.out.println("존재하는 개시물이 없습니다!");
                    continue;
                }

                System.out.println("번호  |   제목  ");
                for (int i = articleList.size() - 1; i >= 0; i--) {
                    Article article = articleList.get(i);
                    System.out.printf("%d   |   %s  \n", article.id, article.title);
                }
            }

        }
        sc.close();
        System.out.println("== 프로그램 끝 ==");
    }
}

class Article{
    int id;
    String title;
    String body;

    public Article(int id, String title, String body) {
        this.id = id;
        this.title = title;
        this.body = body;
    }
}