package graphics;
import javax.swing.*;
import controllers.FileController;
import information.NickManager;
import information.UserManager;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.InetAddress;
import java.util.Set;
public class SelectDialog extends JDialog implements Setable{
	private JComboBox<String> cbx;
	private JButton confirm;
	public SelectDialog(JFrame f,String title,boolean mode) {
		super(f,title,mode);
		init();
		set();
		addListeners();
	}
	@Override
	public void init() {
		cbx=new JComboBox<String>();
		confirm=new JButton("»∑∂®");
	}
	@Override
	public void set() {
		this.setLayout(new FlowLayout());
		this.add(cbx);
		this.add(confirm);
		this.setSize(200,100);
		this.setVisible(false);
	}
	public void refreshUsers() {
		Set<InetAddress> ipSet=UserManager.getKeys();
		cbx.removeAllItems();
		cbx.addItem("-«Î—°‘Ò-");
		for(InetAddress ip:ipSet) {
			String user=UserManager.getUser(ip);
			if(!user.equals(NickManager.getNick()))
				cbx.addItem(user);
		}
	}
	private void closeSelect() {
		this.setVisible(false);
	}
	@Override
	public void addListeners() {
		confirm.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				closeSelect();
				FileController.fileOut((String)cbx.getSelectedItem());
			}
		});
	}
}
