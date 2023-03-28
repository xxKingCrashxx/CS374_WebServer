
import java.io.IOException;
import java.net.* ;

public final class WebServer
{
	public static void main(String argv[]) throws Exception
	{
		// Set the port number.
		int port = 6789;
		ServerSocket serverSocket = null;

		try {
			serverSocket = new ServerSocket(port);
			System.out.println("http webserver hosted on port: " + port);
			// Process HTTP service requests in an infinite loop.
			while (true) {
				// Listen for a TCP connection request.
				System.out.println("waiting for client connection...");
				Socket clientSocket = serverSocket.accept();
				
				System.out.println("Client:" + clientSocket.getInetAddress().toString() + " connecting on:" + clientSocket.getPort());
				new Thread(new HTTPRequestHandler(clientSocket)).start();
			}
		}
		catch(IOException e){
			e.printStackTrace();
		}
		finally{
			if(serverSocket != null)
				serverSocket.close();
		}
	}
}
