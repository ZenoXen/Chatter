package graphics;

import javax.swing.*;

public class Bar extends JMenuBar implements Setable {
    private JMenu m;

    public Bar() {
        init();
        set();
        addListeners();
    }

    public void init() {
        m = new JMenu("菜单");
    }

    public void set() {
        this.add(m);
    }

    @Override
    public void addListeners() {
    }
}