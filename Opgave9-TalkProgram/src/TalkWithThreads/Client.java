package TalkWithThreads;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class Client {
    public static void main(String[] args) throws IOException {
        //Client socket
        Socket clientSocket= new Socket("localhost",6969);
        //Readers
        BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in));
        //client
        DataOutputStream cOutgoing = new DataOutputStream(clientSocket.getOutputStream());
        BufferedReader cIngoing = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

        ThreadIngoing tcin = new ThreadIngoing(cIngoing, false);
        ThreadOutgoing tcout = new ThreadOutgoing(cOutgoing, userInput);
        tcin.start();
        tcout.start();
    }
}
