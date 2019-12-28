package sort;

public class QuickSort {
    public static void main(String[] args) {
        int[] arr = {-9, 78, 0, 23, -567, 70};
        quickSort(arr, 0, args.length - 1);
    }


    public static void quickSort(int[] arr, int left, int right) {
        int l = left; // 左下标
        int r = right;// 右下标
        // pivot 中轴
        int pivot = arr[(left + right) / 2];

        // 临时变量，用于交换
        int tmp = 0;

        // 比pivot 值小的放在左边
        while (l < r) {
            while (arr[l] < pivot) {
                l += 1;
            }
            while (arr[r] > pivot) {
                r -= 1;
            }

            if (l >= r) {
                break;
            }
            // 交换
            tmp = arr[l];
            arr[l] = arr[r];
            arr[r] = tmp;

            // 如果交换后， arr[l] == pivot, 前移
            if (arr[l] == pivot) {
                r -= 1;
            }

            if (arr[r] == pivot) {
                l += 1;
            }

            // 防止栈溢出
            if (l == r) {
                l += 1;
                r -= 1;
            }

            // 向左递归
            if (left < r) {
                quickSort(arr, left, r);
            }

            // 向右递归
            if (right > l) {
                quickSort(arr, l, right);
            }
        }
    }
}
