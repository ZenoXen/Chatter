package network;

import controllers.FileController;
import controllers.UtilController;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class FileReciver extends Thread {
    private ServerSocket ss;

    public FileReciver() {
        try {
            ss = new ServerSocket(NetworkConsts.fileBackPort);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void run() {
        while (true) {
            Socket s = null;
            try {
                s = ss.accept();
            } catch (IOException e) {
                e.printStackTrace();
            }
            BufferedReader br = UtilController.getReader(s);
            String file = null;
            long len = 0;
            try {
                file = br.readLine();
                len = Long.parseLong(br.readLine());
            } catch (IOException e) {
                e.printStackTrace();
            }
            File f = FileController.showPrompt(file, len);
            if (f == null) {
                reply(s, false);
                continue;
            }
            reply(s, true);
            new FileReceiveThread(s, f, len).start();
        }
    }

    private void reply(Socket s, boolean flag) {
        BufferedWriter bw = UtilController.getWriter(s);
        try {
            if (flag) bw.write("yes");
            else bw.write("no");
            bw.newLine();
            bw.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
