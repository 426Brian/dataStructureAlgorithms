package tree;

import java.util.function.BiPredicate;

public class BinaryTreeDemo {
    public static void main(String[] args) {
        // 创建二叉树
        BinaryTree binaryTree = new BinaryTree();

        HeroNode root = new HeroNode(1, "宋江");
        HeroNode hero2 = new HeroNode(2, "卢俊义");
        HeroNode hero3 = new HeroNode(3, "吴用");
        HeroNode hero4 = new HeroNode(4, "林冲");
        HeroNode hero5 = new HeroNode(5, "关胜");

        root.setLeft(hero2);
        root.setRight(hero3);
        hero3.setLeft(hero5);
        hero3.setRight(hero4);
        binaryTree.setRoot(root);

        System.out.println("前序遍历 ==> ");
        binaryTree.preOrder();

        System.out.println("中序遍历 ==> ");
        binaryTree.infixOrder();

        System.out.println("后序遍历 ==> ");
        binaryTree.postOrder();

        HeroNode heroNode = null;
        System.out.println("前序遍历查找 ==> ");
        heroNode = binaryTree.preOrderSearch(5);
        if (heroNode != null) {
            System.out.println(heroNode.toString());
            heroNode = null;

        }

        System.out.println("中序遍历查找 ==> ");
        heroNode = binaryTree.infixOrderSearch(5);
        if (heroNode != null) {
            System.out.println(heroNode.toString());
            heroNode = null;
        }

        System.out.println("后序遍历查找 ==> ");
        heroNode = binaryTree.postOrderSearch(5);
        if (heroNode != null) {
            System.out.println(heroNode.toString());
            heroNode = null;
        }

    }
}

// 二叉树
class BinaryTree {
    private HeroNode root;

    public void setRoot(HeroNode root) {
        this.root = root;
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
    public void deleteNode(int no){
        if(root !=null){
            // 如果只有一个 root 节点， 立即判断 root 是否是待删除的节点
            if(root.getNo() == no){
                root = null;
            }else{
                root.deleteNode(no);
            }
        }else {
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

    public HeroNode(int no, String name) {
        this.no = no;
        this.name = name;
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

        if (this.left != null) {
            resNode = this.left.postOrderSearch(no);
        }
        return resNode;
    }

    // 递归删除节点
    public void deleteNode(int no){

        if(this.left !=null && this.left.no ==no){
            this.left = null;
            return;
        }

        if(this.right !=null && this.right.no ==no){
            this.right = null;
            return;
        }

        if(this.left !=null){
            this.left.deleteNode(no);
        }

        if(this.right !=null){
            this.right.deleteNode(no);
        }
    }
}