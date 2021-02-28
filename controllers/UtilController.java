package controllers;
import java.util.*;
import information.UserManager;
import network.NetworkConsts;
import java.io.*;
import java.net.*;
public class UtilController {
	public static BufferedInputStream getByteIn(Socket s) {
		BufferedInputStream bis=null;
		try {
			bis=new BufferedInputStream(
					s.getInputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return bis;
	}
	public static BufferedOutputStream getByteOut(Socket s) {
		BufferedOutputStream bos=null;
		try {
			bos=new BufferedOutputStream(
					s.getOutputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return bos;
	}
	public static BufferedReader getReader(Socket s) {
		BufferedReader br=null;
		try {
			br=new BufferedReader(
				new InputStreamReader(
						s.getInputStream()));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return br;
	}
	public static BufferedWriter getWriter(Socket s) {
		BufferedWriter bw=null;
		try {
			bw=new BufferedWriter(
					new OutputStreamWriter(
							s.getOutputStream()));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return bw;
	}
	public static Socket createSocket(String nickname) {
		Socket s=null;
		try {s=new Socket(UserManager.getAddr(nickname).getHostAddress(), NetworkConsts.fileBackPort);} 
		catch (UnknownHostException e) {e.printStackTrace();}
		catch (IOException e) {e.printStackTrace();}
		return s;
	}
	public static void closeSocket(Socket s) {
		try {s.close();} 
		catch (IOException e) {e.printStackTrace();}
	}
}