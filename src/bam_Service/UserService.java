package bam_Service;

import Bam_Util.Util;
import bam_Dao.MemberDao;
import bam_User.User;

public class UserService {
    private MemberDao memberDao;

    public UserService(){
        this.memberDao = new MemberDao();
    }

    public int lastUserId(){
       return memberDao.setMemberId();
    }

    public void add(User user) {
        memberDao.add(user);
    }

    public boolean isUserIdDup(String userId) {
        return memberDao.isUserIdDup(userId);
    }

    public User getUserByLoginId(String userId) {
        return memberDao.getUserByLoginId(userId);
    }

    public int userListSize() {
        return memberDao.userListSize();
    }

    public User getUser(int i) {
      return memberDao.getUser(i);
    }

    public void makeTestData() {
        memberDao.makeTestData();
    }
}
