package sort;

import java.util.Arrays;

public class BubbleSort {
    public static void main(String[] args) {
        int[] arr = {3, 9, -1, 10, 20};

        // 冒泡排序过程
        // 第一遍排序，最大的数字排在最后
        int tmp = 0;
       /* for (int i = 0; i < arr.length - 1 - 0; i++) {
            if (arr[i] > arr[i + 1]) {
                tmp = arr[i];
                arr[i] = arr[i + 1];
                arr[i + 1] = tmp;
            }
            System.out.println("1 === " + Arrays.toString(arr));
        }

        for (int i = 0; i < arr.length - 1 - 1; i++) {
            if (arr[i] > arr[i + 1]) {
                tmp = arr[i];
                arr[i] = arr[i + 1];
                arr[i + 1] = tmp;
            }
            System.out.println("2 === " + Arrays.toString(arr));
        }

        for (int i = 0; i < arr.length - 1 - 2; i++) {
            if (arr[i] > arr[i + 1]) {
                tmp = arr[i];
                arr[i] = arr[i + 1];
                arr[i + 1] = tmp;
            }
            System.out.println("3 === " + Arrays.toString(arr));
        }

        for (int i = 0; i < arr.length - 1 - 3; i++) {
            if (arr[i] > arr[i + 1]) {
                tmp = arr[i];
                arr[i] = arr[i + 1];
                arr[i + 1] = tmp;
            }
            System.out.println("4 === " + Arrays.toString(arr));
        }

        for (int j = 0; j < arr.length - 1; j++) {
            for (int i = 0; i < arr.length - 1 - j; i++) {
                if (arr[i] > arr[i + 1]) {
                    tmp = arr[i];
                    arr[i] = arr[i + 1];
                    arr[i + 1] = tmp;
                }
                System.out.println(j + " === " + Arrays.toString(arr));
            }
        }*/


    }


    public static void bubbleSort(int[] arr){
        System.out.println("优化后 ========= ");
        // 优化 flag 表示是否进行过交换
        int tmp;
        boolean flag = false;
        for (int j = 0; j < arr.length - 1; j++) {
            for (int i = 0; i < arr.length - 1 - j; i++) {
                if (arr[i] > arr[i + 1]) {
                    flag = true;
                    tmp = arr[i];
                    arr[i] = arr[i + 1];
                    arr[i + 1] = tmp;
                }

            }

            System.out.println("第"+(j+1) + "趟排序后 === " + Arrays.toString(arr));
            if(!flag){
                break;
            }else{
                flag = false;
            }
        }
    }
}
