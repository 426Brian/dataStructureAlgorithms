package dataStructure;

import java.util.Arrays;

public class SparseArray {
    public static void main(String[] args) {
        // 创建原始二维数组 11 * 11
        int row = 11, column = 11;
        int[][] chessArr = new int[row][column];
        // 0 表示没有棋子， 1 表示黑子 2 表示篮子
        chessArr[1][2] = 1;
        chessArr[2][3] = 2;


        // 记录非 0 个数
        int count = 0;
        for (int[] arr : chessArr) {
            for (int ele : arr) {
                if (ele != 0) {
                    count++;
                }
            }
        }

        // 输出原始二维数组
        System.out.println("原始二维数组");
        printArr(chessArr);

        // 二维数组转成稀疏数组
        int[][] spareArr = new int[count + 1][3];
        spareArr[0][0] = row;
        spareArr[0][1] = column;
        spareArr[0][2] = count;

        int tmp = 0;
        for (int i = 0; i < chessArr.length; i++) {
            for (int j = 0; j < chessArr.length; j++) {
                if (chessArr[i][j] != 0) {
                    tmp++;
                    spareArr[tmp][0] = i;
                    spareArr[tmp][1] = j;
                    spareArr[tmp][2] = chessArr[i][j];
                }
            }
        }

        // 输出稀疏数组
        System.out.println("稀疏数组");
        printArr(spareArr);

        // 根据稀疏数组恢复二维数组
        int[][] chessArr2 = new int[row][column];
        for (int i = 1; i < spareArr.length; i++) {
            chessArr2[spareArr[i][1]][spareArr[i][1]]  = spareArr[i][2];
        }

        // 恢复后的二维数组
        System.out.println("恢复后的二维数组");
        printArr(chessArr2);
    }

    public static void printArr(int[][] arr) {
        if (arr != null) {
            for (int[] a : arr) {
                System.out.println(Arrays.toString(a));
            }
        }
    }

}
