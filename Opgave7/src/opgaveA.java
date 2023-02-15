import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

public class opgaveA {
    public static void main(String[] args) throws IOException {
        URL url = new URL("https://dis.students.dk/example1.php");
        InputStreamReader r = new InputStreamReader(url.openStream());
        BufferedReader in = new BufferedReader(r);
        String str;
        while((str = in.readLine()) != null) {
            if(str.contains("seen")){
                break;
            }
        }
        in.close();
        String number = str.split(" ")[5];
        System.out.println(number);
    }
}
