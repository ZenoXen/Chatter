package network;
import network.*;
import controllers.*;
import information.*;
import graphics.*;
import java.io.*;
import java.net.*;
import java.util.*;

import javax.swing.JOptionPane;
public class FileReciver extends Thread{
	private ServerSocket ss;
	public FileReciver() {
		try {ss=new ServerSocket(NetworkConsts.fileBackPort);} 
		catch (IOException e) {e.printStackTrace();}
	}
	public void run() {
		while(true) {
			Socket s=null;
			try {s=ss.accept();} 
			catch (IOException e) {e.printStackTrace();}
			BufferedReader br=UtilController.getReader(s);
			String file=null;
			long len=0;
			try {
				file=br.readLine();
				len=Long.parseLong(br.readLine());
			} catch (IOException e) {
				e.printStackTrace();
			}
			File f=FileController.showPrompt(file,len);
			if(f==null) {
				reply(s,false);
				continue;
			}
			reply(s,true);
			new FileReceiveThread(s,f,len).start();
		}
	}
	private void reply(Socket s,boolean flag) {
		BufferedWriter bw=UtilController.getWriter(s);
		try {
			if(flag) bw.write("yes");
			else bw.write("no");
			bw.newLine();
			bw.flush();
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
}
