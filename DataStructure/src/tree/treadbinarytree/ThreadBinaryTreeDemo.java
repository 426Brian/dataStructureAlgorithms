package tree.treadbinarytree;

public class ThreadBinaryTreeDemo {
    public static void main(String[] args) {
        int[] arr = {8, 3, 10, 1, 14, 6};

        HeroNode root = new HeroNode(1, "tom");
        HeroNode node2 = new HeroNode(3, "jack");
        HeroNode node3 = new HeroNode(6, "smith");
        HeroNode node4 = new HeroNode(8, "mary");
        HeroNode node5 = new HeroNode(10, "king");
        HeroNode node6 = new HeroNode(14, "dim");

        root.setLeft(node2);
        root.setRight(node3);
        node2.setLeft(node4);
        node2.setRight(node5);
        node3.setLeft(node6);

        ThreadBinaryTree threadBinaryTree = new ThreadBinaryTree();
        threadBinaryTree.setRoot(root);

        // 测试中序线索化
        threadBinaryTree.threadNodes(root);
        HeroNode leftNode = node5.getLeft();
        HeroNode rightNode = node5.getRight();
        System.out.println("10号节点的前驱节点是 " + leftNode);
        System.out.println("10号节点的后继节点是 " + rightNode);

        // 当线索化二叉树后不能再使用原先的遍历方式
        threadBinaryTree.threadList();
    }
}


// 二叉树
class ThreadBinaryTree {

    private tree.treadbinarytree.HeroNode root;
    // 为了实现线索化，需要创建指向当前节点的前驱节点指针
    private HeroNode pre;

    public void setRoot(tree.treadbinarytree.HeroNode root) {
        this.root = root;
    }


    // 遍历线索化二叉树方法 中序遍历
    public void threadList() {
        HeroNode node = root;
        while (node != null) {
            // 找到 leftType == 1 的节点
            while (node.getLeftType() == 0) {
                node = node.getLeft();
            }

            // 输出当前节点
            System.out.println(node);

            // 如果当前节点的有指针指向的是后继节点就一直输出
            while (node.getRightType() == 1) {
                node = node.getRight();
                System.out.println(node);
            }

            // 替换遍历节点
            node = node.getRight();

        }
    }

    /**
     * 对二叉树进行中序线索化的方法
     *
     * @param node
     */
    public void threadNodes(HeroNode node) {
        if (node == null) {
            return;
        }

        // 1. 先线索化左子树
        threadNodes(node.getLeft());

        // 2. 线索化当前节点
        // 处理当前节点的前驱节点
        if (node.getLeft() == null) {
            // 当前节点左指针，指向前驱节点
            node.setLeft(pre);

            // 修改当前节点的左指针类型
            node.setLeftType(1);
        }

        // 处理当前节点的后继节点
        if (pre != null && pre.getRight() == null) {
            pre.setRight(node);
            pre.setRightType(1);
        }

        // !!! 每处理一个节点后， 让当前节点是下一个节点的前驱节点
        pre = node;

        // 3. 线索化右子树
        threadNodes(node.getRight());


    }

    // 前序遍历
    public void preOrder() {
        if (this.root != null) {
            this.root.preOrder();
        } else {
            System.out.println("二叉树为空无法遍历");
        }
    }

    // 中序遍历
    public void infixOrder() {
        if (this.root != null) {
            this.root.infixOrder();
        } else {
            System.out.println("二叉树为空无法遍历");
        }
    }

    // 后续遍历
    public void postOrder() {
        if (this.root != null) {
            this.root.postOrder();
        } else {
            System.out.println("二叉树为空无法遍历");
        }
    }


    // 前序遍历查找
    public HeroNode preOrderSearch(int no) {
        if (this.root != null) {
            return this.root.preOrderSearch(no);
        }

        return null;
    }


    // 中序遍历查找
    public HeroNode infixOrderSearch(int no) {
        if (this.root != null) {
            return this.root.infixOrderSearch(no);
        }
        return null;
    }

    // 后续遍历查找
    public HeroNode postOrderSearch(int no) {
        if (this.root != null) {
            return this.root.postOrderSearch(no);
        }
        return null;
    }

    // 删除节点
    public void deleteNode(int no) {
        if (root != null) {
            // 如果只有一个 root 节点， 立即判断 root 是否是待删除的节点
            if (root.getNo() == no) {
                root = null;
            } else {
                root.deleteNode(no);
            }
        } else {
            System.out.println("空树，不能删除");
        }

    }
}

// 节点类
class HeroNode {
    private int no;
    private String name;
    private HeroNode left;
    private HeroNode right;

    // leftType = 0 指向左子树, leftType = 1 指向前驱节点
    private int leftType;

    // rightType = 0 指向右子树, rightType = 1 指向后继节点
    private int rightType;

    public HeroNode(int no, String name) {
        this.no = no;
        this.name = name;
    }

    public int getLeftType() {
        return leftType;
    }

    public void setLeftType(int leftType) {
        this.leftType = leftType;
    }

    public int getRightType() {
        return rightType;
    }

    public void setRightType(int rightType) {
        this.rightType = rightType;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public HeroNode getLeft() {
        return left;
    }

    public void setLeft(HeroNode left) {
        this.left = left;
    }

    public HeroNode getRight() {
        return right;
    }

    public void setRight(HeroNode right) {
        this.right = right;
    }

    @Override
    public String toString() {
        return "HeroNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                '}';
    }

    // 前序遍历
    public void preOrder() {
        // 先输出父节点
        System.out.println(this);

        // 递归向左子树
        if (this.left != null) {
            this.left.preOrder();
        }

        // 递归向右子树
        if (this.right != null) {
            this.right.preOrder();
        }
    }

    // 中序遍历
    public void infixOrder() {
        // 递归向左子树
        if (this.left != null) {
            this.left.infixOrder();
        }
        // 输出父节点
        System.out.println(this);

        // 递归向右子树
        if (this.right != null) {
            this.right.infixOrder();
        }
    }

    // 后续遍历
    public void postOrder() {
        // 递归向左子树
        if (this.left != null) {
            this.left.postOrder();
        }
        // 递归向右子树
        if (this.right != null) {
            this.right.postOrder();
        }
        // 输出父节点
        System.out.println(this);
    }

    // 前序遍历查找
    public HeroNode preOrderSearch(int no) {
        // 比较当前节点
        if (this.no == no) {
            return this;
        }

        HeroNode resNode = null;
        if (this.left != null) {
            resNode = this.left.preOrderSearch(no);
        }

        if (resNode != null) {
            return resNode;
        } else if (this.right != null) {
            resNode = this.right.preOrderSearch(no);
        }

        return resNode;
    }

    // 中序遍历查找
    public HeroNode infixOrderSearch(int no) {

        HeroNode resNode = null;
        if (this.left != null) {
            resNode = this.left.infixOrderSearch(no);
        }

        if (resNode != null) {
            return resNode;
        }

        // 比较当前节点
        if (this.no == no) {
            return this;
        }

        if (this.right != null) {
            resNode = this.right.infixOrderSearch(no);
        }

        return resNode;
    }

    // 后序遍历查找
    public HeroNode postOrderSearch(int no) {

        HeroNode resNode = null;

        // 左子树
        if (this.left != null) {
            resNode = this.left.postOrderSearch(no);
        }

        if (resNode != null) {
            return resNode;
        }

        // 右子树
        if (this.right != null) {
            resNode = this.right.postOrderSearch(no);
        }

        if (resNode != null) {
            return resNode;
        }

        // 比较当前节点
        if (this.no == no) {
            return this;
        }

        return resNode;
    }

    // 递归删除节点
    public void deleteNode(int no) {

        if (this.left != null && this.left.no == no) {
            this.left = null;
            return;
        }

        if (this.right != null && this.right.no == no) {
            this.right = null;
            return;
        }

        // 向左递归
        if (this.left != null) {
            this.left.deleteNode(no);
        }

        // 向右递归
        if (this.right != null) {
            this.right.deleteNode(no);
        }
    }
}
