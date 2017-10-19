package com.ca.bdas.qpr.listeners;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ca.bdas.qpr.restexec.QPRSession;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Servlet implementation class RestHandler
 */
public class QPRNewSession extends HttpServlet {
	
	static final ObjectMapper mapper = new ObjectMapper();
	static final String SESSION_ATTRIBUTE_NAME = "QPRSessionObject";
	
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public QPRNewSession() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	/**
	 * Accepts a Post request, retrieves data from the request's body
	 * 
	 * @return response Sends the sessionID of the object in the database
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		/*
		 * Reads the Json body of the message
		 */
		final JsonNode root = mapper.readTree(request.getInputStream());
		Map<String, String> keyValueMap = new HashMap<String, String>();
		Iterator<String> itr = root.fieldNames();		
		
		/*
		 * Populate a HashMap from the key value pairs in the Json
		 */
		while(itr.hasNext())
		{
			String field = itr.next();
			keyValueMap.put(field, root.get(field).textValue());
		}
		
		QPRSession newQPR = new QPRSession(keyValueMap);	
		String sessionID = newQPR.completeMap(request.getSession().getServletContext());
		response.setHeader("Session ID", sessionID);
		System.out.println(sessionID);
		
	}	
	

}
