package com;

import java.io.FileNotFoundException;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

public class TextAnalysisTest {
	ExtractPageContent epc;
	TextAnalysis ta;
	
	@Before
	public void setUp()
	{
		epc = new ExtractPageContent("example_pdfs/3.pdf");
		ta = new TextAnalysis(epc.getFileContents());
		System.out.println(epc.getFileContents());
		try {
			epc.toFile("txt");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void testLanguageDetection()
	{
		Assert.assertEquals("pl", ta.detectLanguage());
		
	}
	
	@Test
	public void testGetNumerOfSigns()
	{
		Assert.assertEquals(new Integer(34), ta.getNumberOfSigns());
	}
	@Test
	public void testGetNumerOfWords()
	{
		Assert.assertEquals(new Integer(6), ta.getNumberOfWords());
	}
	
	@Test
	public void testNumberOfSentences() {
		Assert.assertEquals(new Integer(3), ta.getNumberOfSentences());
	}
}