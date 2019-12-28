package search;

public class BinarySearch {
    public static void main(String[] args) {

    }

    /**
     * @param arr
     * @param left
     * @param right
     * @param findVal
     * @return
     */
    public static int binarySearch(int[] arr, int left, int right, int findVal) {
        // 没有找到
        if(left > right){
            return -1;
        }


        int mid = (left + right) / 2;
        int midVal = arr[mid];
        if (findVal > midVal) {
            left = mid + 1;
            binarySearch(arr, left, right, findVal);
        } else if (findVal < midVal) {
            right = mid - 1;
            binarySearch(arr, left, right, findVal);
        }else {
            return mid;
        }
        return 0;
    }
}
