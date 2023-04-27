package bam_User;

public class User {
    public int id;
    public String userId;
    public String userPw;
    public String userName;
    public String regDate;

    public User(int id,String userId, String userPw, String userName, String regDate) {
        this.id = id;
        this.userId = userId;
        this.userPw = userPw;
        this.userName = userName;
        this.regDate = regDate;
    }
}
