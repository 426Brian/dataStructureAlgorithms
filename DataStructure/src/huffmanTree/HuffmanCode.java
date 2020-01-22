package huffmanTree;

import java.nio.ByteBuffer;
import java.util.*;

public class HuffmanCode {
    // 存放霍夫曼编码
    static Map<Byte, String> huffmanCodes = new HashMap<>();

    // 声称霍夫曼编码表示，拼接路径
    static StringBuilder stringBuilder = new StringBuilder();

    public static void main(String[] args) {
        String content = "i like like like java do you like a java";
        byte[] huffmanBytes = huffmanZip(content);

        byte[] decode = decode(huffmanCodes, huffmanBytes);

        System.out.println(new String(decode));
    }


    // 霍夫曼编码

    /**
     * 霍夫曼编码
     *
     * @param content 原始字符串
     * @return 经过霍夫曼处理后的字节数组（压缩后的数组）
     */
    public static byte[] huffmanZip(String content) {

        byte[] contentBytes = content.getBytes();
        System.out.println("contentBytes.length == " + contentBytes.length);

        // 字节数组对应转成树的节点（用list 保存）
        List<Node2> nodes = getNodes(contentBytes);
        System.out.println("nodes == " + nodes);

        // 利用list 保存的树的节点生成霍夫曼树
        Node2 huffmanRoot = createHuffmanTree(nodes);

        // 前序遍历霍夫曼树
        preOrder(huffmanRoot);

        // 利用霍夫曼树得到霍夫曼编码
        getCode(huffmanRoot, "", stringBuilder);
        System.out.println("生成的霍夫曼编码表 ====== " + huffmanCodes);

        // 将霍夫曼编码压缩成字节数组
        byte[] huffmanBytes = zip(contentBytes, huffmanCodes);
        System.out.println("霍夫曼编码长度 == " + huffmanBytes.length + " 霍夫曼编码 ====== " + Arrays.toString(huffmanBytes));
        return huffmanBytes;
    }

    // 霍夫曼解码

    /**
     * @param huffmanCodes 霍夫曼编码表 map
     * @param huffmanBytes 霍夫曼编码得到的字节数组
     * @return 原来字符串对应的数组
     */
    public static byte[] decode(Map<Byte, String> huffmanCodes, byte[] huffmanBytes) {
        // 1. huffmanBytes 对应二进制字符串
        StringBuilder stringBuilder = new StringBuilder();

        // 2. byte[] 转成二进制字符串
        for (int i = 0; i < huffmanBytes.length; i++) {
            boolean flag = (i == huffmanBytes.length - 1);
            byte b = huffmanBytes[i];
            stringBuilder.append(byteToString(!flag, b));
        }

        System.out.println("霍夫曼编码 == " + stringBuilder.toString());

        // 字符串按照霍夫曼编码进行解码
        HashMap<String, Byte> map = new HashMap<String, Byte>();
        for (Map.Entry<Byte, String> entry : huffmanCodes.entrySet()) {
            // 反向添加
            map.put(entry.getValue(), entry.getKey());
        }
        ArrayList<Byte> list = new ArrayList<>();
        for (int i = 0; i < stringBuilder.length(); ) {
            int count = 1;
            boolean flag = true;
            Byte b = null;
            while (flag) {
                // 逐个拼接字符去匹配
                String key = stringBuilder.substring(i, i + count);
                b = map.get(key);
                if (b == null) {
                    // 没有匹配到
                    count++;
                } else {
                    flag = false;
                }
            }
            list.add(b);
            i += count;
        }

        byte[] bytes = new byte[list.size()];
        for (int i = 0; i < bytes.length; i++) {
            bytes[i] = list.get(i);
        }

        return bytes;
    }


    public static String byteToString(boolean flag, byte b) {
        // 使用变量保存 b
        int tmp = b;
        if (flag) {
            tmp |= 256; // 按位与 256  1 0000 0000 | 0000 0001 =>  1 0000 0001
        }
        String str = Integer.toBinaryString(tmp);
        if (flag) {
            return str.substring(str.length() - 8);
        } else {
            return str;
        }

    }

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
    public static byte[] zip(byte[] bytes, Map<Byte, String> huffmanCodes) {
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
        // len = (stringBuilder.length()+7)/8; (一行代码代替77 行到 81 行代码)

        // 压缩后的byte[]
        byte[] by = new byte[len];
        int index = 0;

        for (int i = 0; i < stringBuilder.length(); i += 8) {
            String str;
            if (i + 8 > stringBuilder.length()) {
                // 不够8 位
                str = stringBuilder.substring(i);
            } else {
                str = stringBuilder.substring(i, i + 8);
            }

            // str 转成byte[]
            by[index] = (byte) Integer.parseInt(str, 2); // radix: 2 表示二进制
            index++;
        }

        return by;

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
