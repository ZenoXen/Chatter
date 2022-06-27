package bin;

import graphics.UI;

public class Chatter {
    private UI ui;

    public Chatter() {
        showUI();
    }

    public static void main(String[] args) {
        Chatter c = new Chatter();
    }

    private void initUI() {
        ui = new UI();
        ui.setSize(800, 650);
        ui.setResizable(false);
        ui.setLocationRelativeTo(null);
    }

    private void showUI() {
        initUI();
        ui.setVisible(true);
    }
}