package com;
/*
 * This class is part of the book "iText in Action - 2nd Edition"
 * written by Bruno Lowagie (ISBN: 9781935182610)
 * For more info, go to: http://itextpdf.com/examples/
 * This example only works with the AGPL version of iText.
 */

import java.io.IOException;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.parser.PdfReaderContentParser;
import com.itextpdf.text.pdf.parser.SimpleTextExtractionStrategy;
import com.itextpdf.text.pdf.parser.TextExtractionStrategy;
 
public class ExtractPageContent {
	private String filePath;
	private String fileContents;
	
	PdfReader reader;
	PdfReaderContentParser parser = new PdfReaderContentParser(reader);
	TextExtractionStrategy strategy;
	
	ExtractPageContent(String filePath)
	{
		this.filePath = filePath;
		try {
			reader = new PdfReader(filePath);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public String getFileContents()
	{
		return fileContents;
	}
	
	public String getPage(int i) throws IOException
	{		
        strategy = parser.processContent(i, new SimpleTextExtractionStrategy());
        String result = strategy.getResultantText();
        return result;        
	}

	public void getContents() throws IOException
	{	
		for (int i = 1; i <= reader.getNumberOfPages(); i++) {
            strategy = parser.processContent(i, new SimpleTextExtractionStrategy());
            fileContents += strategy.getResultantText();
        }
	}
    
}
 