package information;

public class NickManager {
    private static String nickname;

    public static String getNick() {
        return nickname;
    }

    public static void setNick(String n) {
        nickname = n;
    }
}