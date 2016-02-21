
import java.io.*;
import java.net.*;

class ServeClient extends Thread { // use thread for this communication
	
	private BufferedReader in; // create commucation channel for read (receive)
	private PrintWriter out;   // create commucation channel for write (transmit)
	private Socket socket;     // create socket communication
	
	public ServeClient (Socket s) throws IOException {  // handling ioexeptions (if have) from commucation channels
		
		socket = s;
		in = new BufferedReader (new InputStreamReader (
				socket.getInputStream() ));
		
		
		out = new PrintWriter ( new BufferedWriter (new OutputStreamWriter (
				socket.getOutputStream() )), true);
		start();					
	}		
	
	public void run() { // start application here
		try {
			while (true) {  // forever loop, while except type "end"
				
				String instr = in.readLine();
				System.out.println("Client: " + instr);	
				if (instr.equals("end")) break;
				
				out.println("Server: " + instr);
			}		
			
			System.out.println("Connection closed"); // if typed "end", close communication and goto finaly to close communication
			
		} catch (IOException e) {
			System.out.println("Communication error!"); // if have ioexception print this message
			
		}
		finally {
			
			try {
				socket.close(); // close socket communication
			}
			catch (IOException e) {
							
				System.out.println("Can't close socket!"); // if have error while closing communication socket print this message
			}	
		}		
	}
}	


public class Server {
	
	static final int PORT = 10056;  // choosed port to socket communication
	
	public static void main (String [] args) throws IOException {
		
		ServerSocket s = new ServerSocket (PORT);  // create Server (server socket) on port

		System.out.println("Server is started! Waiting for client connections..."); // print message
		
		try {
			while (true) {
				
				Socket socket = s.accept(); // socket is waiting for a message from client
				
				try {
					new ServeClient(socket); // create connection with client
				} 
				catch (IOException e) {
					socket.close();  // if have error while create connection with client go close socket
					System.out.println("Error ...");  // print message
				}	
			}	
		} 
		finally {
			s.close();  // close Server (socket) connection
		}					
	}
}		 	
