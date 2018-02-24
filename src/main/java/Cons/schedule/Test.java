package Cons.schedule;

/**
 * Created by Administrator on 2017/10/27.
 */

public class Test{
    public static void main(String[] args) {
        incr(0);
    }

    private static int incr(int i){
        if(i<10) {
            System.out.println(i);
            i++;
            incr(i);
        }
        else
            return 0;
    return i;
    }
}