package cn.edu.hfut.backend.util;

public class RandomUtil {

    public static String createGroupAccount() {

        Integer groupAccount = (int)(Math.random()*10000 +100000);
        return groupAccount.toString();
    }
}
