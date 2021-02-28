import java.util.*;
import java.io.*;
public class ReadLines {
	public static void main(String[] args) throws IOException {
		File currentPath=new File("E:\\study\\Programming\\JAVA\\JavaPrograms\\ChatSoftware\\src");
		File[] paks=currentPath.listFiles();
		LineNumberReader l;
		int cnt=0;
		for(File dir:paks) {
			if(dir.isDirectory()) {
				File[] files=dir.listFiles();
				for(File f:files) {
					int num=0;
					System.out.println(f);
					l=new LineNumberReader(new FileReader(f));
					String str;
					while((str=l.readLine())!=null) {
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