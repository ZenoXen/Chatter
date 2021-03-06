package information;
import java.util.*;
import java.net.*;
import java.io.*;
public class UserManager {
	private static HashMap<InetAddress,String> onlineMsgs;
	private static HashMap<InetAddress,String> users;
	private static HashMap<String,InetAddress> addrs;
	static {
		onlineMsgs=new HashMap<InetAddress,String>();
		users=new HashMap<InetAddress,String>();
		addrs=new HashMap<String,InetAddress>();
	}
	public static void addUser(InetAddress ip,String nickname) {
		Date d=Calendar.getInstance().getTime();
		nickname=nickname.trim();
		String text=nickname+" ����ʱ��: "+d.toString();
		onlineMsgs.put(ip, text);
		users.put(ip, nickname);
		addrs.put(nickname, ip);
	}
	public static void removeUser(InetAddress ip) {
		onlineMsgs.remove(ip);
		addrs.remove(users.get(ip));
		users.remove(ip);
	}
	public static void processBroad(InetAddress ip,String nickname) {
		if(onlineMsgs.containsKey(ip))
			removeUser(ip);
		else
			addUser(ip,nickname);
	}
	public static Set<InetAddress> getKeys(){
		return onlineMsgs.keySet();
	}
	public static String getMessage(InetAddress ip) {
		return onlineMsgs.get(ip);
	}
	public static String getUser(InetAddress ip) {
		return users.get(ip);
	}
	public static InetAddress getAddr(String nickname) {
		InetAddress addr=addrs.get(nickname);
		return addr;
	}
}