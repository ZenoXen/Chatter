package graphics;

import information.ComponentManager;

import javax.swing.*;
import java.awt.*;

public class WaitDialog extends JDialog {

    public WaitDialog(String file) {
        super((UI) ComponentManager.getReference("UI"), false);
        JLabel fileLabel = new JLabel(file);
        JLabel l = new JLabel("�ȴ��Է�ȷ�Ͻ���");
        this.setTitle("Waiting");
        this.setLayout(new BorderLayout());
        this.add(l, BorderLayout.NORTH);
        this.add(fileLabel, BorderLayout.CENTER);
        this.setSize(300, 200);
        this.setLocationRelativeTo(ComponentManager.getReference("UI"));
    }
}