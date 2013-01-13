package com;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;

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
			getContents();
		} catch (IOException e) {
			e.printStackTrace();
		}		
	}

	public String getFileContents() {
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
	
	public void toFile (String fileExtension) throws FileNotFoundException {
		String newFile= new String(this.filePath.substring(0,(this.filePath.length()-3))+fileExtension);
		PrintWriter myFile= new PrintWriter(newFile);
		myFile.write(this.fileContents);
		System.out.println(newFile);
		myFile.close();
	}
}
