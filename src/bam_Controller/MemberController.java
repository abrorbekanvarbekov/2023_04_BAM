package bam_Controller;

import Bam_Util.Util;
import bam_User.User;

import java.util.List;
import java.util.Scanner;

public class MemberController {
    private List<User> userList;
    private int lastUserId;
    private Scanner sc;
    public MemberController(List<User> userList, Scanner sc){
        this.userList = userList;
        this.sc = sc;
    }
    public void doJoin(){
        System.out.println("=== 회원  가입 ===");
        int id = lastUserId + 1;
        lastUserId = id;
        String userId = null;
        while (true) {
            System.out.println("로그인 아이디  :   ");
            userId = sc.nextLine();

            if (isUserIdDup(userId)){
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
    public void doUserList(){
        System.out.println("== 회원 목록 ==");
        if (userList.size() == 0) {
            System.out.println("회원 가입자가 없습니다!");
        }
        for (int i = userList.size() - 1; i >= 0; i--) {
            User user = userList.get(i);
            System.out.printf("%d   |   %s  |   %s    |    %s\n", user.id, user.userId, user.regDate, user.userName);
        }
    }

    private boolean isUserIdDup(String userId){
        for (User user : userList) {
            if (user.userId.equals(userId)) {
                return true;
            }
        }
        return false;
    }
}
