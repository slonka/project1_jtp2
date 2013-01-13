package com;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import com.cybozu.labs.langdetect.Detector;
import com.cybozu.labs.langdetect.DetectorFactory;
import com.cybozu.labs.langdetect.LangDetectException;

public class TextAnalysis {
	private String contents;
	// TODO: check this patch
	private final String langdetectProfileDirectory = "profiles/";
	private Map<String, Integer> wordCounts = new HashMap<String, Integer>();
	
	// saved results
	String language;
	float gunningFogIndex = 0;
	int numberOfSigns = -1;
	int numberOfWords = -1;
	int numberOfSentences = -1;
	
	TextAnalysis(String c)
	{
		contents = c;
	}
	
	public void getWordOccurances()
	{
		String[] words = contents.toLowerCase().split("\\s+");
	
		for (String word : words) {
		    Integer count = wordCounts.get(word);
		    if (count == null) {
		        count = 0;
		    }
		    wordCounts.put(word, count + 1);
		}
	}
	
	float getGunningFogIndex()
	{
		return (float) (0.4 * (numberOfWords/numberOfSentences)); // TODO: + 100 (complex words / words) 
		
	}
	
	// TODO: Check what it actually retuns, my guess: iso language code
	public String detectLanguage()
	{
		String lang = "Could not detect";
		try {
			DetectorFactory.loadProfile(langdetectProfileDirectory);
			Detector detector = DetectorFactory.create();
			detector.append(contents);
			lang = detector.detect();
		} catch (LangDetectException e) {
			// TODO We need a logging system!
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		return lang;
	}
	
	public Integer getNumberOfSigns() {
		numberOfSigns = contents.length()-1; // najwidoczniej dodaje 1 znak na koncu
		System.out.println(numberOfSigns); 
		for (int i=0 ; i< contents.length(); i++)
		System.out.println(i+ ": "+ contents.charAt(i));
		return numberOfSigns;
	}

	public Integer getNumberOfWords() {
		if (numberOfWords == -1)
		{
			Scanner scanner = new Scanner(contents);
			int count = 0;
			while (scanner.hasNext()) {
				String s= scanner.next();
				System.out.println(s);
				count++;
			}
			scanner.close();
			return count;
		}
		else
			return numberOfWords;
	}
	
	public Integer getNumberOfSentences() {
		if (numberOfSentences == -1)
		{
			int count=0;
			char a;
			boolean dots= false;
			for (int i=0; i<contents.length(); i++) {
				a= contents.charAt(i);
				if (((a=='.') ||  (a=='…') )&&(!dots)) {
						count++;
						dots= true;
				} else 
					if ((a=='!') || (a=='?')) {
						count++;
						dots= false;
					}
					else dots= false;
			}
			return count;
		}
		else
			return numberOfSentences;
	}
}
