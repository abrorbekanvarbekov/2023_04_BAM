package bam_Service;

import bam_Dao.UserDao;
import bam_User.User;
import bam_container.Container;

public class UserService {
    private UserDao userDao;

    public UserService(){
        this.userDao = Container.userDao;
    }

    public int lastUserId(){
       return userDao.setMemberId();
    }

    public void add(User user) {
        userDao.add(user);
    }

    public boolean isUserIdDup(String userId) {
        return userDao.isUserIdDup(userId);
    }

    public User getUserByLoginId(String userId) {
        return userDao.getUserByLoginId(userId);
    }

    public int userListSize() {
        return userDao.userListSize();
    }

    public User getUser(int i) {
      return userDao.getUser(i);
    }

    public void makeTestData() {
        userDao.makeTestData();
    }

    public String getWriterName(int userId) {
        return userDao.getWriterName(userId);
    }
}
