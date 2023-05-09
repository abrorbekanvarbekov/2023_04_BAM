package bam_Controller;

import Bam_Util.Util;
import bam_Service.UserService;
import bam_User.User;
import bam_container.Container;

import java.util.Scanner;

public class MemberController extends Controller {

    private UserService userService;
    private Scanner sc;

    public MemberController(Scanner sc) {
        this.sc = sc;
        loginedUser = null;
        userService = Container.userService;
    }

    @Override
    public void doAction(String cmd, String methodName) {
        switch (methodName) {
            case "join":
                doJoin();
                break;
            case "list":
                doUserList();
                break;
            case "login":
                doLogin();
                break;
            case "logout":
                doLogOut();
                break;
            default:
                System.out.println("명령어를 확인해주세요!");
                break;
        }
    }


    private void doJoin() {
        System.out.println("=== 회원  가입 ===");
        int id = userService.lastUserId();

        String userId = null;
        while (true) {
            System.out.println("로그인 아이디  :   ");
            userId = sc.nextLine();

            if (userService.isUserIdDup(userId)) {
                System.out.printf("%s은 (는) 이미 사용중인 아이디입니다!\n", userId);
                continue;
            }
            System.out.printf("%s은 (는) 사용가능한 아이디입니다!\n", userId);
            break;
        }
        System.out.println("로그인 비밀번호  :   ");
        String userPw = sc.nextLine();
        while (true) {
            System.out.println("로그인 비밀번호  확인:   ");
            String userCHeckPw = sc.nextLine();
            if (userPw.equals(userCHeckPw) == false) {
                System.out.println("비밀번호를 확인해주세요!");
                continue;
            }
            break;
        }
        System.out.println("이름 :   ");
        String userName = sc.nextLine();
        String regDate = Util.getDateStr();
        User user = new User(id, userId, userPw, userName, regDate);
        userService.add(user);
        System.out.printf("%s 회원님이 가입되었습니다. \n", userId);

    }

    private void doLogin() {

        System.out.println("== 로그인 페이지 ==");

        System.out.println("로그인 아이디    :");
        String userId = sc.nextLine();
        System.out.println("로그인 비밀번호    :");
        String userPw = sc.nextLine();

        User user = userService.getUserByLoginId(userId);
        if (user == null) {
            System.out.println("로그인 아이디가 일치하지 않습니다!");
            return;
        }

        if (user.userPw.equals(userPw) == false){
            System.out.println("로그인 비밀번호가 일치하지 않습니다!");
            return;
        }
        loginedUser = user;
        System.out.printf("%s님  환영합니다!\n",user.userName);
        return;
    }

    private void doLogOut() {
        loginedUser = null;
        System.out.println("로그아웃 되었습니다!");
    }

    private void doUserList() {
        System.out.println("== 회원 목록 ==");
        if (userService.userListSize() == 0) {
            System.out.println("회원 가입자가 없습니다!");
        }
        for (int i = userService.userListSize() - 1; i >= 0; i--) {
            User user = userService.getUser(i);
            System.out.printf("%d   |   %s  |   %s    |    %s \n", user.id, user.userId, user.userName, user.regDate);
        }
    }



    @Override
    public void makeTestData() {
        System.out.println("테스트용 회원 데이터가 3 개 만들어졌습니다.");
        userService.makeTestData();
    }

}
