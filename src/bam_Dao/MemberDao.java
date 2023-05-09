package bam_Dao;

import Bam_Util.Util;
import bam_User.User;

import java.util.ArrayList;
import java.util.List;

public class MemberDao {
    private List<User> userList;
    private int lastUserId;

    public MemberDao(){
        this.userList = new ArrayList<>();
        this.lastUserId = 0;
    }

    public int setMemberId(){
        int id = lastUserId + 1;
        lastUserId = id;
        return id;
    }

    public void add(User user) {
        userList.add(user);
    }

    public boolean isUserIdDup(String userId) {
        for (User user : userList) {
            if (user.userId.equals(userId)) {
                return true;
            }
        }
        return false;
    }

    public User getUserByLoginId(String userId) {
        for (User user : userList) {
            if (user.userId.equals(userId)) {
                return user;
            }
        }
        return null;
    }

    public int userListSize() {
        return userList.size();
    }

    public User getUser(int i) {
        User user =  userList.get(i);
        return user;
    }

    public void makeTestData() {
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
