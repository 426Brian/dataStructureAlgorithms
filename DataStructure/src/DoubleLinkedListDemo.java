import java.awt.*;

public class DoubleLinkedListDemo {
    public static void main(String[] args) {
        System.out.println("双向链表的测试 === ");
        HeroNode2 hero1 = new HeroNode2(1, "宋江", "及时雨");
        HeroNode2 hero2 = new HeroNode2(2, "卢俊义", "玉麒麟");
        HeroNode2 hero4 = new HeroNode2(4, "林冲", "豹子头");
        HeroNode2 hero3 = new HeroNode2(3, "吴用", "智多星");

        DoubleLinkedList doubleLinkedList = new DoubleLinkedList();

        doubleLinkedList.add(hero1);
        doubleLinkedList.add(hero2);
        doubleLinkedList.add(hero3);
        doubleLinkedList.add(hero4);
        doubleLinkedList.display();

        HeroNode2 hero5 = new HeroNode2(3, "公孙胜", "入云龙");
        doubleLinkedList.update(hero5);
        System.out.println("更改后 ====== ");
        doubleLinkedList.display();

        doubleLinkedList.deleteNode(3);
        System.out.println("删除后 ====== ");
        doubleLinkedList.display();
    }
}

class DoubleLinkedList{
    private HeroNode2 head = new HeroNode2(0, "","");


    // 返回头节点
    public HeroNode2 getHead() {
        return head;
    }

    // 显示链表[遍历]
    public void display() {
        if (head.next == null) {
            System.out.println("链表为空 ==============");
            return;
        }

        // 因为头节点不能动，因此需要辅助变量来遍历
        HeroNode2 tmp = head.next;
        while (true) {
            if (tmp == null) {
                break;
            }

            //输出节点信息
            System.out.println(tmp.toString());
            tmp = tmp.next;
        }
    }

    // 添加节点到双向链表
    public void add(HeroNode2 heroNode2) {
        HeroNode2 tmp = head;
        while (true) {
            if (tmp.next == null) {
                break;
            }

            // 如果没有找到最后，将tmp 后移
            tmp = tmp.next;
        }

        // 双向链表
        tmp.next = heroNode2;
        heroNode2.pre = tmp;

    }

    // 修改双向链表
    public void update(HeroNode2 newHeroNode2) {
        if (head.next == null) {
            System.out.println("链表为空");
            return;
        }
        // 找到要修改的节点, 根据no 编号
        // 定义辅助变量
        HeroNode2 tmp = head.next;
        boolean flag = false;

        while (true) {
            if (tmp == null) {
                // 遍历完
                break;
            }

            if (tmp.no == newHeroNode2.no) {
                // 找到
                flag = true;
                break;
            }

            tmp = tmp.next;
        }

        if (flag) {
            tmp.name = newHeroNode2.name;
            tmp.nick = newHeroNode2.nick;
        } else {
            System.out.printf("没有找到编号%d 的节点, 不能修改\n", newHeroNode2.no);
        }
    }

    // 删除节点
    public void deleteNode(int no) {
        // 判断是否为空
        if(head.next == null){
            System.out.println("链表为空，无法删除 === ");
            return;
        }


        HeroNode2 tmp = head.next;
        boolean flag = false;

        while (true) {
            if (tmp == null) {
                break;
            }

            if (tmp.no == no) {
                flag = true;
                break;
            }
            tmp = tmp.next;
        }

        if (flag) {
            tmp.pre.next = tmp.next;
            if(tmp.next !=null){
                // 要删除的节点不是最后一个节点
                tmp.next.pre = tmp.pre;
            }

        } else {
            System.out.printf("要删除的节点%d 不存在");
        }

    }

}
class HeroNode2 {
    public int no;
    public String name;
    public String nick;
    public HeroNode2 pre;
    public HeroNode2 next;

    public HeroNode2(int no, String name, String nick) {
        this.no = no;
        this.name = name;
        this.nick = nick;
    }

    @Override
    public String toString() {
        return "HeroNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                ", nick='" + nick + '\'' +
                '}';
    }
}