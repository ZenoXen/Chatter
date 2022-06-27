package information;

import java.net.InetAddress;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Set;

public class UserManager {
    private static final HashMap<InetAddress, String> onlineMsgs;
    private static final HashMap<InetAddress, String> users;
    private static final HashMap<String, InetAddress> addrs;

    static {
        onlineMsgs = new HashMap<>();
        users = new HashMap<>();
        addrs = new HashMap<>();
    }

    public static void addUser(InetAddress ip, String nickname) {
        Date d = Calendar.getInstance().getTime();
        nickname = nickname.trim();
        String text = nickname + " 加入时间: " + d;
        onlineMsgs.put(ip, text);
        users.put(ip, nickname);
        addrs.put(nickname, ip);
    }

    public static void removeUser(InetAddress ip) {
        onlineMsgs.remove(ip);
        addrs.remove(users.get(ip));
        users.remove(ip);
    }

    public static void processBroad(InetAddress ip, String nickname) {
        if (onlineMsgs.containsKey(ip))
            removeUser(ip);
        else
            addUser(ip, nickname);
    }

    public static Set<InetAddress> getKeys() {
        return onlineMsgs.keySet();
    }

    public static String getMessage(InetAddress ip) {
        return onlineMsgs.get(ip);
    }

    public static String getUser(InetAddress ip) {
        return users.get(ip);
    }

    public static InetAddress getAddr(String nickname) {
        return addrs.get(nickname);
    }
}