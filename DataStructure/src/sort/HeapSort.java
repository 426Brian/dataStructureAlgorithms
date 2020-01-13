package sort;

import java.util.Arrays;

public class HeapSort {

    public static void main(String[] args) {
        // 升序排列，需要构建大顶堆
        int[] arr = {4, 6, 8, 5, 9};

        heapSort(arr);

        int[] arr2 = new int[80000];

        for (int i = 0; i < arr2.length; i++) {
            arr2[i] = (int) (Math.random() * 8000000);
        }

        long start = System.currentTimeMillis();
        heapSort(arr2);
        long end = System.currentTimeMillis();
        System.out.println("8万数据排序所用时间 "+(end-start));
    }

    public static void heapSort(int[] arr) {
        System.out.println("堆排序 === ");

        // 1. 数组调整成大顶堆
        for (int i = arr.length / 2 - 1; i >= 0; i--) {
            adjustHeap(arr, i, arr.length);
        }

//        System.out.println("大顶堆 ==> " + Arrays.toString(arr));

        // 2. 交换最顶元素和末尾元素
        for (int j = arr.length - 1; j > 0; j--) {
            int tmp = arr[0];
            arr[0] = arr[j];
            arr[j] = tmp;
            adjustHeap(arr, 0, j);
        }
//        System.out.println("有序 ==> " + Arrays.toString(arr));
    }

    // 将一个数组（二叉树）， 调整成大顶堆

    /**
     * @param arr    {4, 6, 8, 5, 9}  -> {4, 9, 8, 5, 6} -> { 9, 6, 8, 5, 4}
     * @param i      非叶子节点在数组中索引
     * @param length 对多少个元素继续调整， length 逐渐减少
     */
    public static void adjustHeap(int[] arr, int i, int length) {
        int tmp = arr[i];
        // 1. k = i * 2 + 1   k 是i 节点的左子节点
        for (int k = i * 2 + 1; k < length; k = k * 2 + 1) {
            if (k + 1 < length && arr[k] < arr[k + 1]) {
                // 如果左子节点小于右子节点，k 指向右子节点， 目的找到子节点中较大值
                k++;
            }
            if (arr[k] > tmp) {
                // 子节点大于父节点
                arr[i] = arr[k];    // 较大值交换给当前节点
                i = k; // i 指向 K 继续循环
            } else {
                break;
            }
        }

        // for 循环结束后， 我们已经将以 i 为父节点树的最大值，放在最顶
        arr[i] = tmp;
    }
}
