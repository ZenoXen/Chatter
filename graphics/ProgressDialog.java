package graphics;

import information.ComponentManager;
import network.FileReceiveThread;
import network.FileSendThread;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ProgressDialog extends JDialog {
    private final JLabel fileName;
    private final JProgressBar jpb;
    private final JButton btn;

    public ProgressDialog(JFrame f, String title, boolean mode, String fileName, boolean isSend, Thread t) {
        super(f, title, mode);
        this.fileName = new JLabel(fileName);
        jpb = new JProgressBar();
        jpb.setIndeterminate(false);
        jpb.setStringPainted(true);
        jpb.setString("传输中 " + 0 + "%");
        btn = new JButton("取消");
        this.setSize(300, 200);
        this.setLayout(new BorderLayout());
        this.add(this.fileName, BorderLayout.NORTH);
        this.add(jpb, BorderLayout.CENTER);
        this.add(btn, BorderLayout.SOUTH);
        addListeners(isSend, t);
        this.setLocationRelativeTo(ComponentManager.getReference("UI"));
    }

    public void setValue(int val) {
        jpb.setValue(val);
        jpb.setString("传输中 " + val + "%");
    }

    private void addListeners(boolean isSend, Thread t) {
        btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                if (isSend) ((FileSendThread) t).terminate();
                else ((FileReceiveThread) t).terminate();
            }
        });
    }
}