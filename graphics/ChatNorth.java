package graphics;

import information.ComponentManager;

import javax.swing.*;
import java.awt.*;

public class ChatNorth extends JPanel implements Setable {
    private JTextArea ta;

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
        JScrollPane jsp = new JScrollPane(ta);
        this.setBorder(BorderFactory.createEtchedBorder());
        this.setLayout(new BorderLayout());
        this.setPreferredSize(new Dimension(600, 400));
        this.add(jsp);
    }

    @Override
    public void init() {
        ta = new JTextArea();
    }

    public void addMessage(String msg) {
        ta.append(msg + System.lineSeparator());
    }

    public void clearScreen() {
        ta.setText("");
    }

    @Override
    public void addListeners() {
    }
}