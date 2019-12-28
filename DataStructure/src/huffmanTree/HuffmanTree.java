package huffmanTree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class HuffmanTree {
    public static void main(String[] args) {
        int[] arr = {13, 7, 8, 3, 29, 6, 1};

        Node huffmanTree = createHuffmanTree(arr);

        System.out.println(" ================== ");
        preOrder(huffmanTree);
        System.out.println(" ================== ");

        System.out.println(huffmanTree.toString());
    }

    public static void preOrder(Node root) {
        if (root != null) {
            root.preOrder();
        } else {
            System.out.println("空树");
        }
    }


    public static Node createHuffmanTree(int[] arr) {
        // 1. 遍历数组
        // 2. 将每一个元素构成一个Node
        // 3. 将Node 放入ArrayList 中
        List<Node> nodes = new ArrayList<Node>();
        for (int value : arr) {
            nodes.add(new Node(value));
        }

        while (nodes.size() > 1) {
            // 排序
            Collections.sort(nodes);

            System.out.println("nodes " + nodes);

            // 1. 取出权值最小的二叉树
            Node leftNode = nodes.get(0);
            // 2. 第二小
            Node rightNode = nodes.get(1);

            // 3. 构建新二叉树
            Node parent = new Node(leftNode.value + rightNode.value);
            parent.left = leftNode;
            parent.right = rightNode;

            // 4. 从ArrayList 删除处理过的二叉树
            nodes.remove(leftNode);
            nodes.remove(rightNode);

            // 5. 将parent 加入ArrayList
            nodes.add(parent);

        }

        // 返回霍夫曼树的 root 节点
        return nodes.get(0);

    }
}

// 创建节点
class Node implements Comparable<Node> {
    int value;  // 节点权值
    Node left;  // 左子节点
    Node right; // 右子节点

    public Node(int value) {
        this.value = value;
    }

    // 前序遍历
    public void preOrder() {
        System.out.println(this);

        if (this.left != null) {
            this.left.preOrder();
        }

        if (this.right != null) {
            this.right.preOrder();
        }
    }

    @Override
    public String toString() {
        return "Node{" +
                "value=" + value +
                '}';
    }

    @Override
    public int compareTo(Node o) {
        // 从小到大
        return this.value - o.value;
    }
}
