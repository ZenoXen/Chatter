package graphics;
import java.awt.*;
import controllers.*;
import graphics.*;
import information.ComponentManager;
import network.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.*;
import bin.*;
public class UI extends JFrame implements Setable{
	private Bar b;
	private ChatArea ca;
	private InfoArea ia;
	protected void finalize() throws Throwable {
		StateController.broad();
	}
	public void init() {
		ca=new ChatArea();
		ia=new InfoArea();
		b=new Bar();
	}
	public void set() {
		this.setTitle("Chatter");
		this.setJMenuBar(b);
		this.setLayout(new BorderLayout());
		this.add(ca,BorderLayout.WEST);
		this.add(ia,BorderLayout.EAST);
		StateController.setController();
		StateController.broad();
		MsgController.setController();
		ComponentManager.saveReference("UI",this);
		FileController.setController();
	}
	public UI() {
		init();
		set();
		addListeners();
	}
	@Override
	public void addListeners() {
		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				StateController.broad();
				System.exit(0);
			}
		});
	}
}