package bam_Controller;

import Bam_Util.Util;
import bam_User.User;

import java.util.List;
import java.util.Scanner;

public class MemberController extends Controller {
    private List<User> userList;
    private int lastUserId;
    private Scanner sc;

    public MemberController(List<User> userList, Scanner sc) {
        this.userList = userList;
        this.sc = sc;
        loginedUser = null;
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
        if (loginedUser != null){
            System.out.println("로그인 아웃 후 사용해주세요!");
            return;
        }

        System.out.println("=== 회원  가입 ===");
        int id = lastUserId + 1;
        lastUserId = id;
        String userId = null;
        while (true) {
            System.out.println("로그인 아이디  :   ");
            userId = sc.nextLine();

            if (isUserIdDup(userId)) {
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
        userList.add(user);
        System.out.printf("%s 회원님이 가입되었습니다. \n", userId);

    }

    private void doLogin() {
        if (loginedUser != null){
            System.out.println("로그인 아웃 후 사용해주세요!");
            return;
        }

        System.out.println("== 로그인 페이지 ==");

        System.out.println("로그인 아이디    :");
        String userId = sc.nextLine();
        System.out.println("로그인 비밀번호    :");
        String userPw = sc.nextLine();

        User user = isUserPWEq(userId);
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
        if(loginedUser == null){
            System.out.println("로그인 상태가 아닙니다!");
            return;
        }
        loginedUser = null;
        System.out.println("로그아웃 되었습니다!");
    }

    private void doUserList() {
        System.out.println("== 회원 목록 ==");
        if (userList.size() == 0) {
            System.out.println("회원 가입자가 없습니다!");
        }
        for (int i = userList.size() - 1; i >= 0; i--) {
            User user = userList.get(i);
            System.out.printf("%d   |   %s  |   %s    |    %s \n", user.id, user.userId, user.userName, user.regDate);
        }
    }


    private boolean isUserIdDup(String userId) {
        for (User user : userList) {
            if (user.userId.equals(userId)) {
                return true;
            }
        }
        return false;
    }

    private User isUserPWEq(String userId) {
        for (User user : userList) {
            if (user.userId.equals(userId)) {
                return user;
            }
        }
        return null;
    }

    @Override
    public void makeTestData() {
        System.out.println("테스트용 회원 데이터가 3 개 만들어졌습니다.");

        for (int i = 1; i <= 3; i++) {
            int id = lastUserId + 1;
            lastUserId = id;
            String userId = "userId" + i;
            String userPw = "userPw" + i;
            String name = "사용자" + i;
            User user = new User(id, userId, userPw, name, Util.getDateStr());
            userList.add(user);
        }
    }

}
