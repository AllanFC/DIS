public class MergeThread extends Thread{
    CommonClass common;
    int start;
    int end;
    FletteSortering merge;

    public MergeThread(CommonClass common, FletteSortering merge, int start, int end) {
        this.common = common;
        this.merge = merge;
        this.start = start;
        this.end = end;
    }

    public void run(){
        merge.mergesort(common.getNumbers(), start, end);

    }
}
