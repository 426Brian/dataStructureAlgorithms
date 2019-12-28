package algorithm;

import java.util.Arrays;

public class KMPAlgorithm {
    public static void main(String[] args) {
        String str1 = "BBC ABCDAB ABCDABCDABDE";
        String str2 = "ABCDABD";

        int[] next = kmpnext("AA");
        System.out.println(Arrays.toString(next));
    }

    // 获取到字符串的部分匹配值
    public static int[] kmpnext(String dest) {
        int[] next = new int[dest.length()];
        next[0] = 0;  //  字符串长度为 1

        for (int i = 1, j = 0; i < dest.length(); i++) {
            if (dest.charAt(i) == dest.charAt(j)) {
                j++;
            }
            next[i] = j;
        }
        return next;
    }
}
