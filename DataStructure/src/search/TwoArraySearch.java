package search;

import java.util.Arrays;

public class TwoArraySearch {
    public static void main(String[] args) {
        int[] arr = {1, 5, 32, 41, 80};
        int[] arr2 = {21, 26, 45, 54, 55, 57, 60, 90};

        int k = 3;

        int test = test(arr, arr2, k);
        System.out.println(test);
    }

    public static int test(int[] arr1, int[] arr2, int k) {
        int m = arr1.length;
        int n = arr2.length;
        int index = 0;
        int[] tmp = new int[m + n];
        int a = 0;
        int b = 0;

        while (a < m && b < n) {
            if (arr1[a] >= arr2[b]) {
                tmp[index++] = arr2[b++];
            } else {
                tmp[index++] = arr1[a++];
            }

        }


        while (a < m) {
            // arr1 中还有数据
            tmp[index++] = arr1[a++];
        }

        while (b < n) {
            // arr2 中还有数据
            tmp[index++] = arr2[b++];
        }

        System.out.println(Arrays.toString(tmp));

        return tmp[k - 1];

    }
}
