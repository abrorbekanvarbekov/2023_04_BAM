package bam;

import Bam_Dto.Article;
import bam_Controller.ArticleController;
import bam_Controller.Controller;
import bam_Controller.MemberController;
import bam_User.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class App {
    private List<Article> articleList;
    private List<User> userList;
    public App() {
        articleList = new ArrayList<>();
        userList = new ArrayList<>();
    }

    public void run() {
        System.out.println("== 프로그램 시작 ==");
        Scanner sc = new Scanner(System.in);
        MemberController memberController = new MemberController(userList, sc);
        ArticleController articleController = new ArticleController(articleList, sc);
        articleController.makeTestData();
        memberController.makeTestData();

        while (true) {
            System.out.println("명령어)");
            String cmd = sc.nextLine();
            if (cmd.equals("exit")) {
                break;
            }
            Controller controller = null;

            String[] cmdBits = cmd.split(" ");

            if (cmdBits.length == 1){
                System.out.println("명령어를 확인하세요!");
                continue;
            }
            String controllerName = cmdBits[0];
            String methodName = cmdBits[1];

            if(controllerName.equals("article")){
                controller = articleController;
            } else if (controllerName.equals("member")) {
                controller = memberController;
            }else {
                System.out.println("존재하지 않는 명령어 입니다!!");
                continue;
            }

            controller.doAction(cmd, methodName);
//
//            if (cmd.equals("member join")) {
//                memberController.doJoin();
//            } else if (cmd.equals("member list")) {
//                memberController.doUserList();
//            } else if (cmd.equals("article write")) {
//                articleController.doArticleWrite();
//            } else if (cmd.startsWith("article list")) {
//                articleController.showList(cmd);
//            } else if (cmd.startsWith("article detail ")) {
//                articleController.showDetail(cmd);
//            } else if (cmd.startsWith("article modify ")) {
//                articleController.doModify(cmd);
//            } else if (cmd.startsWith("article delete ")) {
//                articleController.doDelete(cmd);
//            } else {
//                System.out.println("존재하지 않는 명령어 입니다!!");
//            }

        }
        sc.close();
        System.out.println("=== 프로그램 끝 ===");
    }


}
