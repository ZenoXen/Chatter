package hanlders;

import graphics.ChatNorth;
import graphics.ChatSouth;
import information.ComponentManager;
import information.MsgHead;
import information.NickManager;
import network.MsgBroader;
import network.MsgReciver;

import java.util.Calendar;
import java.util.Date;

public class MsgHandler {
    private static MsgBroader mb;

    public static void setController() {
        mb = new MsgBroader();
        MsgReciver mr = new MsgReciver();
        mr.start();
    }

    public static void messageIn(String msg) {
        ChatNorth cn = (ChatNorth) (ComponentManager.getReference("ChatNorth"));
        cn.addMessage(msg);
    }

    public static void messageOut() {
        ChatSouth cs = (ChatSouth) (ComponentManager.getReference("ChatSouth"));
        ChatNorth cn = (ChatNorth) (ComponentManager.getReference("ChatNorth"));
        String content = cs.getContent();
        if (content == null || content.equals("")) return;
        StringBuilder sb = new StringBuilder();
        Date d = Calendar.getInstance().getTime();
        MsgHead mh = new MsgHead(NickManager.getNick(), d.toString());
        sb.append(mh);
        sb.append(System.lineSeparator());
        sb.append(content);
        String msg = sb.toString();
        mb.sendMsg(msg);
        cn.addMessage(msg);
        cs.clearInput();
    }

    public static void messageClear() {
        ChatNorth cn = (ChatNorth) (ComponentManager.getReference("ChatNorth"));
        cn.clearScreen();
    }
}
