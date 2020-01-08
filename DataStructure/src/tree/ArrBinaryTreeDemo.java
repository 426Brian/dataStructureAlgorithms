package tree;

public class ArrBinaryTreeDemo {
    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5, 6, 7};

        ArrBinaryTree arrBinaryTree = new ArrBinaryTree(arr);
        arrBinaryTree.preOrder();
    }
}


// 编写一个ArrayBinaryTree, 实现顺序存储二叉树

class ArrBinaryTree {
    // 存储数据的数组
    private int[] arr;

    public ArrBinaryTree(int[] arr) {
        this.arr = arr;
    }

    public void preOrder() {
        preOrder(0);
    }

    /**
     * 顺序存储二叉树前序遍历
     *
     * @param index 数组下标
     */
    public void preOrder(int index) {
        if (arr == null || arr.length == 0) {
            System.out.println("数组为空， 不能按照二叉树遍历");
        }

        // 输出当前元素
        System.out.println(arr[index]);

        // 向左递归遍历
        // 2*n+1
        if (2 * index + 1 < arr.length) {
            preOrder(2 * index + 1);
        }

        // 向右递归遍历
        // 2*n+2
        if (2 * index + 2 < arr.length) {
            preOrder(2 * index + 2);
        }
    }
}