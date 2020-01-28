package search;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BinarySearch {
    public static void main(String[] args) {
        int[] arr = {1, 8, 10, 89, 89, 89, 1000, 1234};

        int result = binarySearch(arr, 0, arr.length, 89);

        int result2 = binarySearch2(arr, 89);
        System.out.println("result = " + result + ", result2 = " + result2);


        List list1 = binarySearch3(arr, 89);
        List list2 = binarySearch4(arr, 0, arr.length, 89);
        System.out.println("list1 = " + list1 + ", list2 = " + list2);
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
            return binarySearch(arr, left, right, findVal);
        } else if (findVal < midVal) {
            right = mid - 1;
            return binarySearch(arr, left, right, findVal);
        } else {
            return mid;
        }

    }

    public static int binarySearch2(int[] arr, int findVal) {
        int low = 0;
        int high = arr.length - 1;
        int mid = 0;
        int midVal = 0;

        while (low <= high) {
            mid = (low + high) >>> 1;
            midVal = arr[mid];

            if (findVal > midVal) {
                low = mid + 1;
            } else if (findVal < midVal) {
                high = mid - 1;
            } else {
                return mid;
            }
        }

        return -1;
    }


    // 有重复值时找出所有下标
    public static List binarySearch3(int[] arr, int findVal) {
        int low = 0;
        int high = arr.length - 1;
        int mid = 0;
        int midVal = 0;
        List<Integer> list = new ArrayList<>();

        while (low <= high) {
            mid = (low + high) >>> 1;
            midVal = arr[mid];

            if (findVal > midVal) {
                low = mid + 1;
            } else if (findVal < midVal) {
                high = mid - 1;
            } else {
                int tmp = mid - 1;
                while (true) {
                    // 向左查找
                    if (tmp < 0 || arr[tmp] != findVal) {
                        break;
                    }
                    list.add(tmp);
                    tmp -= 1;
                }
                list.add(mid);

                tmp = mid + 1;
                while (true) {
                    if (tmp > arr.length || arr[tmp] != findVal) {
                        break;
                    }
                    list.add(tmp);
                    tmp += 1;
                }
                break;
            }
        }

        if (list.size() < 1) {
            list.add(-1);
        }

        return list;
    }

    // 有重复值时找出所有下标
    public static List binarySearch4(int[] arr, int left, int right, int findVal) {
        // 没有找到
        if (left > right) {
            return null;
        }

        int mid = (left + right) / 2;
        int midVal = arr[mid];


        if (findVal > midVal) {
            left = mid + 1;
            return binarySearch4(arr, left, right, findVal);
        } else if (findVal < midVal) {
            right = mid - 1;
            return binarySearch4(arr, left, right, findVal);
        } else {
            List<Integer> list = new ArrayList<>();
            int tmp = mid - 1;

            while (true) {
                if (tmp < 0 || arr[tmp] != findVal) {
                    break;
                }
                list.add(tmp);
                tmp -= 1;
            }

            list.add(mid);
            tmp = mid + 1;
            while (true) {
                if (tmp > arr.length - 1 || arr[tmp] != findVal) {
                    break;
                }
                list.add(tmp);
                tmp += 1;
            }

            return list;
        }

    }
}
