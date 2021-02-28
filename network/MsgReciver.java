package network;
import graphics.ChatArea;
import java.io.IOException;
import java.net.*;

import controllers.MsgController;
public class MsgReciver extends Thread{
	private DatagramSocket ds;
	private DatagramPacket dp;
	public MsgReciver() {
		try {ds=new DatagramSocket(NetworkConsts.msgBackPort);} 
		catch (SocketException e1) {e1.printStackTrace();}
		dp=new DatagramPacket(new byte[1024],1024);		
	}
	public void run() {
		while(true) {
			try {ds.receive(dp);}
			catch (IOException e) {e.printStackTrace();}
			String fromAddress=dp.getAddress().getHostAddress();
			boolean loopEqual=fromAddress.equals(NetworkConsts.loopAddr.getHostAddress());
			boolean addrEqual=fromAddress.equals(NetworkConsts.localAddr.getHostAddress());
			if(loopEqual||addrEqual) 
				continue;
			String msg=new String(dp.getData(),0,dp.getLength());
			MsgController.messageIn(msg);
		}
	}
}