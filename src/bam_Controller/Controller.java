package bam_Controller;

import bam_User.User;

public abstract class Controller {
    public abstract void doAction(String cmd , String methodName);
    public abstract void makeTestData();
    public static User loginedUser;
}
