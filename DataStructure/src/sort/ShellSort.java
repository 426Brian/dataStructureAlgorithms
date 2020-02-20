package sort;

import java.util.Arrays;

public class ShellSort {
    public static void main(String[] args) {
        int[] arr = {8, 9, 1, 7, 2, 3, 5, 4, 6, 0};
        shellSort2(arr);

        System.out.println("排序后 == " + Arrays.toString(arr));

        int[] arr2 = new int[80000];
        for (int i = 0; i < arr2.length; i++) {
            arr2[i] = (int) (Math.random() * 8000000);
        }

        long start = System.currentTimeMillis();
        shellSort(arr2);


        long end = System.currentTimeMillis();

        System.out.println("8万数据排序交换法所用时间 " + (end - start) / 1000f + "s");

        long start2 = System.currentTimeMillis();
        shellSort2(arr2);

        long end2 = System.currentTimeMillis();

        System.out.println("8万数据排序移位法所用时间 " + (end2 - start2) / 1f + "ms");
    }

    // 希尔排序交换法
    public static void shellSort(int[] arr) {
        int tmp = 0;
        for (int gap = arr.length / 2; gap > 0; gap /= 2) {

            for (int i = 0; i < arr.length; i++) {

                for (int j = i + gap; j < arr.length; j += gap) {
                    // 如果当前元素大于加上步长后的那个元素，交换
                    if (arr[i] > arr[j]) {
                        tmp = arr[j];
                        arr[j] = arr[i];
                        arr[i] = tmp;

                    }
                }
            }
        }


    }

    // 希尔排序移位法
    public static void shellSort2(int[] arr) {
        int insertVal = 0;
        int insertIndex = 0;

        for (int gap = arr.length / 2; gap > 0; gap /= 2) {

            for (int i = gap; i < arr.length; i++) {
                insertVal = arr[i];
                insertIndex = i - gap;

                while (insertIndex >= 0 && insertVal < arr[insertIndex]) {
                    arr[insertIndex + gap] = arr[insertIndex];
                    insertIndex -= gap;
                }

                if (insertIndex + gap != i) {
                    // 位置有移动，需要重新赋值
                    arr[insertIndex + gap] = insertVal;
                }


            }
        }


    }

    public static void shellSortOrigin(int[] arr) {
        int tmp = 0;
        // 希尔排序第一轮排序， 10 个数据分成 5 组
        for (int i = 0; i < arr.length; i++) {
            // 遍历各组中所有的元素(5 组，每组 2 个数字) 步长为 5
            for (int j = i + 5; j < arr.length; j += 5) {
                // 如果当前元素大于加上步长后的那个元素，交换
                if (arr[i] > arr[j]) {
                    tmp = arr[j];
                    arr[j] = arr[i];
                    arr[i] = tmp;

                }
            }
        }
        System.out.println("第一轮排序 == " + Arrays.toString(arr));
        // 希尔排序第二轮排序， 10 个数据分成 2 组
        for (int i = 0; i < arr.length; i++) {
            // 遍历各组中所有的元素(5 组，每组 2 个数字) 步长为 5
            for (int j = i + 2; j < arr.length; j += 2) {
                // 如果当前元素大于加上步长后的那个元素，交换
                if (arr[i] > arr[j]) {
                    tmp = arr[j];
                    arr[j] = arr[i];
                    arr[i] = tmp;

                }
            }
        }
        System.out.println("第二轮排序 == " + Arrays.toString(arr));

        for (int i = 0; i < arr.length; i++) {
            // 遍历各组中所有的元素(5 组，每组 2 个数字) 步长为 5
            for (int j = i + 1; j < arr.length; j += 1) {
                // 如果当前元素大于加上步长后的那个元素，交换
                if (arr[i] > arr[j]) {
                    tmp = arr[j];
                    arr[j] = arr[i];
                    arr[i] = tmp;

                }
            }
        }
        System.out.println("第二轮排序 == " + Arrays.toString(arr));
    }

}
