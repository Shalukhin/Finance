package main.java.by.epam.util;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DriversFileDAO {


	public static boolean writeToEndOfFile(String link, String text) throws IOException {
		File file = new File(link);
		
		long fileLengthBeforeWrite = file.length();
		
		FileWriter fileWriter = new FileWriter(file, true);		
		
		fileWriter.append(text);
		fileWriter.append("\n");
		fileWriter.close();

		if (file.length() != fileLengthBeforeWrite) {
			return true;
		} else {
			return false;
		}
	}

	public static String extractTextFromFile(String link) throws IOException{

		File beansBase = new File(link);
		StringBuilder beansBaseStr = new StringBuilder();
		
		FileReader fileReader = new FileReader(beansBase);
		int symb;
		while ((symb = fileReader.read()) != -1) {
			beansBaseStr.append((char) symb);
		}
		fileReader.close();
		
		return beansBaseStr.toString();
	}

	public static ArrayList<String> getArrayTagsFromTextByName(String text, String nameTag) {
		ArrayList<String> tagsArr = new ArrayList<>();
		int start;
		int end;
		Pattern patternStartTag = Pattern.compile("<" + nameTag + ">");
		Matcher matcherStartTag = patternStartTag.matcher(text);
		Pattern patternEndTag = Pattern.compile("<\\/" + nameTag + ">");
		Matcher matcherEndTag = patternEndTag.matcher(text);
		while (matcherStartTag.find()) {
			start = matcherStartTag.end();
			if (matcherEndTag.find(start)) {
				end = matcherEndTag.start();
				tagsArr.add(text.substring(start, end).trim());
			}
		}

		return tagsArr;
	}
	
	public static void cleanFile(String link) throws IOException {
		File beansBase = new File(link);
		
		FileWriter fileWriter = new FileWriter(beansBase);
		fileWriter.append("");
		fileWriter.close();
	}

}
