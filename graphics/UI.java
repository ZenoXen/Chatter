package graphics;

import hanlders.FileHanlder;
import hanlders.MsgHandler;
import hanlders.StateHandler;
import information.ComponentManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class UI extends JFrame implements Setable {
    private Bar b;
    private ChatArea ca;
    private InfoArea ia;

    public UI() {
        init();
        set();
        addListeners();
    }

    protected void finalize() {
        StateHandler.broad();
    }

    public void init() {
        ca = new ChatArea();
        ia = new InfoArea();
        b = new Bar();
    }

    public void set() {
        this.setTitle("Chatter");
        this.setJMenuBar(b);
        this.setLayout(new BorderLayout());
        this.add(ca, BorderLayout.WEST);
        this.add(ia, BorderLayout.EAST);
        StateHandler.setController();
        StateHandler.broad();
        MsgHandler.setController();
        ComponentManager.saveReference("UI", this);
        FileHanlder.setController();
    }

    @Override
    public void addListeners() {
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                StateHandler.broad();
                System.exit(0);
            }
        });
    }
}