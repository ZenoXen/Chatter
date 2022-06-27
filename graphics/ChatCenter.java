package graphics;

import controllers.FileController;
import controllers.MsgController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ChatCenter extends JPanel implements Setable {
    private JButton sendBtn;
    private JButton clearBtn;
    private JButton transferBtn;

    public ChatCenter() {
        init();
        set();
        addListeners();
    }

    @Override
    public void init() {
        sendBtn = new JButton("����");
        clearBtn = new JButton("����");
        transferBtn = new JButton("�����ļ�");
    }

    @Override
    public void set() {
        this.setLayout(new FlowLayout());
        this.add(sendBtn);
        this.add(clearBtn);
        this.add(transferBtn);
    }

    @Override
    public void addListeners() {
        sendBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                MsgController.messageOut();
            }
        });
        clearBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                MsgController.messageClear();
            }
        });
        transferBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FileController.showSelect();
            }
        });
    }
}