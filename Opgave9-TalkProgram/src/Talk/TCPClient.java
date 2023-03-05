package Talk;

import java.io.*;
import java.net.*;

public class TCPClient {

	public static void main(String argv[]) throws Exception{
		String sentence;
		String modifiedSentence;
		String connectionString = "Start " + "Allan";
		BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));
		Socket clientSocket= new Socket("10.10.140.89",6969);
		DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());
		BufferedReader inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
		//Init
		outToServer.writeBytes(connectionString + "\n");
		if(!inFromServer.readLine().equals("Nej")){
			System.out.println("Connection open");
			while(true){
				sentence = inFromUser.readLine();
				outToServer.writeBytes(sentence + '\n');
				if(sentence.contains(".disconnect")){
					break;
				}
				modifiedSentence = inFromServer.readLine();
				System.out.println("FROM SERVER: " + modifiedSentence);

			}
		} else {
			System.out.println("Server declined");
		}
		clientSocket.close();
	}
}


