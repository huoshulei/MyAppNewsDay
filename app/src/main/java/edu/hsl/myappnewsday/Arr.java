package edu.hsl.myappnewsday;

/**
 * Created by Administrator on 2016/6/12.
 */
public class Arr {
    public static int[] arr(int[] ints) {
        int len = ints.length;
        for (int i = 0; i < len / 2; i++) {
            int in = ints[i];
            ints[i] = ints[len - i - 1];
            ints[len - i - 1] = in;
        }
        return ints;
    }
}
