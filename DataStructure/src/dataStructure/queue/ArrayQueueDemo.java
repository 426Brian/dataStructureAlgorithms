package dataStructure.queue;

import java.util.Scanner;

public class ArrayQueueDemo {
    public static void main(String[] args) {
        ArrayQueue arrayQueue = new ArrayQueue(3);
        char key = ' ';
        Scanner scanner = new Scanner(System.in);
        boolean loop = true;

        while (loop) {
            System.out.println("s(show) 显示队列");
            System.out.println("e(exit) 退出队列");
            System.out.println("a(add)  添加数据到队列");
            System.out.println("g(get)  取出数据");
            System.out.println("h(head)  队列头数据");

            key = scanner.next().charAt(0);
            switch (key) {
                case 's':
                    arrayQueue.showQueue();
                    break;
                case 'a':
                    System.out.println("请输入一个数字");
                    int value = scanner.nextInt();
                    arrayQueue.addData(value);
                    break;
                case 'g':
                    try {
                        int data = arrayQueue.getData();
                        System.out.println("取出的数据 === " + data);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'h':
                    try {
                        int head = arrayQueue.head();
                        System.out.println("头数据 == " + head);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }

                    break;
                case 'e':
                    scanner.close();
                    loop = false;

                    break;
                default:
                    break;
            }

        }
    }
}

// 数组模拟队列
class ArrayQueue {
    private int maxSize;  // 数组最大容量
    private int front; // 队列头
    private int rear; // 队列尾
    private int[] arr; // 存放数据

    // 创建队列
    public ArrayQueue(int maxSize) {
        this.maxSize = maxSize;
        arr = new int[maxSize];
        front = -1; // 指向队列头部，前一个位置
        rear = -1;  // 指向队列尾的具体位置
    }

    // 队列是否满
    public boolean isFull() {
        return rear == maxSize - 1;
    }

    // 队列是否空
    public boolean isEmpty() {
        return rear == front;
    }

    // 添加数据到队列
    public void addData(int data) {
        if (isFull()) {
            System.out.println("队列已满 === ");
            return;
        }

        arr[++rear] = data;
    }

    // 数据出队列
    public int getData() {
        if (isEmpty()) {
            throw new RuntimeException("队列为空 ===");
        }

        return arr[++front];
    }

    // 显示队列所有数据
    public void showQueue() {
        if (isEmpty()) {
            System.out.println("队列为空 ===");
            return;
        }

        // 遍历数组不够严谨, 如果队列中数据被取出过，或者没有放满都有bug
        /*for (int data : arr) {
            System.out.print(data + "\t");
        }*/

        // 从数组中取出有效数据
        for (int i = front; i < rear; ) {
            System.out.print(arr[++i] + "\t");

        }

    }

    // 显示头数据
    public int head() {
        if (isEmpty()) {
            throw new RuntimeException("队列为空 ===");
        }

        return arr[front + 1];
    }
}
