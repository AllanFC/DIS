package TalkWithThreads;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;

public class ThreadOutgoing extends Thread{
    BufferedReader userInput;
    DataOutputStream outgoing;

    public ThreadOutgoing(DataOutputStream outgoing, BufferedReader userInput) {
        this.outgoing = outgoing;
        this.userInput = userInput;
    }

    public void run(){
        String input;
        while(true){
            try {
                input = userInput.readLine();
                outgoing.writeBytes(input + "\n");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
