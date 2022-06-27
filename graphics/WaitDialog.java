package graphics;

import information.ComponentManager;

import javax.swing.*;
import java.awt.*;

public class WaitDialog extends JDialog {
    private JLabel l;
    private JLabel fileLabel;

    public WaitDialog(String file) {
        super((UI) ComponentManager.getReference("UI"), false);
        fileLabel = new JLabel(file);
        l = new JLabel("等待对方确认接受");
        this.setTitle("Waiting");
        this.setLayout(new BorderLayout());
        this.add(l, BorderLayout.NORTH);
        this.add(fileLabel, BorderLayout.CENTER);
        this.setSize(300, 200);
        this.setLocationRelativeTo((UI) ComponentManager.getReference("UI"));
    }
}