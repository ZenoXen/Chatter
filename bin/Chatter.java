package bin;
import graphics.UI;
import information.NickManager;
import graphics.*;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowStateListener;
import javax.swing.*;
public class Chatter {
	private UI ui;
	private void initUI() {
		ui=new UI();
		ui.setSize(800, 650);
		ui.setResizable(false);
		ui.setLocationRelativeTo(null);
	}
	public Chatter() {
		showUI();
	}
	private void showUI() {
		initUI();
		ui.setVisible(true);
	}
	public static void main(String[] args) {
		Chatter c=new Chatter();
	}
}