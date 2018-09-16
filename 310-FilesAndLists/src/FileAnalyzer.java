import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class FileAnalyzer {
	private File file;
	private String fileAdress;
	private ArrayList<String> tokenAr;
	
	public FileAnalyzer() {}
	
	public FileAnalyzer(String fileAdress) {
		this.fileAdress = fileAdress;
		this.file = new File(fileAdress);
		this.tokenAr = fileToAr(this.file);
	}
	
	public FileAnalyzer(File file) {
		this.fileAdress = file.getPath();
		this.file = file;
		this.tokenAr = fileToAr(this.file);
	}

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.fileAdress = file.getPath();
		this.file = file;
		this.tokenAr = fileToAr(this.file);
	}

	public String getFileAdress() {
		return fileAdress;
	}

	public void setFileAdress(String fileAdress) {
		this.fileAdress = fileAdress;
		this.file = new File(fileAdress);
		this.tokenAr = fileToAr(this.file);
	}
	
	public void printArray() {
		for(String token: tokenAr)
			System.out.println(token);
	}
	
	/***
	 * Converts a file into an ArrayList of unsorted cleaned tokens.
	 * @see lineToArray() for more information 
	 * @param file
	 * @return arrayList of tokens
	 */
	private ArrayList<String> fileToAr(File file) {
		ArrayList<String> tokens = new ArrayList<String>();
		BufferedReader br;
		try {
			br = new BufferedReader(new FileReader(file));
			String line = br.readLine();
			while(line != null) {
				tokens.addAll(lineToAr(line));
				line = br.readLine();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return tokens;
	}
	
	/***
	 * Converts a line of text to an ArrayList<String> of clean tokens
	 * @see cleanTokenArray() for definition of "clean" token
	 * @param text
	 * @return ArrayList<String> of clean tokens
	 */
	
	private ArrayList<String> lineToAr(String text){
		ArrayList<String> tokenArList = new ArrayList<String>();
		String[] tokenAr = text.split("\\s+");
		tokenArList.addAll(cleanTokenArray(tokenAr));
		
		for(String token: tokenArList) {
			System.out.println(token);
		}
		
		
		return tokenArList;
	}
	
	
	/**
	 * This function cleans a String[], it strips white space and removes any tokens which are empty strings
	 * @param tokenAr, a dirty array of strings
	 * @return Cleaned Array
	 */
	private ArrayList<String> cleanTokenArray(String[] tokenAr) {
		ArrayList<String> cleanedTokenAr = new ArrayList<String>();
		for(String token : tokenAr) {
			if(token.trim().length() > 0) { //We may want to add more conditions here to clean the tokens further, but this should cover most cases
				cleanedTokenAr.add(token.trim());
			}
		}
		return cleanedTokenAr;
	}
	
}
