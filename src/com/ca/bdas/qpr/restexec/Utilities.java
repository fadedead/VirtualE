package com.ca.bdas.qpr.restexec;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletContext;

import edu.stanford.nlp.tagger.maxent.MaxentTagger;


public class Utilities {
	
	/**
	 * Function takes a string and returns a map of each word to it's 
	 * corresponding Part of Speech
	 * 
	 * @param Query The string that is to be POS tagged
	 * @return posMap A Map with the words as the key and the corresponding 
	 * 					POS tag as the value
	 */
	public static Map<String, String> posTagger(String Query, ServletContext servContext)
	{
		//TODO Validate the input string
		Map<String, String> posMap = new HashMap<String, String>();
		String keyValue[];
		try
		{
			String root = servContext.getRealPath("/");
			MaxentTagger tagger = new MaxentTagger(root + "/static/left3words-wsj-0-18.tagger");
			String tagged = tagger.tagString(Query);
			String seperatedString[] = tagged.split(" ");
			//TODO Handle corner cases
			for(String str : seperatedString)
			{
				 keyValue = str.split("/");
				 posMap.put(keyValue[0], keyValue[1]);
			}
		}
		catch(IOException ioe)
		{
			System.out.println("Cannot find the tagger");
			//TODO Log an error and continue
		}
		catch(ClassNotFoundException cnfe)
		{
			//TODO Log an error and continue
		}
		return posMap;
	}	

}
