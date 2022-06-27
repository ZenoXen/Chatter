package graphics;

import hanlders.FileHanlder;
import hanlders.MsgHandler;

import javax.swing.*;
import java.awt.*;

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
        sendBtn.addActionListener(arg0 -> MsgHandler.messageOut());
        clearBtn.addActionListener(arg0 -> MsgHandler.messageClear());
        transferBtn.addActionListener(e -> FileHanlder.showSelect());
    }
}