package dataStructure.linkedList;

public class DoubleLinkedListDemo {

}

class DoubleLinkedList {
    private HeroNode2 head = new HeroNode2(0, "", "");

    // 拿到头节点
    public HeroNode2 getHead() {
        return head;
    }

    // 链表为空
    public boolean isEmpty() {
        return head.next == null;
    }


    // 显示链表
    public void showList() {
        HeroNode2 tmp = head;
        if (isEmpty()) {
            System.out.println("链表为空 === ");
            return;
        }

        while (tmp.next != null) {
            tmp = tmp.next;
            System.out.println(tmp);

        }

    }

    // 添加节点到双向链表
    public void add(HeroNode2 heroNode) {
        HeroNode2 tmp = head;

        while (tmp.next != null) {
            tmp = tmp.next;
        }

        tmp.next = heroNode;
        heroNode.pre = tmp;

    }

    // 修改节点
    public void update(HeroNode2 newHeroNode) {
        if (isEmpty()) {
            System.out.println("链表为空");
            return;
        }
        // 找到要修改的节点, 根据no 编号
        // 定义辅助变量
        HeroNode2 tmp = head;
        boolean flag = false;

        while (tmp.next != null) {
            tmp = tmp.next;
            if (tmp.no == newHeroNode.no) {
                tmp.name = newHeroNode.name;
                tmp.nickName = newHeroNode.nickName;
                flag = true;
                break;
            }

        }

        if (!flag) {
            System.out.printf("没有找到编号%d 的节点, 不能修改\n", newHeroNode.no);

        }
    }

    // 删除节点
    public void delNode(HeroNode2 heroNode) {
        if (isEmpty()) {
            System.out.println("链表为空 === ");
            return;
        }

        HeroNode2 tmp = head;
        HeroNode2 curNode = null;

        // 找到标签
        boolean flag = false;

        while (tmp.next != null) {
            curNode = tmp;
            tmp = tmp.next;
            if (tmp.no == heroNode.no) {
                // 找到
                curNode.next = tmp.next;
                if (tmp.next != null) {
                    // 不是最后一个节点 ***
                    tmp.next.pre = curNode;
                }

                flag = true;
                break;
            }
        }

        if (!flag) {
            System.out.println("没有找到编号对应信息");
        }
    }

}

// 定义HeroNode , 每个 HeroNode 对象是一个节点
class HeroNode2 {
    public int no;
    public String name;
    public String nickName;
    public HeroNode2 next;
    public HeroNode2 pre;

    public HeroNode2(int no, String name, String nickName) {
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
