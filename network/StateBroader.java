package network;

import information.NickManager;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

public class StateBroader {
    private DatagramSocket ds;
    private DatagramPacket dp;

    public StateBroader() {
        try {
            ds = new DatagramSocket(NetworkConsts.stateForthPort);
        } catch (SocketException e) {
            e.printStackTrace();
        }
        dp = new DatagramPacket(new byte[1024], 1024);
        byte[] buf = NickManager.getNick().getBytes();
        dp = new DatagramPacket(buf, buf.length);
        dp.setPort(NetworkConsts.stateBackPort);
        dp.setAddress(NetworkConsts.broadAddr);
    }

    public void inform() {
        try {
            ds.send(dp);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}