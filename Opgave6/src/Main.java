public class Main {
    public static void main(String[] args) {
        CommonClass c = new CommonClass("Karl");
        InputThread in = new InputThread(c);
        PrintThread out = new PrintThread(c);
        in.start();
        out.start();
    }
}
