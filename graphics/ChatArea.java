package graphics;
import java.util.*;
import java.awt.*;
import java.awt.event.*;
import java.net.*;
import java.util.Calendar;
import javax.swing.*;

import controllers.MsgController;
import network.*;

import java.io.*;
public class ChatArea extends JPanel implements Setable{
	private ChatNorth cn;
	private ChatCenter ca;
	private ChatSouth cs;
	public void init() {
		cn=new ChatNorth();
		cs=new ChatSouth();
		ca=new ChatCenter();
	}
	public void set() {
		this.setLayout(new BorderLayout());
		this.add(cn,BorderLayout.NORTH);
		this.add(ca,BorderLayout.CENTER);
		this.add(cs,BorderLayout.SOUTH);
	}
	public ChatArea() {
		init();
		set();
		addListeners();
	}
	@Override
	public void addListeners() {
	}
}