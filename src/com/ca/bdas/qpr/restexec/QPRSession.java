package com.ca.bdas.qpr.restexec;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletContext;

public class QPRSession {
	
	//Constants
	final String SPEECH_STRING = "STTstring";
	
	//Variables
	private Map<String, String> sessionMap = new HashMap<String, String>();
	
	/*
	 * Begin Getters and Setters
	 */
	
	/**
	 * @return the sessionMap
	 */	
	public Map<String, String> getSessionMap() {
		return sessionMap;
	}

	/**
	 * @param sessionMap the sessionMap to set
	 */	
	public void setSessionMap(Map<String, String> sessionMap) {
		this.sessionMap = sessionMap;
	}
	
	/*
	 * End Getters and Setters
	 */

	/**
	 * Constructor
	 * 
	 * @param map Accepts a map of values that are to be added to the sessionMap
	 */
	public QPRSession(Map<String, String> map)
	{
		setSessionMap(map);
	}
	
	/**
	 * This function adds more details to the existing map
	 *
	 * @return String sessionID the session ID of the QPRSession
	 */	
	public String completeMap(ServletContext servContext)
	{
		Map<String, String> posMap = new HashMap<String, String>();
		/*
		 * Take the STT string out and then pass it to the Python function
		 */
		String STTStringValue = sessionMap.remove(SPEECH_STRING);
		if(STTStringValue.equals(null))
		{
			/*
			 * If the STT string is not present
			 */
			//TODO LOG an error and continue
		}
		else
		{
			//TODO Invoke the right python function here
			posMap = Utilities.posTagger(STTStringValue, servContext);
			if(posMap.equals(null))
			{
				//TODO LOG an error and continue
			}
			else
			{
				sessionMap.putAll(posMap);	
			}
		}	
		
		System.out.println(sessionMap.toString());
		
		/*
		 * TODO Pass keyValueMap to Vidhya's API
		 */
		String sessionID = saveQPRSession();
		return sessionID;
	}
	
	/**
	 * This function serializes the current QPRSession object and saves to the database
	 * 
	 * @return String The session id under which the session was saved in the database
	 */	
	private String saveQPRSession()
	{
		String sessionID = "something";	
		
		return sessionID;
	}
	
	/**
	 * Returns an object of the class QPRSession that is populated from the values
	 * retrieved from the database based on the session id
	 * 
	 * @param sessionID ID of the session that is to be retrieved
	 * @return An object of the class session 
	 */	
	static QPRSession getQPRSession(String sessionID)
	{
		
		QPRSession retrievedSession = null;
		
		return retrievedSession;
	}
	
	

}
