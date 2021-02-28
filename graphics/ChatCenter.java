package graphics;
import java.awt.*;
import java.awt.event.*;
import java.util.Calendar;
import java.util.*;
import javax.swing.*;

import controllers.FileController;
import controllers.MsgController;
import information.MsgHead;
import information.NickManager;
public class ChatCenter extends JPanel implements Setable{
	private JButton sendBtn;
	private JButton clearBtn;
	private JButton transferBtn;
	public ChatCenter() {
		init();
		set();
		addListeners();
	}
	@Override
	public void init() {
		sendBtn=new JButton("发送");
		clearBtn=new JButton("清屏");
		transferBtn=new JButton("发送文件");
	}
	@Override
	public void set() {
		this.setLayout(new FlowLayout());
		this.add(sendBtn);
		this.add(clearBtn);
		this.add(transferBtn);
	}
	@Override
	public void addListeners() {
		sendBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				MsgController.messageOut();
			}
		});
		clearBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				MsgController.messageClear();
			}
		});
		transferBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				FileController.showSelect();
			}
		});
	}
}