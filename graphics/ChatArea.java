package graphics;

import javax.swing.*;
import java.awt.*;

public class ChatArea extends JPanel implements Setable {
    private ChatNorth cn;
    private ChatCenter ca;
    private ChatSouth cs;

    public ChatArea() {
        init();
        set();
        addListeners();
    }

    public void init() {
        cn = new ChatNorth();
        cs = new ChatSouth();
        ca = new ChatCenter();
    }

    public void set() {
        this.setLayout(new BorderLayout());
        this.add(cn, BorderLayout.NORTH);
        this.add(ca, BorderLayout.CENTER);
        this.add(cs, BorderLayout.SOUTH);
    }

    @Override
    public void addListeners() {
    }
}