package graphics;
import java.awt.*;
import javax.swing.*;

import information.ComponentManager;
public class ChatNorth extends JPanel implements Setable{
	private JTextArea ta;
	private JScrollPane jsp;
	public ChatNorth() {
		init();
		set();
		addListeners();
		ComponentManager.saveReference("ChatNorth", this);
	}
	@Override
	public void set() {
		ta.setLineWrap(true);
		ta.setEditable(false);
		jsp=new JScrollPane(ta);
		this.setBorder(BorderFactory.createEtchedBorder());
		this.setLayout(new BorderLayout());
		this.setPreferredSize(new Dimension(600,400));
		this.add(jsp);
	}
	@Override
	public void init() {
		ta=new JTextArea();
	}
	public void addMessage(String msg) {
		ta.append(msg+System.lineSeparator());
	}
	public void clearScreen() {
		ta.setText("");
	}
	@Override
	public void addListeners() {
	}
}