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
	final String ROOT_DIRECTORY = "WebServer/website";

	// Constructor
	public HTTPRequestHandler(Socket socket) throws Exception 
	{
		clientSocket = socket;
	}

	// Implement the run() method of the Runnable interface.
	public void run()
	{
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
		//getting access to the I/O stream of the client socket
		InputStream clientInput = clientSocket.getInputStream();
		OutputStream clientOutput = clientSocket.getOutputStream();
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(clientInput));

		//getting the request line from the http request message
		String requestLine = bufferedReader.readLine();
		System.out.println(requestLine);

		//splitting each individual part of the request line by ' '
		//creating HTTPMessageRequest object based on data.
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
		//Get method case
		if(request.getMethod().equals("GET")){
			HTTPMessageResponse serverResponse = getResource(request.getResource());
			String responseMsg = serverResponse.generateResponseString();
			out.write(responseMsg.getBytes());
		}
	}
	private HTTPMessageResponse getResource(String resourcePath){
		FileInputStream fis = null;
		HTTPMessageResponse serverResponse = null;

		if(!resourcePath.contains(ROOT_DIRECTORY))
			resourcePath = ROOT_DIRECTORY + resourcePath;
		
		File file = new File(resourcePath);
		String contentType = getContentType(resourcePath);
			
		try{

			if(!file.exists()){
				System.out.println("File does not exist:" + file.getPath());
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
			System.out.println("server error:" + file.getPath());
			return serverResponse = new HTTPMessageResponse(404, "NOT FOUND", contentType);
		}	
		
	}
	private String getContentType(String resource){
		String contentType = "";

		if(resource.endsWith(".html") || resource.endsWith(".htm"))
			contentType = "text/html";
		else if(resource.endsWith(".jpeg"))
			contentType = "image/jpeg";
		else if(resource.endsWith(".gif"))
			contentType = "image/gif";
		else
			contentType = "application/octet-stream";
		return contentType;
	}

    
}
