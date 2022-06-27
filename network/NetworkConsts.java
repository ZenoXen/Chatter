package network;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class NetworkConsts {
    public static final int stateForthPort = 30001;
    public static final int stateBackPort = 30002;
    public static final int msgForthPort = 30003;
    public static final int msgBackPort = 30004;
    public static final int fileBackPort = 30006;
    public static final InetAddress broadAddr;
    public static InetAddress localAddr;
    public static final InetAddress loopAddr;

    static {
        broadAddr = getAddrByStr("255.255.255.255");
        loopAddr = getAddrByStr("127.0.0.1");
        try {
            localAddr = InetAddress.getLocalHost();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }

    private static InetAddress getAddrByStr(String ipStr) {
        InetAddress ip = null;
        String[] nums = ipStr.split("\\.");
        byte[] addr = new byte[nums.length];
        for (int i = 0; i < nums.length; i++)
            addr[i] = (byte) Integer.parseInt(nums[i]);
        try {
            ip = InetAddress.getByAddress(addr);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        return ip;
    }
}