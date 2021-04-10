import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class FileWorker {
	File file;
	public FileWorker(){
		file = new File("bestScore.txt");
		if(!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public void write(int bestScore) {
		try {
			FileWriter writer = new FileWriter(file);
			writer.write(String.valueOf(bestScore));
			writer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
	
	public String read() {
		String bestScore = "";
		try {
			FileReader reader = new FileReader(file);
			Scanner sc = new Scanner(reader);
			if(sc.hasNext()) {
				bestScore = sc.nextLine();
			}else {
				bestScore = "0";
			}
			reader.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		return bestScore;
	}
}
