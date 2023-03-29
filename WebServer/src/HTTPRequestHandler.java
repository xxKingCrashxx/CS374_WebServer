import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;

import httputil.HTTPMessageRequest;
import httputil.HTTPMessageResponse;

public final class HTTPRequestHandler implements Runnable
{
	Socket clientSocket;

	// Constructor
	public HTTPRequestHandler(Socket socket) throws Exception 
	{
		clientSocket = socket;
	}

	// Implement the run() method of the Runnable interface.
	public void run()
	{
		//TODO
		try 
		{
			processRequest();
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}

	private void processRequest() throws Exception
	{
		//TODO
		InputStream clientInput = clientSocket.getInputStream();
		OutputStream clientOutput = clientSocket.getOutputStream();
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(clientInput));

		String requestLine = bufferedReader.readLine();
		System.out.println(requestLine);

		String[] parsedLine = requestLine.split(" ");
		HTTPMessageRequest request = new HTTPMessageRequest(parsedLine[0], parsedLine[1], parsedLine[2]);

		//printing out HTTP request body headers
		String headerLine;
		while((headerLine = bufferedReader.readLine()).length() != 0){
			System.out.println(headerLine);
		}
		System.out.println();
		handleMethod(request, clientOutput);

		clientOutput.close();
		bufferedReader.close();
		clientSocket.close();
	}
	private void handleMethod(HTTPMessageRequest request, OutputStream out) throws IOException{
		if(request.getMethod().equals("GET")){
			HTTPMessageResponse serverResponse = getResource(request.getResource());
			String responseMsg = serverResponse.generateResponseString();
			out.write(responseMsg.getBytes());

		}
	}
	private HTTPMessageResponse getResource(String resourcePath){
		FileInputStream fis = null;
		HTTPMessageResponse serverResponse = null;

		File file = new File("WebServer/webpage" + resourcePath);
		String contentType = getContentType(resourcePath);
			
		try{

			if(!file.exists()){
				return serverResponse = new HTTPMessageResponse(404, "NOT FOUND", contentType);
			}

			fis = new FileInputStream(file);
			byte[] content = fis.readAllBytes();

			fis.close();
			return serverResponse = new HTTPMessageResponse(200, "OK", content, contentType);
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return serverResponse = new HTTPMessageResponse(0, resourcePath, contentType);
		}	
		
	}
	private String getContentType(String resource){

		String[] splitResource = resource.split(".");
		String contentType = "";

		switch(splitResource[1]){
			case "htm":	case "html":	
				contentType = "text/html";
				break;
			case "jpg":
				contentType = "img/jpg";
				break;
			default:
				contentType = "application/octet-stream";
				break;	
		}
		return contentType;
	}

    
}
