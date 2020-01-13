package algorithm;

import java.util.Arrays;

public class Prim {
    public static void main(String[] args) {
        char[] data = {'A', 'B', 'C', 'D', 'E', 'F', 'G'};
        int verx = data.length;
        // 10000 表示不连通
        int[][] weight = {
                {10000, 5, 7, 10000, 10000, 10000, 2},
                {5, 10000, 10000, 9, 10000, 10000, 3},
                {7, 10000, 10000, 10000, 8, 10000, 10000},
                {10000, 9, 10000, 10000, 10000, 4, 10000},
                {10000, 10000, 8, 10000, 10000, 5, 4},
                {10000, 10000, 10000, 4, 5, 10000, 6},
                {2, 3, 10000, 10000, 4, 6, 10000}
        };

        MGraph mGraph = new MGraph(verx);
        MinTree minTree = new MinTree();

        minTree.createGraph(mGraph, verx, data, weight);

        minTree.showGraph(mGraph);

        // 假设从顶点A 开始
        minTree.prim(mGraph, 0);
    }
}

// 最小生成树
class MinTree {
    public void createGraph(MGraph graph, int verxs, char[] data, int[][] weight) {
        int i, j;
        for (i = 0; i < verxs; i++) {
            graph.data[i] = data[i];

            for (j = 0; j < verxs; j++) {
                graph.weight[i][j] = weight[i][j];
            }
        }
    }

    public void showGraph(MGraph graph) {
        for (int[] link : graph.weight) {
            System.out.println(Arrays.toString(link));
        }
    }

    // prim 算法

    /**
     * @param graph
     * @param v     从第几个顶点生成
     */
    public void prim(MGraph graph, int v) {
        int[] isVisited = new int[graph.verx];
        isVisited[v] = 1;
        int h1 = -1;
        int h2 = -1;
        int minWeight = 10000;

        for (int k = 1; k < graph.verx; k++) {
            // grph.verx -1 条边
            for (int i = 0; i < graph.verx; i++) {
                // 访问过的点
                for (int j = 0; j < graph.verx; j++) {
                    // 未访问过的点
                    if (isVisited[i] == 1 && isVisited[j] == 0 && graph.weight[i][j] < minWeight) {
                        minWeight = graph.weight[i][j];
                        h1 = i;
                        h2 = j;
                    }
                }
            }

            // 找到一条边是最小
            System.out.println("边<" + graph.data[h1] + "," + graph.data[h2] + "> 权值：" + minWeight);
            // 将当前节点设置为已访问
            isVisited[h2] = 1;

            // minWeight 重新设置成最大值
            minWeight = 10000;

        }

    }
}

class MGraph {
    int verx;       // 节点数
    char[] data;    // 存放节点数据
    int[][] weight; // 存放边，邻接矩阵


    public MGraph(int verx) {
        this.verx = verx;
        data = new char[verx];
        weight = new int[verx][verx];
    }
}
