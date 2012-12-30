package com;

import java.io.IOException;
import java.util.Scanner;

import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.parser.PdfReaderContentParser;
import com.itextpdf.text.pdf.parser.SimpleTextExtractionStrategy;
import com.itextpdf.text.pdf.parser.TextExtractionStrategy;

public class ExtractPageContent {
	private String filePath;
	private String fileContents;

	PdfReader reader;
	PdfReaderContentParser parser;
	TextExtractionStrategy strategy;

	ExtractPageContent(String filePath) {
		this.filePath = filePath;
		try {
			reader = new PdfReader(filePath);
			parser = new PdfReaderContentParser(reader);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public String getFileContents() throws IOException {
		return fileContents;
	}

	public String getPage(int i) throws IOException {
		strategy = parser.processContent(i, new SimpleTextExtractionStrategy());
		String result = strategy.getResultantText();
		return result;
	}

	public void getContents() throws IOException {
		for (int i = 1; i <= reader.getNumberOfPages(); i++) {
			strategy = parser.processContent(i,
					new SimpleTextExtractionStrategy());
			fileContents += strategy.getResultantText();
		}
	}

	public int getNumerOfSigns() {
		return fileContents.length();
	}

	public int getNumberOfWords() {
		Scanner scanner = new Scanner(fileContents);
		int count = 0;
		while (scanner.hasNext()) {
			scanner.next();
			count++;
		}
		scanner.close();
		return count;

	}
	
	public int getNumberOfSentences() {
		int count=0;
		char a;
		for (int i=0; i<fileContents.length(); i++) {
			a= fileContents.charAt(i);
			if ((a=='.') || (a=='!') || (a=='?')) {
				count++;
			}
		}
		return count;
	}
}
