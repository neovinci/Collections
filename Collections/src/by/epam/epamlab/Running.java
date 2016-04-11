package by.epam.epamlab;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Running {
	private static Map<String, Integer> words;
	public static void main(String[] args) {
		Scanner scanner = null;
		//pattern to identify the correct word
		String regex  = "([a-z]+\\-?\\'?[a-z]*)+";
		//pattern to extract words from text
		String regexToken = "!#$%&()*+,./:;<=>?@[\\]^_`{|}~\" ";
		try {
			scanner = new Scanner(new FileReader("src/1.txt"));
			words = new HashMap<String, Integer>();
			Pattern p = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
			
			while(scanner.hasNext()) {
				StringTokenizer token = new StringTokenizer(scanner.nextLine(), regexToken);
				
				while(token.hasMoreTokens()) {
					String word = token.nextToken().trim();
					Matcher m = p.matcher(word);
					
					if(m.find()){
						word = m.group().toLowerCase();
						if(words.containsKey(word)) {
							words.put(word, words.get(word) + 1);							
						} else {
							words.put(word, 1);
						}						
					}
				}								
			}
			
			for(Map.Entry<String, Integer> word : words.entrySet()) {
				System.out.println(word.getKey() + " -> " + word.getValue());
			}
			
			findWord("hello");
			findWord("Hello");
			findWord("Home");
			
			
		} catch (FileNotFoundException e) {
			System.err.println("File not found!");
		} finally {
			
			if(scanner != null) {
				scanner.close();
			}
		}		
	}
	//method to find word in map
	private static int findWord(String word) {
		int count = 0;
		String currentWord = word.toLowerCase();
		
		if(words.containsKey(currentWord)) {
			count = words.get(currentWord);
		}
		
		System.out.printf("\nThe word [%s] contains in current text %d time\n", word, count);
		return count;
	}
}
