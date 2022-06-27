import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;

public class ReadLines {
    public static void main(String[] args) throws IOException {
        File currentPath = new File("E:\\study\\Programming\\JAVA\\JavaPrograms\\ChatSoftware\\src");
        File[] paks = currentPath.listFiles();
        LineNumberReader l;
        int cnt = 0;
        for (File dir : paks) {
            if (dir.isDirectory()) {
                File[] files = dir.listFiles();
                for (File f : files) {
                    int num = 0;
                    System.out.println(f);
                    l = new LineNumberReader(new FileReader(f));
                    String str;
                    while ((str = l.readLine()) != null) {
                        cnt++;
                        num++;
                    }
                    System.out.println(num);
                }
            }
        }
        System.out.println(cnt);
    }
}