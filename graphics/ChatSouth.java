package graphics;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import controllers.MsgController;
import information.ComponentManager;
public class ChatSouth extends JPanel implements Setable{
	private JTextArea ta;
	private JScrollPane jsp;
	public ChatSouth() {
		init();
		set();
		addListeners();
		ComponentManager.saveReference("ChatSouth", this);
	}
	@Override
	public void set() {
		this.setLayout(new BorderLayout());
		this.setPreferredSize(new Dimension(600,150));
		ta.setLineWrap(true);
		this.add(jsp);
	}
	@Override
	public void init() {
		ta=new JTextArea();
		jsp=new JScrollPane(ta);
	}
	public void clearInput() {
		ta.setText("");
	}
	public String getContent() {
		return ta.getText();
	}
	@Override
	public void addListeners() {
		ta.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode()==KeyEvent.VK_ENTER)
					if(e.isControlDown())
						MsgController.messageOut();
			}
		});
	}
}
