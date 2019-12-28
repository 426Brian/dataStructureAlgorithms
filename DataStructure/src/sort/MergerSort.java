package sort;

import java.util.Arrays;

public class MergerSort {
    public static void main(String[] args) {
        int[] arr = {8, 4, 5, 7, 1, 3, 6, 2};
        int[] tmp = new int[arr.length];

        mergerSort(arr, 0, arr.length -1, tmp);

        System.out.println(Arrays.toString(arr));
    }


    // 分+ 合
    public static void mergerSort(int[] arr, int left, int right, int[] tmp) {
        if (left < right) {
            int mid = (left + right) / 2; // 中间索引

            // 向左递归
            mergerSort(arr, left, mid, tmp);

            // 向右递归
            mergerSort(arr, mid + 1, right, tmp);

            merge(arr, left, mid, right, tmp);
        }
    }

    /**
     * @param arr   原始数据
     * @param left  左边有序序列初始索引
     * @param mid   中间索引
     * @param right 右边索引
     * @param tmp   中转数组
     */
    public static void merge(int[] arr, int left, int mid, int right, int[] tmp) {
        int i = left;
        int j = mid + 1;
        int t = 0; // 指向tmp 数组的当前索引

        // 1. 先把左右两边的数据按照规则填充到tmp 数组
        // 直到左右两边的有序序列，有一边处理完为止
        while (i <= mid && j <= right) {
            if (arr[i] <= arr[j]) {
                // 左边当前元素小于等于右边当前元素， 左边当前元素拷贝到tmp 数组
                // 然后 t 后移 i 后移

                tmp[t] = arr[i];
                t++;
                i++;
            } else {
                tmp[t] = arr[j];
                t++;
                j++;
            }
        }

        // 2. 剩余数据一边的数据全部填充到tmp
        while (i <= mid) {
            // 左边剩余
            tmp[t] = arr[i];
            t++;
            i++;
        }

        while (j <= right) {
            // 右边剩余
            tmp[t] = arr[j];
            t++;
            j++;
        }

        // 3. 将tmp 数组元素拷贝到 arr
        t = 0;
        int tmpLeft = left;
        while (tmpLeft <= right) {
            arr[tmpLeft] = tmp[t];
            t++;
            tmpLeft++;
        }
    }
}
