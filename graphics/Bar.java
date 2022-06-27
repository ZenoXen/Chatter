package graphics;

import javax.swing.*;

public class Bar extends JMenuBar implements Setable {
    private JMenu m;

    public Bar() {
        init();
        set();
        addListeners();
    }

    public static void main(String[] args) {
    }

    public void init() {
        m = new JMenu("²Ëµ¥");
    }

    public void set() {
        this.add(m);
    }

    @Override
    public void addListeners() {
    }
}