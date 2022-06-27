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
        sendBtn = new JButton("发送");
        clearBtn = new JButton("清屏");
        transferBtn = new JButton("发送文件");
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
        sendBtn.addActionListener(arg0 -> MsgController.messageOut());
        clearBtn.addActionListener(arg0 -> MsgController.messageClear());
        transferBtn.addActionListener(e -> FileController.showSelect());
    }
}