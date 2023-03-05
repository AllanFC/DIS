package TalkWithThreads;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
public class TCPServer {
	
	/**
	 * @param args
	 */
	public static void main(String[] args)throws Exception {
		String clientSentence;
		String capitalizedSentence;
		String serverNavn = "Servernavn";
		ServerSocket welcomeSocket = new ServerSocket(6969);
		Socket connectionSocket = welcomeSocket.accept();
		BufferedReader inFromClient = new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));
		DataOutputStream outToClient = new DataOutputStream(connectionSocket.getOutputStream());
		if(inFromClient.readLine().split(" ")[0].equals("Start")){
			outToClient.writeBytes(serverNavn + "\n");
			while (true) {
				clientSentence = inFromClient.readLine();
				if(clientSentence.contains(".disconnect")){
					break;
				}
				capitalizedSentence = clientSentence.toUpperCase() + '\n';
				outToClient.writeBytes(capitalizedSentence);
			}
		} else {
			outToClient.writeBytes("Nej");

		}

	}

}
