import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Arrays;

public class opgaveB {
    public static void main(String[] args) throws IOException {
        URL url = new URL("https://m.valutakurser.dk");
        InputStreamReader r = new InputStreamReader(url.openStream());
        BufferedReader in = new BufferedReader(r);
        String str = "";
        while((str = in.readLine()) != null) {
            if(str.contains("amerikanske-dollar")){
                str = in.readLine();
                break;
            }
        }
        in.close();
        String[] listOfStr = str.split(">");
        System.out.println(listOfStr[6].substring(0,listOfStr[6].indexOf("<")));
    }
}
