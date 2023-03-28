
import java.io.IOException;
import java.net.* ;

public final class WebServer
{
	public static void main(String argv[]) throws Exception
	{
		// Set the port number.
		int port = 6789;
		try {
			ServerSocket serverSocket = new ServerSocket(port);
			// Process HTTP service requests in an infinite loop.
			while (true) {
				// Listen for a TCP connection request.
				Socket clientSocket = serverSocket.accept();
				new Thread(new HTTPRequestHandler(clientSocket)).start();
			}
			//serverSocket.close();
		}
		catch(IOException e){
			e.printStackTrace();
		}
	}
}
