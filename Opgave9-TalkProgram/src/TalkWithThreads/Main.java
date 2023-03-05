package TalkWithThreads;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in));
        Socket clientSocket= new Socket("10.10.139.45",6969);
        DataOutputStream outgoing = new DataOutputStream(clientSocket.getOutputStream());
        BufferedReader ingoing = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));


    }
}
