package graphics;

import information.ComponentManager;
import information.UserManager;

import javax.swing.*;
import java.awt.*;
import java.net.InetAddress;
import java.util.Set;

public class InfoArea extends JPanel implements Setable {
    private JTextArea info;
    private JScrollPane jsp;

    public InfoArea() {
        init();
        set();
        addListeners();
        ComponentManager.saveReference("InfoArea", this);
    }

    @Override
    public void set() {
        this.setLayout(new BorderLayout());
        this.setPreferredSize(new Dimension(190, 600));
        this.add(jsp);
        info.setEditable(false);
        info.setLineWrap(true);
    }

    @Override
    public void init() {
        info = new JTextArea();
        jsp = new JScrollPane(info);
    }

    public void reprint() {
        info.setText("");
        Set<InetAddress> set = UserManager.getKeys();
        for (InetAddress ip : set)
            info.append(UserManager.getMessage(ip) + System.lineSeparator());
    }

    @Override
    public void addListeners() {
    }
}