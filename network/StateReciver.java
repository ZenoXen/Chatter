package network;
import java.util.*;

import controllers.StateController;

import java.io.*;
import java.net.*;
import graphics.*;
import information.NickManager;
public class StateReciver extends Thread{
	private DatagramSocket ds;
	private DatagramPacket dp;
	public StateReciver() {
		try {ds=new DatagramSocket(NetworkConsts.stateBackPort);} 
		catch (SocketException e) {e.printStackTrace();}
	}
	private void sendBack(InetAddress ip) {
		String nickname=NickManager.getNick();
		byte[] buf=nickname.getBytes();
		dp=new DatagramPacket(buf,buf.length);
		dp.setAddress(ip);
		dp.setPort(NetworkConsts.stateBackPort);
		try {ds.send(dp);} 
		catch (IOException e) {e.printStackTrace();}
	}
	private boolean isLocal(String fromAddress) {
		boolean addrEqual=fromAddress.equals(NetworkConsts.localAddr.getHostAddress());
		boolean loopEqual=fromAddress.equals("127.0.0.1");
		if(addrEqual&&loopEqual) return true;
		return false;
	}
	private boolean isBroadMsg(int port) {
		return port==NetworkConsts.stateForthPort;
	}
	public void run() {
		while(true) {
			dp=new DatagramPacket(new byte[1024],1024);
			try {ds.receive(dp);}
			catch (IOException e) {e.printStackTrace();}
			int port=dp.getPort();
			byte[] buf=dp.getData();
			String nickname=new String(buf);
			InetAddress from=dp.getAddress();
			String fromAddress=from.getHostAddress();
			if(isLocal(fromAddress)) return;
			if(isBroadMsg(port)) {
				sendBack(from);
				StateController.processInfo(from, nickname);
			}
			else
				StateController.processInfo(from, nickname);
		}
	}
}