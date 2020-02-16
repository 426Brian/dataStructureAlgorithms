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
        System.out.println("排序前 === " + Arrays.toString(arr));
        bubbleSort(arr);
        System.out.println("排序后 === " + Arrays.toString(arr));

        System.out.println("----------------------");
        int[] arr2 = new int[80000];

        for (int i = 0; i < arr2.length; i++) {
            arr2[i] = (int) (Math.random() * 8000000);
        }

        long start = System.currentTimeMillis();
        bubbleSort(arr2);
        long end = System.currentTimeMillis();
        System.out.println("八万数据排序所用时间 "+(end-start)/1000f+"s");
    }


    public static void bubbleSort(int[] arr) {
        System.out.println("优化后 ========= ");
        // 优化 flag 表示是否进行过交换
        int tmp;
        boolean flag = false;
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length - 1 - i; j++) {
                if (arr[j] > arr[j + 1]) {
                    flag = true;
                    tmp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = tmp;
                }

            }

//            System.out.println("第" + (j + 1) + "趟排序后 === " + Arrays.toString(arr));
            if (!flag) {
                break;
            } else {
                // flag 在外层for 循环的外部定义赋值, 所以每循环一轮需要重新赋值 跟下面的 bubbleSortModify(int[] arr) 方法比较
                flag = false;
            }
        }
    }

    public static void bubbleSortModify(int[] arr) {
        System.out.println("优化后 ========= ");
        int tmp;

        for (int i = 0; i < arr.length - 1; i++) {
            // 优化 flag 表示是否进行过交换
            boolean flag = false;
            for (int j = 0; j < arr.length - 1 - i; j++) {
                if (arr[j] > arr[j + 1]) {
                    flag = true;
                    tmp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = tmp;
                }

            }

            if (!flag) {
                break;
            }

            // flag 在外层for 循环的内部定义赋值, 所以每循环一轮都会重新定义, 所以不需要特意重新赋值对比上面的 bubbleSort(int[] arr)方法
           /* else {

                flag = false;
            }*/
        }
    }
}
