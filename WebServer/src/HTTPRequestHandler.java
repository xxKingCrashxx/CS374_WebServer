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
		handleMethod(request, bufferedReader, clientOutput);

		clientOutput.close();
		bufferedReader.close();
		clientSocket.close();
	}
	private void handleMethod(HTTPMessageRequest request, BufferedReader reader, OutputStream outputStream) throws IOException{
		if(request.getMethod().equals("GET")){
			FileInputStream fis = null;
			HTTPMessageResponse serverResponse;
			boolean fileExists = true;
			byte[] content;

			try 
			{
				fis = new FileInputStream(new File("WebServer/webpage"+ request.getResource()));
			} 
			catch (Exception e) 
			{
				fileExists = false;
			}

			if(!fileExists)
			{
				serverResponse = new HTTPMessageResponse(404, "NOT FOUND", "text/html");
			}
			serverResponse = new HTTPMessageResponse(200, "OK", fis.readAllBytes(), getContentType(request.getResource()));
		}
	}
	private HTTPMessageResponse getResource(String resourcePath){
		return null;
	}
	private String getContentType(String resource){
		return null;
	}

    
}
