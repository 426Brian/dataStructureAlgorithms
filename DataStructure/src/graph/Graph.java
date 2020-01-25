package graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

public class Graph {
    private ArrayList<String> vertexList; // 顶点集合
    private int[][] edges; // 存储图的邻接矩阵
    private int numOfEdges;  // 表示边的数目
    private boolean[] isVisited;  // 记录某个节点是否别访问


    public Graph(int n) {
        edges = new int[n][n];
        vertexList = new ArrayList<String>(n);
        isVisited = new boolean[n];
    }

    public static void main(String[] args) {
        int n = 5;
        String vertexValue[] = {"A", "B", "C", "D", "E"};
        Graph graph = new Graph(n);

        // 循环添加顶点
        for (String vertex : vertexValue) {
            graph.insertVertex(vertex);
        }

        // 添加边 （建立顶点之间的关系）
        // A-B  A-C  B-C  B-D  B-E
        graph.insertEdge(0, 1, 1); // A - B
        graph.insertEdge(0, 2, 1);
        graph.insertEdge(1, 2, 1);
        graph.insertEdge(1, 3, 1);
        graph.insertEdge(1, 4, 1);


        // 显示
        graph.showGraph();

        // 深度优先
        graph.dfs();

        // 广度优先

    }

    // 图中常用方法
    // 添加顶点
    public void insertVertex(String vertex) {
        vertexList.add(vertex);
    }

    // 添加边

    /**
     * @param v1     顶点下标
     * @param v2     第二个顶点对应的下标
     * @param weight
     */
    public void insertEdge(int v1, int v2, int weight) {
        edges[v1][v2] = weight;
        edges[v2][v1] = weight;
        numOfEdges++;
    }

    // 返回顶点个数
    public int getNumOfVertex() {
        return vertexList.size();
    }

    // 返回边的数目
    public int getNumOfEdges() {
        return numOfEdges;
    }

    // 返回回结点对应的数据
    public String getValueByIndex(int index) {
        return vertexList.get(index);
    }

    // 返回 v1 v2 的权值
    public int getWeight(int v1, int v2) {
        return edges[v1][v2];
    }

    // 显示图对应的矩阵
    public void showGraph() {
        for (int[] link : edges) {
            System.err.println(Arrays.toString(link));
        }
    }


    // 得到一个邻接结点的下标

    /**
     * @param index
     * @return 如果存在返回对应的下标，否则返回-1
     */
    public int getFirstNeighbor(int index) {
        for (int j = 0; j < vertexList.size(); j++) {
            if (edges[index][j] > 0) {
                return j;
            }

        }

        return -1;
    }

    // 根据前一个邻接节点的下标获得下一个邻接结点
    public int getNextNeighbor(int v1, int v2) {
        for (int j = v2 + 1; j < vertexList.size(); j++) {
            if (edges[v1][j] > 0) {
                return j;
            }
        }

        return -1;
    }

    // 深度优先遍历
    // i  第一次是 0
    private void dfs(boolean[] isVisited, int i) {
        // 首先我们访问该结点， 输出
        System.out.print(getValueByIndex(i) + " -> ");
        // 将该节点设置成已访问
        isVisited[i] = true;

        // 查找结点 i 的第一个邻接结点 W
        int w = getFirstNeighbor(i);

        while (w != -1) {
            // 节点存在
            if (!isVisited[w]) {
                // 没访问过
                dfs(isVisited, w);
            }

            // 已经被访问过
            w = getNextNeighbor(i, w);
        }
    }

    /**
     * 重载DFS(depth-first-search) 遍历所有节点为解决非连通图的问题
     */
    public void dfs() {
        for (int i = 0; i < getNumOfVertex(); i++) {
            if (!isVisited[i]) {
                dfs(isVisited, i);
            }
        }
    }

    // 对一个节点进行广度遍历
    public void bfs(boolean[] isVisited, int i) {
        int u; // 队列头节点对应的下标
        int w; // 邻接点
        // 队列，记录节点访问顺序
        LinkedList linkedList = new LinkedList();
        System.out.println(getValueByIndex(i) + " -> ");

        // 标记为已访问
        isVisited[i] = true;

        // 节点加入队列
        linkedList.addLast(i);

        while (!linkedList.isEmpty()) {
            // 取出队列头节点下标
            u = (Integer) linkedList.removeFirst();

            // 得到邻节点下标
            w = getFirstNeighbor(u);

            while (w != -1) {
                // 找到
                if (!isVisited[w]) {
                    // 未访问过
                    System.out.println(getValueByIndex(i) + " -> ");
                    isVisited[w] = true;

                    linkedList.addLast(w);
                }

                // 以u 为前驱节点, 找到w 后面的下一个节点
                w = getNextNeighbor(u, w);
            }
        }
    }


    /**
     * 重载BFS(breadth-first-search) 遍历所有节点为解决非连通图的问题
     */
    public void bfs() {
        isVisited = new boolean[vertexList.size()];
        for (int i = 0; i < getNumOfVertex(); i++) {
            if (!isVisited[i]) {
                bfs(isVisited, i);
            }
        }
    }

}
