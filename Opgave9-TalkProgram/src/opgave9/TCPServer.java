package opgave9;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
public class TCPServer {
	
	/**
	 * @param args
	 */
	public static void main(String[] args)throws Exception {
		String clientSentence;
		String capitalizedSentence;
		InetAddress ipaddress;
		ServerSocket welcomeSocket = new ServerSocket(6969);
		while (true) {
			Socket connectionSocket = welcomeSocket.accept();
			BufferedReader inFromClient = new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));
			DataOutputStream outToClient = new DataOutputStream(connectionSocket.getOutputStream());
//			ipaddress = connectionSocket.getInetAddress();
//
//			outToClient.writeBytes(ipaddress.toString() + "\n");
			clientSentence = inFromClient.readLine();
			capitalizedSentence = clientSentence.toUpperCase() + '\n';
			connectionSocket.close();
			outToClient.writeBytes(capitalizedSentence);
	
		}
	}

}
