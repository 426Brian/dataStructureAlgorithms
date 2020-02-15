package dataStructure.linkedList;

import javax.print.DocFlavor;
import java.util.HashMap;
import java.util.Stack;


public class SingleLinkedListDemo {
    public static void main(String[] args) {
        HeroNode hero1 = new HeroNode(1, "宋江", "及时雨");
        HeroNode hero2 = new HeroNode(2, "卢俊义", "玉麒麟");
        HeroNode hero4 = new HeroNode(4, "林冲", "豹子头");
        HeroNode hero3 = new HeroNode(3, "吴用", "智多星");
        SingleLinkedList singleLinkedList = new SingleLinkedList();

       /* singleLinkedList.add(hero1);
        singleLinkedList.add(hero2);
        singleLinkedList.add(hero3);
        singleLinkedList.add(hero4);*/
        singleLinkedList.addbyOrder(hero1);
        singleLinkedList.addbyOrder(hero4);
        singleLinkedList.addbyOrder(hero2);
        singleLinkedList.addbyOrder(hero3);

//        singleLinkedList.update(new HeroNode(4, "吴用", "智多星1"));
//        singleLinkedList.delNode(new HeroNode(1, "吴用", "智多星1"));
        singleLinkedList.showList();

        System.out.println("链表长度 == " + singleLinkedList.getLength());
        System.out.println(singleLinkedList.get(2));

        System.out.println("反转前 ==== ");
        singleLinkedList.showList();
        singleLinkedList.reverse2();
        System.out.println("反转后 ==== ");
        singleLinkedList.showList();

        System.out.println("逆序打印 == ");
        singleLinkedList.reversePrint();
    }

}

// 管理节点
class SingleLinkedList {
    // 初始化头节点， 头节点不动, 不存放任何数据
    private HeroNode head = new HeroNode(0, "", "");

    // 拿到头节点
    public HeroNode getHead() {
        return head;
    }

    // 链表为空
    public boolean isEmpty() {
        return head.next == null;
    }

    // 添加节点
    public void add(HeroNode heroNode) {
        // 头节点不能动，所有辅助节点利用头节点
        HeroNode tmp = head;

        // 找到最后一个节点
        while (tmp.next != null) {
            tmp = tmp.next;
        }

        tmp.next = heroNode;
    }

    // 按照编号顺序添加节点
    public void addbyOrder(HeroNode heroNode) {
        // 当前节点，循环时用到的辅助节点
        HeroNode curNode = head;

        // 添加标签
        boolean flag = false;

        while (curNode.next != null) {
            if (curNode.next.no > heroNode.no) {
                // 找到节点
                heroNode.next = curNode.next;
                curNode.next = heroNode;
                flag = true;
                break;
            } else if (curNode.next.no == heroNode.no) {
                System.out.println("此编号已经存在 " + heroNode.no);
                break;
            }
            curNode = curNode.next;
        }

        // 添加到最后
        if (!flag) {
            curNode.next = heroNode;
        }


    }

    // 修改节点信息
    public void update(HeroNode newHeroNode) {
        if (isEmpty()) {
            System.out.println("链表为空 === ");
            return;
        }

        HeroNode tmp = head;

        // 找到标签
        boolean flag = false;

        while (tmp.next != null) {
            if (tmp.no == newHeroNode.no) {
                // 找到
                tmp.nickName = newHeroNode.nickName;
                flag = true;
                break;
            }
            tmp = tmp.next;
        }

        if (!flag) {
            System.out.println("没有找到编号对应信息");
        }

    }

    // 删除节点
    public void delNode(HeroNode heroNode) {
        if (isEmpty()) {
            System.out.println("链表为空 === ");
            return;
        }

        HeroNode curNode = head;
        HeroNode preNode = null;

        // 找到标签
        boolean flag = false;

        while (curNode.next != null) {
            preNode = curNode;
            curNode = curNode.next;
            if (curNode.no == heroNode.no) {
                // 找到
                preNode.next = curNode.next;
                flag = true;
                break;
            }
        }

        if (!flag) {
            System.out.println("没有找到编号对应信息");
        }
    }

    // 显示链表
    public void showList() {
        HeroNode tmp = head;
        if (isEmpty()) {
            System.out.println("链表为空 === ");
            return;
        }

        while (tmp.next != null) {
            tmp = tmp.next;
            System.out.println(tmp);

        }

    }

    // 有效节点（不包含头节点）
    public int getLength() {
        HeroNode tmp = head;
        if (isEmpty()) {
            System.out.println("链表为空 === ");
            return 0;
        }
        int length = 0;

        while (tmp.next != null) {
            length++;
            tmp = tmp.next;

        }

        return length;
    }

    // 倒数第K 个节点
    public HeroNode get(int k) {
        HeroNode tmp = head;
        int count = 0;

        // 省略了校验k 的代码
        if (k <= 0 || k > getLength()) {
            return null;
        }

        if (isEmpty()) {
            System.out.println("链表为空 === ");
            return null;
        }


        while (tmp.next != null) {
            tmp = tmp.next;
            count++;
            if (count == (getLength() - k + 1)) {
                break;
            }

        }

        return tmp;
    }

    // 单链表反转(借助定义新的头节点)
    public void reverse() {

        if (isEmpty() || head.next.next == null) {
            // 链表为空, 或者只有一个节点
            return;
        }

        HeroNode originCur = head.next;
        // 利用另外一个头节点
        HeroNode newNHead = new HeroNode(0, "", "");
        // 辅助节点
        HeroNode nextNode = null;

        // 将原先的每个节点依次摘下来拼接在 tmp 的头节点后
        // 实现反转后再将tmp 头节点 的next 赋给 原先链表的头节点
        while (originCur != null) {
            nextNode = originCur.next;
            originCur.next = newNHead.next;
            newNHead.next = originCur;
            originCur = nextNode;

        }


        head.next = newNHead.next;
    }

    // 单链表反转
    public void reverse2() {
        HeroNode curNode = head.next;
        HeroNode tmpHead = null;
        HeroNode tmpNode = null;

        while (curNode != null) {
            tmpNode = curNode.next;
            curNode.next = tmpHead;
            tmpHead = curNode;
            curNode =  tmpNode;

        }

        head.next = tmpHead;
    }

    // 从尾到头打印节点
    public void reversePrint() {
        if (isEmpty()) {
            // 链表为空
            return;
        }

        // 利用栈的 FILO
        Stack<HeroNode> stack = new Stack();
        HeroNode cur = head;
        while (cur.next != null) {
            cur = cur.next;
            stack.push(cur);
        }

        // 出栈打印
        while (stack.size() > 0) {
            System.out.println(stack.pop());
        }

    }

}

// 定义HeroNode , 每个 HeroNode 对象是一个节点
class HeroNode {
    public int no;
    public String name;
    public String nickName;
    public HeroNode next;

    public HeroNode(int no, String name, String nickName) {
        this.no = no;
        this.name = name;
        this.nickName = nickName;
    }

    @Override
    public String toString() {
        return "HeroNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                ", nickName='" + nickName + '\'' +
                '}';
    }
}
