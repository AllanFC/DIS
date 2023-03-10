package TalkWithThreads;

import java.io.BufferedReader;
import java.io.IOException;

public class ThreadIngoing extends Thread{
    private BufferedReader ingoing;
    boolean isServer;
    public ThreadIngoing(BufferedReader ingoing, boolean isServer) throws IOException {
        this.ingoing = ingoing;
        this.isServer = isServer;
    }

    public void run() {
        while(true){
            try {
                if (isServer) {
                    System.out.println("Client: " + ingoing.readLine());
                } else {
                    System.out.println("Server: " + ingoing.readLine());
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
