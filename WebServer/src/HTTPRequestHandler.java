import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
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
		DataOutputStream clientOutput = new DataOutputStream(clientSocket.getOutputStream());
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

		handleRequestMethod(request, clientOutput);

		clientOutput.close();
		bufferedReader.close();
		clientSocket.close();
	}
	private void handleRequestMethod(HTTPMessageRequest request, DataOutputStream out) throws IOException
	{
		//Get method case
		if(request.getMethod().equals("GET"))
		{
			handleGetRequest(request, out);
		}
	}
	private void writeOutResponse(HTTPMessageResponse response, DataOutputStream out, File file) throws IOException
	{
		out.write(response.generateResponseString().getBytes());

		FileInputStream fis = new FileInputStream(file);
		byte[] buffer = new byte[2048];
		int bytesRead = 0;

		while((bytesRead = fis.read(buffer)) != -1){
			out.write(buffer, 0, bytesRead);
		}
		fis.close();
	}

	private void handleGetRequest(HTTPMessageRequest request, DataOutputStream out) throws SecurityException, IOException{
		String resourcePath = ROOT_DIRECTORY + request.getResource();
		HTTPMessageResponse response;

		File file = new File(resourcePath);

		try
		{
			if(!file.exists())
			{
				System.out.println("File does not exist:" + resourcePath);
				response = new HTTPMessageResponse(404, "NOT FOUND", request.contentType());
				response.setBody(response.generateBodyString(404, "NOT FOUND", "The webpage you are looking for does not exist."));
			}

			if(!file.canRead())
			{
				System.out.println("file cannot be read:" + resourcePath);
				response = new HTTPMessageResponse(500, "INTERNAL SERVER ERROR", request.contentType());
				response.setBody(response.generateBodyString(500, "INTERNAL SERVER ERROR", "Something went wrong"));
			}	
		}
		catch(SecurityException se)
		{
			throw new SecurityException();
		}
		response = new HTTPMessageResponse(200, "OK", request.contentType());
		writeOutResponse(response, out, file);
	}
}
