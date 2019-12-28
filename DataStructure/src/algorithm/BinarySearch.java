package algorithm;

public class BinarySearch {
    public static void main(String[] args) {
        int[] arr = {1, 3, 8, 10, 11, 67, 100};
        int index = binarySearch(arr, 1);

        System.out.println("index == "+index);
    }

    /**
     * 二分法查找非递归
     *
     * @param arr
     * @param target
     * @return
     */
    public static int binarySearch(int[] arr, int target) {
        int left = 0;
        int right = arr.length - 1;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (arr[mid] == target) {
                return mid;
            } else if (arr[mid] > target) {
                right = mid - 1;// 向左查找
            } else {
                left = mid + 1;
            }
        }
        return -1;
    }
}
