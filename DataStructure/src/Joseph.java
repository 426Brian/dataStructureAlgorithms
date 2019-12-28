public class Joseph {
    public static void main(String[] args) {
        CircleSingleLinkedList circleSingleLinkedList = new CircleSingleLinkedList();
        circleSingleLinkedList.addBoy(5);

        circleSingleLinkedList.showBoys();

        circleSingleLinkedList.countBoy(1, 2, 5);
    }

}

class CircleSingleLinkedList {
    // 创建一个first 节点，当前没有编号
    private Boy first;

    // 给单向环形链表添加节点
    public void addBoy(int nums) {
        if (nums < 1) {
            System.out.println("nums 值不正确");
            return;
        }

        Boy curBoy = null; // 辅助指针
        // for 循环创建环形链表
        for (int i = 1; i <= nums; i++) {
            // 根据编号创建 Boy 节点
            Boy boy = new Boy(i);

            // 第一个 Boy
            if (i == 1) {
                first = boy;
                first.setNext(first);
                curBoy = first;
            } else {
                curBoy.setNext(boy);
                boy.setNext(first);
                curBoy = boy;
            }
        }
    }

    // 遍历单向环形链表
    public void showBoys() {
        // 判断是否为空
        if (first == null) {
            System.out.println("没有任何节点");
            return;
        }

        // 因为first 不能动，因此使用辅助指针遍历
        Boy curBoy = first;
        while (true) {
            System.out.printf("小孩的编号%d\n", curBoy.getNo());

            // 遍历结束
            if (curBoy.getNext() == first) {
                break;
            }
            curBoy = curBoy.getNext();
        }
    }


    /**
     * 根据用户输入，计算出小孩出圈的顺序
     *
     * @param startNo  表示从第几个小孩开始数
     * @param countNum 表示数几下
     * @param nums     表示最初有多少个小孩在圈中
     */
    public void countBoy(int startNo, int countNum, int nums) {
        // 校验数据
        if (first == null || startNo < 1 || startNo > nums) {
            System.out.println("参数输入有误");
            return;
        }

        // 创建辅助指针，帮助完成小孩出圈
        Boy helper = first;

        // 将helper 移动到最后节点
        while(true){
            if(helper.getNext() == first){
                // helper 指向最后的节点
                break;
            }
            helper = helper.getNext();
        }

        // first 定位到开始数的小孩
        for(int i = 0; i < startNo -1; i++){
            first = first.getNext();
            helper = helper.getNext();
        }

        // 小孩报数, 并移出圈
        while(true){
            if(helper == first){
                // 圈中只有个一个节点
                break;
            }

            // 让 first 和 helper 移动 countNum-1
            for(int i = 0; i < countNum -1; i++){
                first = first.getNext();
                helper = helper.getNext();
            }

            System.out.println("出圈的小孩编号 === "+first.getNo());
            // 出圈
            first = first.getNext();
            helper.setNext(first);
        }

        System.out.println("最后留在圈中的小孩编号："+helper.getNo());

    }
}

// Boy 类，表示一个节点
class Boy {
    private int no;
    private Boy next;

    public Boy(int no) {
        this.no = no;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public Boy getNext() {

        return next;
    }

    public void setNext(Boy next) {
        this.next = next;
    }
}
