package TalkWithThreads;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) throws IOException {

        //Server socket
        ServerSocket welcomeSocket = new ServerSocket(6969);
        Socket connectionSocket = welcomeSocket.accept();

        //Readers
        BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in));
        //server
        BufferedReader sIngoing = new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));
        DataOutputStream sOutgoing = new DataOutputStream(connectionSocket.getOutputStream());

        ThreadIngoing scin = new ThreadIngoing(sIngoing, true);
        ThreadOutgoing scout = new ThreadOutgoing(sOutgoing, userInput);
        scin.start();
        scout.start();

    }
}
