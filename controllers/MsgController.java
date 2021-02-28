package controllers;
import java.net.*;
import java.util.Calendar;
import java.util.*;
import graphics.*;
import network.*;
import information.*;
public class MsgController {
	private static MsgReciver mr;
	private static MsgBroader mb;
	public static void setController() {
		mb=new MsgBroader();
		mr=new MsgReciver();
		mr.start();
	}
	public static void messageIn(String msg) {
		ChatNorth cn=(ChatNorth)(ComponentManager.getReference("ChatNorth"));
		cn.addMessage(msg);
	}
	public static void messageOut() {
		ChatSouth cs=(ChatSouth)(ComponentManager.getReference("ChatSouth"));
		ChatNorth cn=(ChatNorth)(ComponentManager.getReference("ChatNorth"));
		String content=cs.getContent();
		if(content==null||content.equals("")) return;
		StringBuilder sb=new StringBuilder("");
		Date d=Calendar.getInstance().getTime();
		MsgHead mh=new MsgHead(NickManager.getNick(),d.toString());
		sb.append(mh.toString());
		sb.append(System.lineSeparator());
		sb.append(content);
		String msg=sb.toString();
		mb.sendMsg(msg);
		cn.addMessage(msg);
		cs.clearInput();
	}
	public static void messageClear() {
		ChatNorth cn=(ChatNorth)(ComponentManager.getReference("ChatNorth"));
		cn.clearScreen();
	}
}
