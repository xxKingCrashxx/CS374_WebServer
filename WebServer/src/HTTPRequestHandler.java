import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;

public final class HTTPRequestHandler implements Runnable
{
    //final static String CRLF = "\r\n";
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

		String line = bufferedReader.readLine();
		System.out.println(line);

		String[] parsedLine = line.split(" ");
		HTTPMessageRequest request = new HTTPMessageRequest(parsedLine[0], parsedLine[1], parsedLine[2]);

		//printing out HTTP request body headers
		String headerLine;
		while((headerLine = bufferedReader.readLine()).length() != 0){
			System.out.println(headerLine);
		}



		


		
	}
    
}
