import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class CommonClass {
    List<Integer> numbers;

    public CommonClass(int amount) {
        numbers = new ArrayList<>();
        Random r=new Random();
        for (int i=0;i<amount;i++) {
            numbers.add(Math.abs(r.nextInt()%10000));
        }
    }

    public List<Integer> getNumbers() {
        return numbers;
    }
}
