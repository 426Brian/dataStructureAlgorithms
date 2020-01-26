package algorithm;

import java.util.Arrays;

public class Floyd {
    public static void main(String[] args) {
        char[] vertex = {'A', 'B', 'C', 'D', 'E', 'F', 'G'};
        int[][] matrix = new int[vertex.length][vertex.length];
        final int N = 65535;

        matrix[0] = new int[]{0, 5, 7, N, N, N, 2};
        matrix[1] = new int[]{5, 0, N, 9, N, N,3};
        matrix[2] = new int[]{7, N, 0, N, 8, N, N};
        matrix[3] = new int[]{N, 9, N, 0, N, 4, N};
        matrix[4] = new int[]{N, N, 8, N, 0, 5, 4};
        matrix[5] = new int[]{N, N, N, 4, 5, 0, 6};
        matrix[6] = new int[]{2, 3, N, N, 4, 6, 0};

        Graph2 graph2 = new Graph2(vertex, matrix);
        graph2.floyd();
        graph2.showResult();


    }
}


class Graph2 {
    private char[] vertex;
    private int[][] matrix;
    private int[][] pre;

    public Graph2(char[] vertex, int[][] matrix) {
        this.vertex = vertex;
        this.matrix = matrix;
        this.pre = new int[vertex.length][vertex.length];

        for (int i = 0; i < vertex.length; i++) {
            Arrays.fill(pre[i], i);
        }
    }

    public void showResult() {
        for (int i = 0; i < vertex.length; i++) {
            for (int j = 0; j < vertex.length; j++) {
                System.out.print(vertex[pre[i][j]] + " ");
            }

            System.out.println();
            for (int k = 0; k < vertex.length; k++) {
                System.out.print("(" + vertex[i] + "到" + vertex[k] + ")" + matrix[i][k] + " ");
            }
            System.out.println();
            System.out.println();
        }
    }


    public void floyd() {
        int len = 0;

        // 中间顶点遍历 [A B C D E F G]
        for (int k = 0; k < vertex.length; k++) {

            // 从 i 顶点开始出发 [A B C D E F G]
            for (int i = 0; i < vertex.length; i++) {

                // 到达 j 顶点 [A B C D E F G]
                for (int j = 0; j < vertex.length; j++) {
                    // i 出发 经过 k 到 j 的距离
                    len = matrix[i][k] + matrix[k][j];

                    if(len < matrix[i][j]){
                        // 小于直连距离
                        len = matrix[i][j];

                        // 更新前驱顶点
                        pre[i][j] = pre[k][j];
                    }
                }
            }
        }
    }
}
