package algorithm;

import java.util.Arrays;

public class Kruskal {
    // 两个顶点不能连通
    private static final int INF = Integer.MAX_VALUE;
    private int edgeNum;
    private char[] vertex;
    private int[][] matrix;

    public Kruskal(char[] vertex, int[][] matrix) {
        this.vertex = new char[vertex.length];

        for (int i = 0; i < vertex.length; i++) {
            this.vertex[i] = vertex[i];
        }

        this.matrix = new int[vertex.length][vertex.length];

        for (int i = 0; i < vertex.length; i++) {
            for (int j = 0; j < vertex.length; j++) {
                this.matrix[i][j] = matrix[i][j];
            }
        }

        // 统计边
        for (int i = 0; i < vertex.length; i++) {
            for (int j = i + 1; j < vertex.length; j++) {
                if (this.matrix[i][j] != INF) {
                    edgeNum++;
                }
            }
        }

        System.out.println("edgeNum == " + edgeNum);
    }

    public static void main(String[] args) {
        char[] data = {'A', 'B', 'C', 'D', 'E', 'F', 'G'};
        int verx = data.length;

        int[][] matirx = {
                {0, 12, INF, INF, INF, 16, 14},
                {12, 0, 10, INF, INF, 7, INF},
                {INF, 10, 0, 3, 5, 6, INF},
                {INF, INF, 3, 0, 4, INF, INF},
                {INF, INF, 5, 4, 0, 2, 8},
                {16, 7, 6, INF, 2, 0, 9},
                {14, INF, INF, INF, 8, 9, 0}
        };
        Kruskal kruscal = new Kruskal(data, matirx);
        kruscal.print();

        EData[] edges = kruscal.getEdges();

        System.out.println("排序前 == 边数[" + edges.length + "] ** " + Arrays.toString(edges));

        kruscal.bubbleSort(edges);
        System.out.println("排序后 == 边数[" + edges.length + "] ** " + Arrays.toString(edges));

        kruscal.kruskal();
    }

    public void print() {
        System.out.println("邻接矩阵 \n");

        for (int[] arr : matrix) {
            System.out.println(Arrays.toString(arr));
        }
    }

    // 对边排序, 冒泡
    public void bubbleSort(EData[] edges) {
        EData tmp = null;
        for (int i = 0; i < edges.length - 1; i++) {
            for (int j = 0; j < edges.length - i - 1; j++) {
                if (edges[j].weight > edges[j + 1].weight) {
                    tmp = edges[j];
                    edges[j] = edges[j + 1];
                    edges[j + 1] = tmp;
                }
            }
        }
    }

    /**
     * @param ch 顶点的值， 比如 'A'
     */
    public int getPosition(char ch) {
        for (int i = 0; i < vertex.length; i++) {
            if (vertex[i] == ch) {
                return i;
            }
        }

        return -1;
    }

    /**
     * 获取图中边放到 EData[] 中
     *
     * @return
     */
    public EData[] getEdges() {
        int index = 0;
        EData[] edges = new EData[edgeNum];
        for (int i = 0; i < vertex.length; i++) {
            for (int j = i + 1; j < vertex.length; j++) {
                if (matrix[i][j] != INF) {
                    edges[index++] = new EData(
                            vertex[i], vertex[j], matrix[i][j]
                    );
                }
            }
        }
        return edges;
    }


    public int getEnd(int[] ends, int i) {
        while (ends[i] != 0) {
            i = ends[i];
        }

        return i;
    }

    /**
     * 克鲁斯卡尔算法
     */
    public void kruskal() {
        int index = 0;
        int[] ends = new int[edgeNum];
        EData[] rets = new EData[vertex.length - 1];

        // 所有边的集合
        EData[] edges = getEdges();

        // 从小到大排序
        bubbleSort(edges);

        // 遍历edges 数组，将边添加到最小生成树
        for (int i = 0; i < edgeNum; i++) {
            int p1 = getPosition(edges[i].start);
            int p2 = getPosition(edges[i].end);

            int m = getEnd(ends, p1);
            int n = getEnd(ends, p2);

            // Kruskal 算法最难之处 判断构成回路
            if (m != n) {
                ends[m] = n;
                rets[index++] = edges[i];
            }
        }
        System.out.println("最小生成树 " + Arrays.toString(rets));
        for (EData edata : rets) {
            System.out.println("最小生成树 " + edata);
        }

    }

}

// 表示一条边
class EData {
    char start;
    char end;
    int weight;

    public EData(char start, char end, int weight) {
        this.start = start;
        this.end = end;
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "EData" +
                "[<" + start +
                ", " + end +
                ">=" + weight +
                ']';
    }
}
