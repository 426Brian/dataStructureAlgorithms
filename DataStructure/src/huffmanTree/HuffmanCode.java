package huffmanTree;

import java.util.*;

public class HuffmanCode {
    // 存放霍夫曼编码
    static Map<Byte, String> huffmanCodes = new HashMap<>();

    // 声称霍夫曼编码表示，拼接路径
    static StringBuilder stringBuilder = new StringBuilder();

    public static void main(String[] args) {
        String content = "i like like like java do you like a java";
        byte[] contentBytes = content.getBytes();

        System.out.println(contentBytes.length);

        List<Node2> nodes = getNodes(contentBytes);
        System.out.println(nodes);

        Node2 huffmanRoot = createHuffmanTree(nodes);

        preOrder(huffmanRoot);

        System.out.println("霍夫曼编码 ====== ");

        getCode(huffmanRoot, "", stringBuilder);
        System.out.println("霍夫曼编码 ====== " + huffmanCodes);

        zip(contentBytes, huffmanCodes);
    }

    // 霍夫曼编码

    /**
     * 传入的node 节点所有叶子节点的霍夫曼编码得到， 并放入集合中
     *
     * @param node          传入节点
     * @param code          路径： 左子节点是 0 右子节点是 1
     * @param stringBuilder 用于拼接路径
     */
    public static void getCode(Node2 node, String code, StringBuilder stringBuilder) {
        StringBuilder stringBuilder2 = new StringBuilder(stringBuilder);
        stringBuilder2.append(code);

        if (node != null) {
            if (node.data == null) {
                // 非叶子节点
                // 左递归
                getCode(node.left, "0", stringBuilder2);

                // 右递归
                getCode(node.right, "1", stringBuilder2);
            } else {
                // 叶子结点
                huffmanCodes.put(node.data, stringBuilder2.toString());
            }
        }
    }

    /**
     * @param bytes        原始字符串对应的byte[]
     * @param huffmanCodes 生成的霍夫曼编码
     * @return 处理后的byte[]
     * 举例： String content = "i like like like java do you like a java" -> byte[]
     */
    public static void zip(byte[] bytes, Map<Byte, String> huffmanCodes) {
        // 1. 利用 huffmanCodes 将bytes 转成霍夫曼编码对应的字符串
        StringBuilder stringBuilder = new StringBuilder();
        // 遍历
        for (byte b : bytes) {
            stringBuilder.append(huffmanCodes.get(b));
        }
        System.out.println("stringBuilder  == " + stringBuilder.toString());

        int len;
        if (stringBuilder.length() % 8 == 0) {
            len = stringBuilder.length() / 8;
        } else {
            len = stringBuilder.length() / 8 + 1;
        }



    }

    // 前序遍历
    public static void preOrder(Node2 root) {
        if (root != null) {
            root.preOrder();
        } else {
            System.out.println("空树");
        }
    }

    public static List<Node2> getNodes(byte[] bytes) {
        // 1. 创建一个ArrayList
        ArrayList<Node2> nodes = new ArrayList<Node2>();

        // 存储每个byte 出现的次数 -> map

        // 遍历bytes  统计每个byte 出现的次数
        Map<Byte, Integer> counts = new HashMap<>();
        for (byte b : bytes) {
            Integer count = counts.get(b);
            if (count == null) {
                counts.put(b, 1);
            } else {
                counts.put(b, ++count);
            }
        }

        // 把每个键值对转成一个node, 加入到 nodes 集合中
        for (Map.Entry<Byte, Integer> entry : counts.entrySet()) {
            nodes.add(new Node2(entry.getKey(), entry.getValue()));
        }

        return nodes;
    }

    public static Node2 createHuffmanTree(List<Node2> nodes) {
        // 1. 遍历数组
        // 2. 将每一个元素构成一个Node
        // 3. 将Node 放入ArrayList 中


        while (nodes.size() > 1) {
            // 排序
            Collections.sort(nodes);

            System.out.println("nodes " + nodes);

            // 1. 取出权值最小的二叉树
            Node2 leftNode = nodes.get(0);
            // 2. 第二小
            Node2 rightNode = nodes.get(1);

            // 3. 构建新二叉树
            Node2 parent = new Node2(null, leftNode.weight + rightNode.weight);
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

class Node2 implements Comparable<Node2> {
    Byte data;     // 存放数据
    int weight;    // 权值，表示字符出现的次数
    Node2 left;
    Node2 right;

    public Node2(Byte data, int weight) {
        this.data = data;
        this.weight = weight;
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
        return "Node2{" +
                "data=" + data +
                ", weight=" + weight +
                '}';
    }

    @Override
    public int compareTo(Node2 o) {
        return this.weight - o.weight;
    }
}
