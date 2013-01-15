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
	private float gunningFogIndex = 0;
	private int numberOfSigns = 0;
	private int numberOfWords = 0;
	private int numberOfSentences = 0;
	private int maxSentenceLength = 0;
	private int minSentenceLength;
	private int numberOfSignsWtSpaces = 0;

	TextAnalysis(String c) {
		contents = c;
		numberOfSigns = contents.length()- 1; // najwidoczniej dodaje 1 znak na
												// koncu
		/*for (int i = 0; i < contents.length(); i++) {
			System.out.println(i + ": " + contents.charAt(i));
		}*/
		numberOfWords = countWords();
		minSentenceLength = numberOfSigns;
		countSentences();
		getWordOccurances();
		gunningFogIndex = (float) (0.4 * (numberOfWords / numberOfSentences));
		// TODO: + 100 (complex words / words)
		//l = detectLanguage();
	}

	public float getGunningFogIndex() {
		return gunningFogIndex;

	}

	public Integer getNumberOfSigns() {
		return numberOfSigns;
	}

	public Integer getNumberOfWords() {
		return numberOfWords;
	}

	public Integer getNumberOfSentences() {
		return numberOfSentences;
	}

	public Integer getMinSentenceLength() {
		return minSentenceLength;
	}

	public Integer getMaxSentenceLength() {
		return maxSentenceLength;
	}

	public Integer getNumberOfSignsWtSpaces() {
		return numberOfSignsWtSpaces;
	}

	public Map<String, Integer> getWordCounts() {
		return wordCounts;
	}

	private int countWords() {
		Scanner scanner = new Scanner(contents);
		int count = 0;
		while (scanner.hasNext()) {
			String s = scanner.next();
			count++;
		}
		scanner.close();
		return count;
	}

	private void countSentences() {
		int length = 0;
		int spaces = 0;
		char a;
		boolean dots = false;
		for (int i = 0; i < contents.length(); i++) {
			a = contents.charAt(i);
			if (a == ' ')
				spaces++;
			if (((a == '.') || (a == '…') || (a == '!') || (a == '?'))
					&& (!dots)) {
				numberOfSentences++;
				length++;
				dots = true;
				if (length < minSentenceLength)
					minSentenceLength = length;
				if (length > maxSentenceLength)
					maxSentenceLength = length;
				length = 0;
			} else {
				if (!dots)
					length++;
				dots = false;
			}
		}
		numberOfSignsWtSpaces = contents.length() - spaces;
	}

	private void getWordOccurances() {
		String[] words = contents.toLowerCase().split("\\s+");

		for (String word : words) {
			Integer count = wordCounts.get(word);
			if (count == null) {
				count = 0;
			}
			wordCounts.put(word, count + 1);
		}
	}

	// TODO: Check what it actually retuns, my guess: iso language code
	public String detectLanguage() {
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
}
