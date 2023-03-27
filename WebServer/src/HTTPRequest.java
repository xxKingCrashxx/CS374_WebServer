import java.net.Socket;

public final class HTTPRequest 
{
    final static String CRLF = "\r\n";
	Socket socket;

	// Constructor
	public HTTPRequest(Socket socket) throws Exception 
	{
		this.socket = socket;
	}

	// Implement the run() method of the Runnable interface.
	public void run()
	{
		. . .
	}

	private void processRequest() throws Exception
	{
		. . .
	}
    
}
