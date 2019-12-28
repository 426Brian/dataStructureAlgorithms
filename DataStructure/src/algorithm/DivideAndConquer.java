package algorithm;

public class DivideAndConquer {
    public static void main(String[] args) {
        Hanoitower.hanoitower(2, 'A', 'B', 'C');
    }
}

class Hanoitower {

    public static void hanoitower(int num, char a, char b, char c) {
        // 如果只有一个盘
        if (num == 1) {
            System.out.println("第1个盘从 A --> C");
        } else {
            // n >=2
            // 1. 先把最上面的所有盘 A -> B
            hanoitower(num - 1, a, c, b);

            // 2. 把最下边的盘 A -> C
            System.out.println("第" + num + "个盘从 " + a + " --> " + c);

            // 3. 把B 塔的所有盘从 B -> C
            hanoitower(num - 1, b, a, c);
        }
    }
}
