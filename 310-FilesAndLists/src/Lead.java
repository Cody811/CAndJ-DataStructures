import java.io.File;
import java.util.ArrayList;

public class Lead {
	public static void main(String[] args) {
		
		for(File f : getFiles(new File(System.getProperty("user.dir") + "\\SampleText"))) {
			FileAnalyzer analyzer = new FileAnalyzer(f); //We may want to extract this whole block to a function
			String out = analyzer.getFileAdress() + " Stats\n";
			
			out += "Length of longest line in file: " + analyzer.lengthOfLongestLineInFile() + "\n";
			out += "Average line length: " + analyzer.averageLineLength() + "\n";
			out += "Number of unique tokens case sensative: " + analyzer.numberOfUniqueTokens(true) + "\n";
			out += "Number of unique tokens case insensative: " + analyzer.numberOfUniqueTokens(false) + "\n";
			
			out += "Most frequently occuring token/s: ";
			for(String token : analyzer.mostFrequentlyOccuringTokens())
				out += token + ", ";
			out += "\n";
			
			out += "Count of most frequently occurung tokens: ";
			for(String count : analyzer.countOfMostFrequentlyOccuringTokens())
				out += count + ", ";
			out += "\n";
			
			out += "10 most frequently occuring tokens and their count: ";
			for(String[] tokenPair : analyzer.tenMostFrequentlyOccuringTokens())
				out += tokenPair[0] + ", " + tokenPair[1] + "\n";
			
			out += "10 least frequently occuring tokens and their count: ";
			for(String[] tokenPair : analyzer.tenLeastFrequentlyOccuringTokens())
				out += tokenPair[0] + ", " + tokenPair[1] + "\n";
			
			//We'll need to put this a .stats file eventually. just console logging for now
			//I think .stats files are comma delimited? Need to look up and clarify how those are formatted and probably change the way the "out" string is built
			System.out.println(out);
		}
	}
	
	public static ArrayList<File> getFiles(File dir) {
		ArrayList<File> files = new ArrayList<File>();
		if(dir.isDirectory()) {
			for(File file : dir.listFiles())
				files.addAll(getFiles(file));
		} else {
			int dotIndex = dir.getName().lastIndexOf('.');
			String type = dir.getName().substring(dotIndex + 1);
			if(type.equals("txt") || type.equals(".java"))
				files.add(dir);
		}
		return files;
	}
}