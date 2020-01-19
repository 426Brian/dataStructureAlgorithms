package search;

import java.util.Arrays;

public class FibonacciSearch {
    public static int maxSize = 20;

    public static void main(String[] args) {
        int[] arr = {1, 8, 10, 89, 1000, 1234};

        int index = fibonacciSearch(arr, 1234);

        System.out.println("index == " + index);

    }

    // mid = low + F(k-1)-1

    // 斐波那契数列
    public static int[] fib() {
        int[] f = new int[maxSize];
        f[0] = 1;
        f[1] = 1;
        for (int i = 2; i < maxSize; i++) {
            f[i] = f[i - 1] + f[i - 2];
        }
        return f;
    }

    /**
     * 斐波那契查找法
     *
     * @param arr 数组
     * @param key 要查找的值
     * @return
     */
    public static int fibonacciSearch(int[] arr, int key) {
        int low = 0;
        int high = arr.length - 1;

        // 斐波那契分割数值的下标
        int k = 0;
        int mid = 0;

        // 获取到菲波那切数列
        int[] f = fib();


        while (high > f[k] - 1) {
            k++;
        }

        // f[k] 可能大于arr 的长度
        int[] tmp = Arrays.copyOf(arr, f[k]);

        // 填充tmp arr={1,3,5,6,9}  tmp = {1,3,5,6,9, 9, 9}
        for (int i = high + 1; i < tmp.length; i++) {
            tmp[i] = arr[high];
        }
        // while 循环找到 key
        while (low <= high) {
            mid = low + f[k - 1] - 1;
            if (key < tmp[mid]) {
                // 向左查找
                high = mid - 1;
                k--;
            } else if (key > tmp[mid]) {
                // 向右查找
                low = mid + 1;
                k -= 2;
            } else {
                if (mid <= high) {
                    return mid;
                } else {
                    return high;
                }
            }
        }

        return 0;
    }

}
