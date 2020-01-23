package avl;

public class AVLTreeDemo {
    public static void main(String[] args) {
        int[] arr = {4, 3, 6, 5, 7, 8};

        // 创建AVLTree
        AVLTree avlTree = new AVLTree();
        // 添加节点
        for (int i = 0; i < arr.length; i++) {
            avlTree.add(new Node(arr[i]));
        }

        // 遍历
        System.out.println("中序遍历");
        avlTree.infixOrder();

        System.out.println("没有平衡处理前 == ");
        System.out.println("树的高度 == " + avlTree.getRoot().height());
        System.out.println("左子树的高度 == " + avlTree.getRoot().leftHeight());
        System.out.println("右子树的高度 == " + avlTree.getRoot().rightHeight());
    }
}


class AVLTree {
    private Node root;

    //添加
    public void add(Node node) {
        if (root == null) {
            root = node;
        } else {
            root.add(node);
        }

    }

    public Node getRoot() {
        return root;
    }

    // 中序遍历
    public void infixOrder() {
        if (root != null) {
            root.infixOrder();
        } else {
            System.out.println("空树");
        }
    }

    // 查找要删除的节点
    public Node search(int value) {
        if (root == null) {
            return null;
        } else {
            return root.search(value);
        }
    }

    // 查找父节点
    public Node searchParent(int value) {
        if (root == null) {
            return null;
        } else {
            return root.searchParent(value);
        }
    }

    // 删除节点
    public void delNode(int value) {
        if (root == null) {
            return;
        } else {
            Node targetNode = search(value);
            if (targetNode == null) {
                return;
            }
            // 只要root 节点
            if (root.left == null && root.right == null) {
                root = null;
                return;
            }

            Node parentNode = searchParent(value);
            // 叶子节点
            if (targetNode.left == null && targetNode.right == null) {
                if (parentNode.left != null && parentNode.left.value == value) {
                    // 被删的是父节点的左子节点
                    parentNode.left = null;
                } else if (parentNode.right != null && parentNode.right.value == value) {
                    // 被删的是父节点的右子节点
                    parentNode.right = null;
                }

            } else if (targetNode.left != null && targetNode.right != null) {
                // 有两颗子树的节点
                int minVal = delRightTreeMin(targetNode.right);
                targetNode.value = minVal;
            } else {
                // 有左子树的节点
                if (targetNode.left != null) {
                    if (parentNode != null) {
                        if (parentNode.left.value == value) {
                            // 父节点的左子节点
                            parentNode.left = targetNode.left;
                        } else {
                            // 父节点的右子节点
                            parentNode.right = targetNode.left;
                        }
                    } else {
                        // 被删的是根节点
                        root = targetNode.left;
                    }

                } else {
                    if (parentNode != null) {
                        // 有右子树树的节点
                        if (parentNode.left.value == value) {
                            // 父节点的左子节点
                            parentNode.left = targetNode.right;
                        } else {
                            // 父节点的右子节点
                            parentNode.right = targetNode.right;
                        }
                    } else {
                        root = targetNode.right;
                    }

                }
            }

        }
    }

    /**
     * @param node 传入的节点
     * @return
     */
    public int delRightTreeMin(Node node) {
        Node target = node;
        // 查找左子节点，找到最小值
        while (target.left != null) {
            target = target.left;
        }

        // 删除最小节点
        delNode(target.value);

        return target.value;
    }
}


// 节点
class Node {
    int value;
    Node left;
    Node right;

    public Node(int value) {
        this.value = value;
    }

    // 树的高度
    public int height() {
        return Math.max(left == null ? 0 : left.height(), right == null ? 0 : right.height()) + 1;
    }

    public int leftHeight() {
        if (left == null) {
            return 0;
        }
        return left.height();
    }

    public int rightHeight() {
        if (right == null) {
            return 0;
        }
        return right.height();
    }

    // 左旋转
    public void leftRotate() {
        // 创建新节点， 以当前根节点的值
        Node newNode = new Node(value);

        // 新节点左子树设置成当前节点的左子树
        newNode.left = left;

        // 新节点右子树设置成当前节点的右子树的左子树
        newNode.right = right.left;

        // 当前节点的值替换成右子节点的值
        value = right.value;

        // 当前节点的右子树设置成当前节点右子树的右子树
        right = right.right;

        // 当前节点的左子树设置成新的节点
        left = newNode;

    }

    // 查找要删除的节点
    public Node search(int value) {
        if (value == this.value) {
            // 找到
            return this;
        } else if (value < this.value) {
            // 向左子树查找
            if (left == null) {
                return null;

            }
            return this.left.search(value);

        } else {
            // 向右子树查找
            if (this.right == null) {
                return null;
            }
            return this.right.search(value);
        }
    }

    // 要删除的节点的父节点
    public Node searchParent(int value) {
        if ((this.left != null && this.left.value == value) ||
                (this.right != null && this.right.value == value)) {
            return this;
        } else {
            // 小于当前值，当前左子节点不为空
            if (value < this.value && this.left != null) {
                return this.left.search(value);
            } else if (value >= this.value && this.right != null) {
                return this.right.search(value);
            } else {
                // 没有找到父节点
                return null;
            }
        }
    }


    // 递归添加节点
    public void add(Node node) {
        if (node == null) {
            return;
        }

        // 传入节点值和当前节点值关系
        if (node.value < this.value) {
            if (this.left == null) {
                this.left = node;
            } else {
                this.left.add(node);
            }

        } else {
            if (this.right == null) {
                this.right = node;
            } else {
                this.right.add(node);
            }
        }

        if(rightHeight() - leftHeight() > 1){
            if(right !=null && right.rightHeight()< right.leftHeight()){

            }
        }
    }

    // 中序遍历
    public void infixOrder() {

        if (this.left != null) {
            this.left.infixOrder();
        }

        System.out.println(this);


        if (this.right != null) {
            this.right.infixOrder();
        }
    }

    @Override
    public String toString() {
        return "Node{" +
                "value=" + value +
                '}';
    }


}