package network;
import java.io.IOException;
import java.net.*;
public class MsgBroader {
	private DatagramSocket ds;
	private DatagramPacket dp;
	public MsgBroader() {
		try {ds=new DatagramSocket(NetworkConsts.msgForthPort);} 
		catch (SocketException e) {e.printStackTrace();} 
	}
	public void sendMsg(String msg){
		if(msg==null||msg.equals("")) return;
		byte[] buf=msg.getBytes();
		dp=new DatagramPacket(buf,buf.length);
		dp.setPort(NetworkConsts.msgBackPort);
		dp.setAddress(NetworkConsts.broadAddr);
		try {ds.send(dp);} 
		catch (IOException e) {e.printStackTrace();}
	}
}