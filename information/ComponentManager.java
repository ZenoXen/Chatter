package information;
import javax.swing.*;
import graphics.*;
import java.awt.*;
import java.util.*;
public class ComponentManager {
	private static HashMap<String,Component> components;
	static {
		components=new HashMap<String,Component>();
	}
	public static void saveReference(String name,Component c) {
		components.put(name, c);
	}
	public static Component getReference(String name) {
		return components.get(name);
	}
}
