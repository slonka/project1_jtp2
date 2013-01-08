package com;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

public class TextAnalysisTest {
	ExtractPageContent epc;
	TextAnalysis ta;
	
	@Before
	public void setUp()
	{
		epc = new ExtractPageContent("example_pdfs/1.pdf");
		ta = new TextAnalysis(epc.getFileContents());
	}
	
	@Test
	public void testLanguageDetection()
	{
		Assert.assertEquals("pl", ta.detectLanguage());
	}
	
	@Test
	public void testGetNumerOfSigns()
	{
		Assert.assertEquals(12124, ta.getNumerOfSigns());
	}
	@Test
	public void testGetNumerOfWords()
	{
		Assert.assertEquals(1658, ta.getNumberOfWords());
	}
}
