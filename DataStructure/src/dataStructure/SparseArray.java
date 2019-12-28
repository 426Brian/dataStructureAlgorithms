package dataStructure;

public class SparseArray {
    public static void main(String[] args) {
        // 创建原始二维数组 11 * 11
        // 0 表示没有棋子， 1 表示黑子 2 表示篮子
        int[][] chessArr = new int[11][11];
        chessArr[1][2] = 1;
        chessArr[2][3] = 2;

        // 记录非 0 个数
        int sum = 0;

        System.out.println("原始二维数组 === ");
        // 输出原始二维数组
        for (int[] arr : chessArr) {
            for (int data : arr) {
                System.out.print(data + "\t");
                if (data != 0) {
                    sum++;
                }
            }
            System.out.println();
        }

        // 二维数组转成稀疏数组
        int[][] sparseArr = new int[sum + 1][3];
        sparseArr[0][0] = 11;
        sparseArr[0][1] = 11;
        sparseArr[0][2] = sum;

        int tmp = 0;
        for (int i = 0; i < chessArr.length; i++) {
            for (int j = 0; j < chessArr[i].length; j++) {
                if (chessArr[i][j] != 0) {
                    tmp += 1;
                    sparseArr[tmp][0] = i;
                    sparseArr[tmp][1] = j;
                    sparseArr[tmp][2] = chessArr[i][j];
                }
            }

        }

        System.out.println("稀疏数组 === ");
        // 输出稀疏数组
        for (int[] row : sparseArr) {
            for (int data : row) {
                System.out.print(data + "\t");
            }
            System.out.println();
        }

        // 根据稀疏数组恢复二维数组
        int[][] chessArr2 = new int[sparseArr[0][0]][sparseArr[0][1]];
        for (int i = 1; i < sparseArr.length; i++) {
            chessArr2[sparseArr[i][0]][sparseArr[i][1]] = sparseArr[i][2];
        }

        System.out.println("恢复后的二维数组 === ");
        for (int[] row : chessArr2) {
            for (int data : row) {
                System.out.print(data + "\t");
            }
            System.out.println();
        }
    }
}
