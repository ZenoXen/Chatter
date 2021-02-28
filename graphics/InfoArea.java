package graphics;
import java.awt.*;
import java.util.*;
import java.awt.event.*;
import javax.swing.*;

import information.UserManager;
import information.ComponentManager;
import information.NickManager;
import network.*;

import java.io.*;
import java.net.*;
public class InfoArea extends JPanel implements Setable{
	private JTextArea info;
	private JScrollPane jsp;
	@Override
	public void set() {
		this.setLayout(new BorderLayout());
		this.setPreferredSize(new Dimension(190,600));
		this.add(jsp);
		info.setEditable(false);
		info.setLineWrap(true);
	}
	@Override
	public void init() {
		info=new JTextArea();
		jsp=new JScrollPane(info);
	}
	public InfoArea() {
		init();
		set();
		addListeners();
		ComponentManager.saveReference("InfoArea", this);
	}
	public void reprint() {
		info.setText("");
		Set<InetAddress> set=UserManager.getKeys();
		for(InetAddress ip:set)
			info.append(UserManager.getMessage(ip)+System.lineSeparator());
	}
	@Override
	public void addListeners() {
	}
}