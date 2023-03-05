package TalkWithThreads;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class ThreadIngoing extends Thread{
    private BufferedReader userInput;
    private Socket connectionSocket;
    private DataOutputStream outgoing;
    private BufferedReader ingoing;
    public ThreadIngoing(Socket connectionSocket, BufferedReader userInput, BufferedReader ingoing, DataOutputStream outgoing) throws IOException {
        this.connectionSocket = connectionSocket;
        this.userInput = userInput;
        this.ingoing = ingoing;
        this.outgoing = outgoing;
    }

    public void run() {

        while(true){
            try {
                String str = userInput.readLine();
                if(str.equals(".disconnect")){
                    break;
                } else {

                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        }
    }
}
