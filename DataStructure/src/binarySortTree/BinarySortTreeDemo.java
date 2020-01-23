package binarySortTree;

public class BinarySortTreeDemo {
    public static void main(String[] args) {
        int[] arr = {7, 3, 10, 12, 5, 1, 9};

        BinarySortTree binarySortTree = new BinarySortTree();

        for (int i = 0; i < arr.length; i++) {
            binarySortTree.add(new Node(arr[i]));
        }

        System.out.println("中序遍历");
        binarySortTree.infixOrder();

    }
}


// 二叉排序树

class BinarySortTree {
    private Node root;

    //添加
    public void add(Node node) {
        if (root == null) {
            root = node;
        } else {
            root.add(node);
        }

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
            // 只有root 节点
            if (root.left == null && root.right == null) {
                root = null;
                return;
            }

            Node parentNode = searchParent(value);
            // 被删除节点是叶子节点
            if (targetNode.left == null && targetNode.right == null) {
                if (parentNode.left != null && parentNode.left.value == value) {
                    // 被删的节点是父节点的左子节点
                    parentNode.left = null;
                } else if (parentNode.right != null && parentNode.right.value == value) {
                    // 被删的节点是父节点的右子节点
                    parentNode.right = null;
                }

            } else if (targetNode.left != null && targetNode.right != null) {
                // 有两颗子树的节点, 找到右子树的最小值的节点删除, 并将其值赋给被删除节点
                int minVal = delRightTreeMin(targetNode.right);
                targetNode.value = minVal;
            } else {
                // 被删除的节点是只有左子树的节点
                if (targetNode.left != null) {
                    if (parentNode != null) {
                        if (parentNode.left.value == value) {
                            // 被删除节点是父节点的左子节点
                            parentNode.left = targetNode.left;
                        } else {
                            // 被删除节点是父节点的右子节点
                            parentNode.right = targetNode.left;
                        }
                    } else {
                        // 被删的是根节点
                        root = targetNode.left;
                    }

                } else {
                    // 被删除的节点是只有右子树的节点
                    if (parentNode != null) {
                        if (parentNode.left.value == value) {
                            // 被删除节点是父节点的左子节点
                            parentNode.left = targetNode.right;
                        } else {
                            // 被删除节点是父节点的右子节点
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

    // 查找要删除的节点
    public Node search(int value) {
        if (value == this.value) {
            // 找到
            return this;
        } else if (value < this.value) {
            // 向左递归
            if (this.left == null) {
                return null;
            }

            return this.left.search(value);
        } else {
            // 向右递归
            if (this.right == null) {
                return null;
            }

            return this.right.search(value);
        }
    }

    // 查找要删除节点的父节点
    public Node searchParent(int value) {
        if ((this.left != null && this.left.value == value) ||
                (this.right != null && this.right.value == value)) {
            return this;
        } else {
            if (value < this.value && this.left != null) {
                // 向左递归
                return this.left.searchParent(value);
            } else if (value >= this.value && this.right != null) {
                // 向右递归
                return this.right.searchParent(value);
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