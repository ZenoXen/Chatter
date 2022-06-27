package information;

import java.awt.*;
import java.util.HashMap;

public class ComponentManager {
    private static final HashMap<String, Component> components;

    static {
        components = new HashMap<>();
    }

    public static void saveReference(String name, Component c) {
        components.put(name, c);
    }

    public static Component getReference(String name) {
        return components.get(name);
    }
}
