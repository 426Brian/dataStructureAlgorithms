package search;

public class BinarySearch {
    public static void main(String[] args) {
        int[] arr = {1, 8, 10, 89,1000, 1234};

        int result = binarySearch(arr, 0, arr.length, 89);

        int result2 = binarySearch2(arr, 89);

        System.out.println("result = "+result+" result2 = "+result2);
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
        if (left > right) {
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
        } else {
            return mid;
        }
        return 0;
    }

    public static int binarySearch2(int[] arr, int findVal) {
        int low = 0;
        int high = arr.length - 1;

        while (low <= high) {
            int mid = (low + high) >>> 1;
            int midVal = arr[mid];

            if (findVal > midVal) {
                low = mid + 1;
            } else if (findVal < midVal) {
                low = mid - 1;
            } else {
                return mid;
            }
        }

        return -1;
    }
}
