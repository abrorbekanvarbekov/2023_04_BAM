package bam;

import Bam_Dto.Article;
import bam_Controller.ArticleController;
import bam_Controller.MemberController;
import bam_User.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class App {
    private List<Article> articleList;
    private List<User> userList;
    private String cmd;
    public App() {
        articleList = new ArrayList<>();
        userList = new ArrayList<>();
    }

    public void run() {
        System.out.println("== 프로그램 시작 ==");
        Scanner sc = new Scanner(System.in);
        MemberController memberController = new MemberController(userList, sc);
        ArticleController articleController = new ArticleController(articleList, sc);
        articleController.makeTestDate();
        while (true) {
            System.out.println("명령어)");
            cmd = sc.nextLine();
            if (cmd.equals("exit")) {
                break;
            }
            if (cmd.equals("member join")) {
                memberController.doJoin();
            } else if (cmd.equals("member list")) {
                memberController.doUserList();
            } else if (cmd.equals("article write")) {
                articleController.doArticleWrite();
            } else if (cmd.startsWith("article list")) {
                articleController.showList(cmd);
            } else if (cmd.startsWith("article detail ")) {
                articleController.showDetail(cmd);
            } else if (cmd.startsWith("article modify ")) {
                articleController.doModify(cmd);
            } else if (cmd.startsWith("article delete ")) {
                articleController.doDelete(cmd);
            } else {
                System.out.println("존재하지 않는 명령어 입니다!!");
            }

        }
        sc.close();
        System.out.println("=== 프로그램 끝 ===");
    }


}
