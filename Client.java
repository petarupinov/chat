
import java.io.*;
import java.net.*;


public class Client {
	
	static final int PORT = 10056;     // connection on this port
//	static final int NUMOFPINGS = 10;  // 

	public static void main (String [] args) throws IOException {
		
		Socket socket = null; // create null socket connection
		
		InetAddress netaddress = InetAddress.getByName("localhost");  // connect on localhost machine
		System.out.println("Net address = " + netaddress);            // print message with local ip address machine
				
		try {
			socket = new Socket(netaddress, PORT); // create Client connection
			
			System.out.println("Socket = " + socket);  // print client connection
			BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));  // create stream for read (receive)
				
			PrintWriter out = new PrintWriter( new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())), true);  // create stream for write (transmit)

//			for (int i=0; i<NUMOFPINGS; i++) {				
//				out.println("Ping " + i);		
//				String str = in.readLine();
//				System.out.println(str);
//			}	

			out.println("This is Client"); // send message to server
			String str = in.readLine();    // read message from server
			System.out.println(str);       // print message from server			

			out.println("end");  // send "end" to close this client connection
		
		}
		catch (IOException e) {
			if (socket == null) {
				System.out.println("Server not responding!");  // if have ioexeption with no socket print this message
			}
			else {
				System.out.println("Communication error!");  // if have any other ioexeption print this message
			}
		}
		finally {
			if (socket != null) {
				socket.close();     // if haven't socket, close communication
			}
		}
	}
}	
