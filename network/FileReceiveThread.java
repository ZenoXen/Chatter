package network;

import controllers.UtilController;
import graphics.ProgressDialog;
import graphics.UI;
import information.ComponentManager;
import information.Terminable;

import javax.swing.*;
import java.io.*;
import java.net.Socket;
import java.net.SocketException;

public class FileReceiveThread extends Thread implements Terminable {
    private Socket s;
    private File file;
    private long totalLen;
    private volatile boolean exit;

    public FileReceiveThread(Socket s, File f, long len) {
        this.s = s;
        this.file = f;
        totalLen = len;
        exit = false;
    }

    public void terminate() {
        exit = true;
    }

    public void run() {
        try {
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        BufferedInputStream bis = UtilController.getByteIn(s);
        BufferedOutputStream bos = null;
        try {
            bos = new BufferedOutputStream(
                    new FileOutputStream(file));
        } catch (FileNotFoundException e1) {
            e1.printStackTrace();
        }
        byte[] buf = new byte[1024 * 1024];
        int len;
        ProgressDialog pd = new ProgressDialog((UI) ComponentManager.getReference("UI"),
                "文件传输", false,
                file.getName(), false, this);
        try {
            pd.setVisible(true);
            long currentLen = 0;
            int progress = 0;
            while (!exit && (len = bis.read(buf)) != -1) {
                bos.write(buf, 0, len);
                currentLen += len;
                int newProgress = (int) (((double) currentLen / totalLen) * 100);
                if (newProgress > progress) {
                    progress = newProgress;
                    pd.setValue(progress);
                }
            }
            pd.setVisible(false);
        } catch (SocketException se) {
            pd.setVisible(false);
            JOptionPane.showMessageDialog(ComponentManager.getReference("UI"), "对方已停止发送");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                bos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        UtilController.closeSocket(s);
    }
}