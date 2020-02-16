public class QueueEight {
    // 不同的摆放方法
    static int count;
    // 判断是否冲突的次数
    static int judgeCount;
    // 皇后个数
    int max = 8;
    // 定义一个数组，保存皇后放置位置的结果
    // array[i]  = val  表示第 i+1 个皇后放在第 i+1 行的第 i+1 列
    int[] array = new int[max];

    public static void main(String[] args) {
        QueueEight queueEight = new QueueEight();
        queueEight.check(0);

        System.out.println("不同的摆放方式有 " + count + "种" + "判断冲突的次数 " + judgeCount);
    }

    // 查看我们放置第 n 个皇后， 就去检测该皇后是否和已经摆放的皇后冲突
    private boolean judge(int n) {
        judgeCount++;
        for (int i = 0; i < n; i++) {

            // 1. array[i] == array[n]  第 n 个皇后跟第 i 个皇后是否在同一列
            // 2. Math.abs(n - i) ==Math.abs(array[n] - array[i] 第 n 个皇后跟第 i 个皇后是否在同一斜线
            if (array[i] == array[n] || Math.abs(n - i) == Math.abs(array[n] - array[i])) {
                return false;
            }
        }
        return true;
    }

    // 放置第 n 个皇后
    private void check(int n) {
        if (n == max) {
            print();
            count++;
            return;
        }

        // 依次放入皇后， 并判断是否冲突
        for (int i = 0; i < max; i++) {
            // 先把当前这个皇后 n， 放在该行的第一列
            array[n] = i;
             if (judge(n)) {
                // 不冲突， 接着放第 n+1 个皇后，开始递归
                check(n + 1);
            }
            // 如果冲突， 就继续执行array[n] = i, 即将第n 个皇后， 放置在本行的后移一个位置
        }

    }

    // 输出皇后摆放位置
    private void print() {
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i] + " ");
        }

        System.out.println();
    }
}
