package sort;

import java.util.Arrays;

public class SelectSort {
    public static void main(String[] args) {
        int[] arr = {3, 9, -1, 10, 20};

        selectSort(arr);
    }

    /**
     *
     */
    public static void selectSort(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            int minIndex = i;
            int min = arr[i];

            for (int j = i + 1; j < arr.length; j++) {
                if (min > arr[j]) {
                    min = arr[j]; // 重置min
                    minIndex = j; // 重置minIndex


                }
            }

            if (minIndex != i) {
                // 将最小值放在arr[0]
                arr[minIndex] = arr[i];
                arr[i] = min;
            }

            System.out.println(Arrays.toString(arr));
        }


    }
}
