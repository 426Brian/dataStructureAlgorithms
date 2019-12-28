package sort;

import java.util.Arrays;

public class InsertSort {
    public static void main(String[] args) {
        int[] arr = {101, 34, 119, 1};

        insertSort(arr);
        System.out.println(Arrays.toString(arr));
    }

    public static void insertSort(int[] arr) {

        for (int i = 1; i < arr.length; i++) {
            int insertVal = arr[i];
            int insertIndex = i - 1;

            while (insertIndex >= 0 && insertVal < arr[insertIndex]) {
                // 覆盖 arr[i] 的值，arr[i]已经保存在insertVal
                arr[insertIndex + 1] = arr[insertIndex];

                // 继续向前比较
                insertIndex--;
            }

            // while 循环结束找到inserIndex 的值
            if(insertIndex + 1 != i){
                // 是否需要赋值
                arr[insertIndex + 1] = insertVal;
            }

        }

       /* // 定义待插入的数组
        int insertVal = arr[1];
        int insertIndex = 1 - 1;

        // 给inserVal 找到插入的位置
        while (insertIndex >= 0 && insertVal < arr[insertIndex]) {
            arr[insertIndex + 1] = arr[insertIndex];
            insertIndex--;
        }

        // 退出while 循环时找到了插入位置 insertIndex+1
        arr[insertIndex + 1] = insertVal;

        System.out.println("第一轮插入 == ");
        System.out.println(Arrays.toString(arr));*/
    }
}


