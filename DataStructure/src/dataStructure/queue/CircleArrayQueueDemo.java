package dataStructure.queue;

import java.util.Scanner;

public class CircleArrayQueueDemo {
    public static void main(String[] args) {
        CircleQueue circleQueue = new CircleQueue(3);
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
                    circleQueue.showQueue();
                    break;
                case 'a':
                    System.out.println("请输入一个数字");
                    int value = scanner.nextInt();
                    circleQueue.addData(value);
                    break;
                case 'g':
                    try {
                        int data = circleQueue.getData();
                        System.out.println("取出的数据 === " + data);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'h':
                    try {
                        int head = circleQueue.head();
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

class CircleQueue {
    private int maxSize;  // 数组最大容量
    private int front; // 队列头 指向队列头部
    private int rear; // 队列尾 指向队列尾的后一个位置
    private int[] arr; // 存放数据

    // 创建队列
    public CircleQueue(int maxSize) {
        this.maxSize = maxSize;
        arr = new int[maxSize];
    }

    // 队列是否满
    public boolean isFull() {
        return (rear + 1) % maxSize == front;
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

        arr[rear] = data;
        rear = (rear + 1) % maxSize;
    }

    // 数据出队列
    public int getData() {
        if (isEmpty()) {
            throw new RuntimeException("队列为空 ===");
        }

        int value = arr[front];
        front = (front + 1) % maxSize;
        return value;
    }

    // 显示队列所有数据
    public void showQueue() {
        if (isEmpty()) {
            System.out.println("队列为空 ===");
            return;
        }

        for (int i = front; i < front + this.size(); i++) {
            System.out.print(arr[i % maxSize] + "\t");
        }
    }

    // 有效数据个数
    public int size() {
        return (maxSize - front + rear) % maxSize;
    }

    // 显示头数据
    public int head() {
        if (isEmpty()) {
            throw new RuntimeException("队列为空 ===");
        }

        return arr[front];
    }
}