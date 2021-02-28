package graphics;
import javax.swing.*;
import java.io.*;
import java.net.*;
import java.awt.*;
import java.util.*;
import information.*;
import network.*;
import graphics.*;
import controllers.*;
import java.awt.event.*;
public class ProgressDialog extends JDialog{
	private JLabel fileName;
	private JProgressBar jpb;
	private JButton btn;
	public ProgressDialog(JFrame f,String title,boolean mode,String fileName,boolean isSend,Thread t) {
		super(f,title,mode);
		this.fileName=new JLabel(fileName);
		jpb=new JProgressBar();
		jpb.setIndeterminate(false);
		jpb.setStringPainted(true);
		jpb.setString("传输中 "+0+"%");
		btn=new JButton("取消");
		this.setSize(300, 200);
		this.setLayout(new BorderLayout());
		this.add(this.fileName,BorderLayout.NORTH);
		this.add(jpb,BorderLayout.CENTER);
		this.add(btn,BorderLayout.SOUTH);
		addListeners(isSend,t);
		this.setLocationRelativeTo(ComponentManager.getReference("UI"));
	}
	public void setValue(int val) {
		jpb.setValue(val);
		jpb.setString("传输中 "+val+"%");
	}
	private void addListeners(boolean isSend,Thread t) {
		btn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(isSend) ((FileSendThread)t).terminate();
				else ((FileReceiveThread)t).terminate();
			}
		});
	}
}