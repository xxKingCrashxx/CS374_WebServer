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
	}

	private void processRequest() throws Exception
	{
		//TODO
	}
    
}
