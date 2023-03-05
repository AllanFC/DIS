import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class InputThread extends Thread{
    CommonClass common;
    public InputThread(CommonClass common) {
        this.common = common;
    }

    public void run(){
        InputStreamReader r = new InputStreamReader(System.in);
        BufferedReader in = new BufferedReader(r);
        while(true){
            try {
                common.setStr(in.readLine());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
