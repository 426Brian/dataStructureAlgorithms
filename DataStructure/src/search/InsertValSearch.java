package search;

import java.util.Arrays;

public class InsertValSearch {
    public static void main(String[] args) {
        int[] arr = new int[100];
        for (int i = 0; i < 100; i++) {
            arr[i] = i + 1;
        }

        System.out.println(Arrays.toString(arr));

        int index = insertValSearch(arr, 0, arr.length - 1, 100);

        System.out.println("index = " + index);

    }

    /**
     * 插值查找
     * 推导出的公式：mid = left + (right - left) * (findVal - arr[left]) / arr[right] - arr[left]
     *
     * @param arr
     * @param left
     * @param right
     * @param findVal
     * @return
     */
    public static int insertValSearch(int[] arr, int left, int right, int findVal) {

        int mid = left + (right - left) * (findVal - arr[left]) / arr[right] - arr[left];

        while (left <= right && mid >= 0 && mid < arr.length - 1) {
            if (findVal > arr[mid]) {
                // 向右
                mid = mid + 1;
            } else if (findVal < arr[mid]) {
                // 向左
                mid = mid - 1;
            } else {
                return mid;
            }
        }

        return -1;
    }
}
