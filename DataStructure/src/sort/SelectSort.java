package sort;

import java.util.Arrays;

public class SelectSort {
    public static void main(String[] args) {
        int[] arr = {3, 9, -1, 10, 20};

        selectSort(arr);

        int[] arr2 = new int[80000];
        for (int i = 0; i < arr2.length; i++) {
            arr2[i] = (int) (Math.random() * 8000000);
        }

        long start = System.currentTimeMillis();
        selectSort(arr2);
        long end = System.currentTimeMillis();

        System.out.println("8万数据排序所用时间 "+(end-start)/1000+"s");

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
                // 找到最小值, 交换
                arr[minIndex] = arr[i];
                arr[i] = min;
            }

//            System.out.println(Arrays.toString(arr));
        }


    }
}
