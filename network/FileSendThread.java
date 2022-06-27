package network;

import controllers.UtilController;
import graphics.ProgressDialog;
import graphics.UI;
import graphics.WaitDialog;
import information.ComponentManager;
import information.Terminable;

import javax.swing.*;
import java.io.*;
import java.net.Socket;
import java.net.SocketException;

public class FileSendThread extends Thread implements Terminable {
    private final Socket s;
    private final File file;
    private volatile boolean exit;

    public FileSendThread(Socket s, File file) {
        this.s = s;
        this.file = file;
        exit = false;
    }

    public void run() {
        WaitDialog wd = new WaitDialog(file.getName());
        wd.setVisible(true);
        sendInfo();
        if (!getResponse()) {
            UtilController.closeSocket(s);
            wd.setVisible(false);
            JOptionPane.showMessageDialog(ComponentManager.getReference("UI")
                    , "对方拒接接受");
            return;
        }
        wd.setVisible(false);
        sendFile();
        UtilController.closeSocket(s);
    }

    public void terminate() {
        exit = true;
    }

    private void sendFile() {
        BufferedInputStream bis = null;
        BufferedOutputStream bos;
        ProgressDialog pd = new ProgressDialog((UI) ComponentManager.getReference("UI"),
                "文件传输", false,
                file.getName(), true, this);
        try {
            bis = new BufferedInputStream(
                    new FileInputStream(file));
            bos = UtilController.getByteOut(s);
            byte[] buf = new byte[1024 * 1024];
            int len;
            pd.setVisible(true);
            long totalLen = file.length(), currentLen = 0;
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
            JOptionPane.showMessageDialog(ComponentManager.getReference("UI"), "对方已终止接受");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                bis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            s.shutdownOutput();
        } catch (IOException e) {
            e.printStackTrace();
        }
        UtilController.closeSocket(s);
    }

    private boolean getResponse() {
        BufferedReader br = UtilController.getReader(s);
        try {
            String reply = br.readLine();
            if (reply != null && reply.equals("no"))
                return false;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }

    private void sendInfo() {
        BufferedWriter bw = UtilController.getWriter(s);
        try {
            bw.write(file.getName());
            bw.newLine();
            bw.write(Long.toString(file.length()));
            bw.newLine();
            bw.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
