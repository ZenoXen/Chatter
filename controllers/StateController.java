package controllers;
import information.*;
import network.*;
import java.net.InetAddress;
import java.net.UnknownHostException;

import javax.swing.JOptionPane;

import graphics.*;
public class StateController {
	private static StateReciver sr;
	private static StateBroader sb;
	public static void setController() {
		inputNick();
		if(!checkValid()) System.exit(0);
		try {UserManager.addUser(InetAddress.getLocalHost(),NickManager.getNick());} 
		catch (UnknownHostException e) {e.printStackTrace();}
		sr=new StateReciver();
		sb=new StateBroader();
		sr.start();
	}
	public static void processInfo(InetAddress ip,String nickname) {
		UserManager.processBroad(ip, nickname);
		((InfoArea)ComponentManager.getReference("InfoArea")).reprint();
	}
	public static void broad() {
		sb.inform();
	}
	private static boolean checkValid() {
		String nickname=NickManager.getNick();
		if(nickname!=null) nickname=nickname.trim();
		return nickname!=null&&!nickname.equals("");
	}
	private static void inputNick() {
		String nickname=JOptionPane.showInputDialog(" ‰»Îƒ„µƒ¡ƒÃÏÍ«≥∆");
		NickManager.setNick(nickname);
	}
}