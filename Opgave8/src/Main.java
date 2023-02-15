import java.util.Random;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        int amount = 1000000;
        CommonClass c = new CommonClass(amount);
        FletteSortering merge = new FletteSortering();
        MergeThread merge1 = new MergeThread(c, merge, 0, amount/2);
        MergeThread merge2 = new MergeThread(c, merge, amount/2+1, amount-1);
        long l1,l2;
        l1 = System.nanoTime();
        merge1.start();
        merge2.start();
        merge1.join();
        merge2.join();
        merge.mergesort(c.getNumbers(), 0, amount-1);
        l2 = System.nanoTime();
        //System.out.println(c.getNumbers());

        System.out.println("Køretiden var " + (l2-l1)/1000000);


    }
}
