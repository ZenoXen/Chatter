package graphics;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
public class Bar extends JMenuBar implements Setable{
	private JMenu m;
	public void init() {
		m=new JMenu("²Ëµ¥");
	}
	public void set() {
		this.add(m);
	}
	public Bar() {
		init();
		set();
		addListeners();
	}
	public static void main(String[] args) {
	}
	@Override
	public void addListeners() {
	}
}