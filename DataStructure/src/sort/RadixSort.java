package sort;

import java.util.Arrays;

public class RadixSort {
    public static void main(String[] args) {
        int[] arr = {53, 3, 542, 748, 14, 214};
//        radixSortOrigin(arr);
        radixSort(arr);

        int[] arr2 = new int[80000];
        for (int i = 0; i < arr2.length; i++) {
            arr2[i] = (int) (Math.random() * 8000000);
        }

        long start = System.currentTimeMillis();
        radixSort(arr2);
        long end = System.currentTimeMillis();

        System.out.println("8万数据排序所用时间 " + (end - start));
    }

    public static void radixSort(int[] arr) {
        // 数组中最大的数的位数
        int max = arr[0];
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] > max) {
                max = arr[i];
            }
        }

        int maxLength = (max + "").length();

        //(针对每个元素的个位数排序处理)
        int[][] bucket = new int[10][arr.length];

        // 记录每个桶中数据， 利用一个数组
        int[] bucketElementCounts = new int[10];


        for (int j = 0, n = 1; j < maxLength; j++, n *= 10) {
            for (int i = 0; i < arr.length; i++) {
                // 取出每个元素的个位数
                int digitOfElement = arr[i] / n % 10;

                bucket[digitOfElement][bucketElementCounts[digitOfElement]] = arr[i];
                bucketElementCounts[digitOfElement]++;
            }
            int index = 0;
            for (int k = 0; k < 10; k++) {
                // 如果桶中有数据， 才放到原数组
                if (bucketElementCounts[k] > 0) {
                    for (int l = 0; l < bucketElementCounts[k]; l++) {
                        arr[index++] = bucket[k][l];
                    }
                    // 清空，为下一轮的初始数据做准备 !!!
                    bucketElementCounts[k] = 0;

                }
            }
        }

//        System.out.println("处理完 === " + Arrays.toString(arr));

    }

    public static void radixSortOrigin(int[] arr) {
        // 第一轮(针对每个元素的个位数排序处理)
        int[][] bucket = new int[10][arr.length];

        // 记录每个桶中数据， 利用一个数组
        int[] bucketElementCounts = new int[10];

        for (int i = 0; i < arr.length; i++) {
            // 取出每个元素的个位数
            int digitOfElement = arr[i] % 10;

            bucket[digitOfElement][bucketElementCounts[digitOfElement]] = arr[i];
            bucketElementCounts[digitOfElement]++;
        }

        int index = 0;
        for (int k = 0; k < 10; k++) {
            // 如果桶中有数据， 才放到原数组
            if (bucketElementCounts[k] > 0) {
                for (int l = 0; l < bucketElementCounts[k]; l++) {
                    arr[index++] = bucket[k][l];
                }
                bucketElementCounts[k] = 0;

            }
        }
        System.out.println("第一轮处理完 === " + Arrays.toString(arr));

        for (int i = 0; i < arr.length; i++) {
            // 取出每个元素的个位数
            int digitOfElement = arr[i] / 10 % 10;

            bucket[digitOfElement][bucketElementCounts[digitOfElement]] = arr[i];
            bucketElementCounts[digitOfElement]++;
        }


        index = 0;
        for (int k = 0; k < 10; k++) {
            // 如果桶中有数据， 才放到原数组
            if (bucketElementCounts[k] > 0) {
                for (int l = 0; l < bucketElementCounts[k]; l++) {
                    arr[index++] = bucket[k][l];
                }
                bucketElementCounts[k] = 0;
            }
        }
        System.out.println("第二轮处理完 === " + Arrays.toString(arr));

        for (int i = 0; i < arr.length; i++) {
            // 取出每个元素的个位数
            int digitOfElement = arr[i] / 100 % 10;

            bucket[digitOfElement][bucketElementCounts[digitOfElement]] = arr[i];
            bucketElementCounts[digitOfElement]++;
        }


        index = 0;
        for (int k = 0; k < 10; k++) {
            // 如果桶中有数据， 才放到原数组
            if (bucketElementCounts[k] > 0) {
                for (int l = 0; l < bucketElementCounts[k]; l++) {
                    arr[index++] = bucket[k][l];
                }
            }
        }
        System.out.println("第三轮处理完 === " + Arrays.toString(arr));
    }
}
