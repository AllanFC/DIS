public class PrintThread extends Thread{
    CommonClass common;
    public PrintThread(CommonClass c) {
        common = c;
    }


    public void run(){
        while(true){
            System.out.println(common.getStr());
            try {
                sleep(3000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}