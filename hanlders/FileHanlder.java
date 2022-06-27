package hanlders;

import graphics.SelectDialog;
import graphics.UI;
import information.ComponentManager;
import information.NickManager;
import information.UserManager;
import network.FileReciver;
import network.FileSendThread;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.net.Socket;

public class FileHanlder {
    private static SelectDialog users;
    private static FileDialog outFile;
    private static FileDialog inFile;

    public static void setController() {
        UI ui = (UI) (ComponentManager.getReference("UI"));
        users = new SelectDialog(ui, "Select a user", true);
        users.setLocationRelativeTo(ui);
        outFile = new FileDialog(ui, "Select a file", FileDialog.LOAD);
        outFile.setVisible(false);
        outFile.setLocationRelativeTo(users);
        inFile = new FileDialog(ui, "Save as", FileDialog.SAVE);
        inFile.setVisible(false);
        inFile.setLocationRelativeTo(users);
        FileReciver fr = new FileReciver();
        fr.start();
    }

    public static File showPrompt(String file, long len) {
        File saved = new File(file);
        String info = getInfo(file, len);
        int result = JOptionPane.showConfirmDialog(
                ComponentManager.getReference("UI"), info, "Recive?",
                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (result == JOptionPane.YES_OPTION) {
            inFile.setFile(file);
            inFile.setVisible(true);
            String dir = inFile.getDirectory(), name = inFile.getFile();
            if (dir != null && name != null)
                saved = new File(inFile.getDirectory() + inFile.getFile());
        }
        return saved;
    }

    public static void showSelect() {
        users.refreshUsers();
        users.setVisible(true);
    }

    public static void fileOut(String nickname) {
        if (nickname.equals(NickManager.getNick())) return;
        if (UserManager.getAddr(nickname) == null) return;
        outFile.setVisible(true);
        String fileName = getSelectedFile();
        if (!fileNameValid(fileName)) return;
        File file = new File(fileName);
        Socket s = UtilHandler.createSocket(nickname);
        FileSendThread fst = new FileSendThread(s, file);
        fst.start();
    }

    private static String getSelectedFile() {
        return outFile.getDirectory() + outFile.getFile();
    }

    private static boolean fileNameValid(String file) {
        if (file == null) return false;
        File f = new File(file);
        boolean notExists = !f.exists();
        boolean isDir = f.isDirectory();
        return !notExists && !isDir;
    }

    private static String getInfo(String file, long len) {
        String sb = "是否接受文件" +
                System.lineSeparator() +
                "文件名: " +
                file +
                System.lineSeparator() +
                "大小: " +
                String.format("%.2f", (double) (len / (1024 * 1024))) +
                " MB";
        return sb;
    }
}