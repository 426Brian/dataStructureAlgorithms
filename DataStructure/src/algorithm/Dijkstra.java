package algorithm;

import java.util.Arrays;

public class Dijkstra {

    public static void main(String[] args) {
        char[] vertex = {'A', 'B', 'C', 'D', 'E', 'F', 'G'};
        int[][] matrix = new int[vertex.length][vertex.length];
        final int N = 65535;

        matrix[0] = new int[]{N, 5, 7, N, N, N, 2};
        matrix[1] = new int[]{5, N, N, 9, N, N, 2};
        matrix[2] = new int[]{7, N, N, N, 8, N, 2};
        matrix[3] = new int[]{N, 9, N, N, N, 4, N};
        matrix[4] = new int[]{N, N, 8, N, N, 5, 4};
        matrix[5] = new int[]{N, N, N, 4, 5, N, 6};
        matrix[6] = new int[]{2, 3, N, N, 4, 6, N};

        Graph graph = new Graph(vertex, matrix);

        graph.showGraph();

        graph.djs(6);
    }


}

class VisitedVertex {

    // 记录每个顶点是否访问过, 1:已访问 0:未访问
    public int[] alreadyArr;

    // 记录每个顶点对应值的前一个下标, 会动态更新
    public int[] preVisitArr;

    // 记录出发顶点到其他顶点的距离
    public int[] disArr;

    public VisitedVertex(int length, int index) {
        this.alreadyArr = new int[length];
        this.preVisitArr = new int[length];
        this.disArr = new int[length];

        this.alreadyArr[index] = 1;
        Arrays.fill(disArr, 65535);
        this.disArr[index] = 0;
    }

    @Override
    public String toString() {
        return "VisitedVertex{" +
                "alreadyArr=" + Arrays.toString(alreadyArr) +
                ", preVisitArr=" + Arrays.toString(preVisitArr) +
                ", disArr=" + Arrays.toString(disArr) +
                '}';
    }

    /**
     * 判断是否已经访问过
     *
     * @param index
     * @return
     */
    public boolean in(int index) {
        return alreadyArr[index] == 1;
    }

    /**
     * 更新到出发点的距离
     *
     * @param index
     * @param len
     */
    public void updateDis(int index, int len) {
        disArr[index] = len;
    }

    /**
     * 更新对应点的前驱顶点
     *
     * @param pre
     * @param index
     */
    public void updatePre(int pre, int index) {
        preVisitArr[pre] = index;
    }


    /**
     * 到出发点距离
     *
     * @param index
     * @return
     */
    public int getDis(int index) {
        return disArr[index];
    }

    public int updateArr() {
        int min = 65535, index = 0;
        for (int i = 0; i < alreadyArr.length; i++) {
            if (alreadyArr[i] == 0 && disArr[i] < min) {
                // 未访问过
                min = disArr[i];
                index = i;
            }
        }

        alreadyArr[index] = 1;
        return index;
    }


}

class Graph {
    private char[] vertex;
    private int[][] matrix;
    private VisitedVertex vv;

    public Graph(char[] vertex, int[][] matrix) {
        this.vertex = vertex;
        this.matrix = matrix;
    }

    public void showGraph() {
        for (int[] link : matrix) {
            System.out.println(Arrays.toString(link));
        }
    }

    public void djs(int index) {
        vv = new VisitedVertex(vertex.length, index);
        System.out.println(vv.toString());
        update(index);

        for (int i = 1; i < vertex.length; i++) {
            index = vv.updateArr();
            update(index);
        }

        showResult();

    }

    private void update(int index) {
        int len = 0;
        for (int i = 0; i < matrix[index].length; i++) {
            // 出发点到index 点的距离 + index 点到 i 顶点的距离
            len = vv.getDis(index) + matrix[index][i];

            if (!vv.in(i) && len < vv.getDis(i)) {
                // 没访问过, len 小于出发点到 i 顶点的距离
                vv.updatePre(i, index);
                vv.updateDis(i, len);
            }
        }

    }

    public void showResult() {
        System.out.println("result  ========================== ");
        System.out.println(Arrays.toString(vv.alreadyArr));
        System.out.println(Arrays.toString(vv.preVisitArr));
        for (int i = 0; i < vertex.length; i++) {
            System.out.print(vertex[i] + "[" + vv.disArr[i] + "]");
        }
    }
}
