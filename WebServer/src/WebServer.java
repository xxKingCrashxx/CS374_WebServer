import java.io.* ;
import java.net.* ;
import java.util.* ;

public final class WebServer
{
	public static void main(String argv[]) throws Exception
	{
		// Set the port number.
		int port = 6789;

		// Establish the listen socket.
      	ServerSocket serverSocket = new ServerSocket(port);

		// Process HTTP service requests in an infinite loop.
		while (true) {
			// Listen for a TCP connection request.
			Socket clientSocket = serverSocket.accept();
			
			HTTPRequestHandler clientWorkerThread = new HTTPRequestHandler(clientSocket);
			clientWorkerThread.run();
		}
	}
}
