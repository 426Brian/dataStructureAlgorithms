package hashTable;

import java.util.Scanner;

public class HashTableDemo {
    public static void main(String[] args) {
        HashTable hashTable = new HashTable(7);

        String key = "";
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("add:  添加雇员");
            System.out.println("list: 显示雇员");
            System.out.println("exit: 退出");

            key = scanner.next();
            switch (key) {
                case "add":
                    System.out.println("输入id");
                    int id = scanner.nextInt();
                    System.out.println("输入名字");
                    String name = scanner.next();
                    Emp emp = new Emp(id, name);
                    hashTable.add(emp);

                    break;
                case "list":
                    hashTable.list();
                    break;
                case "exit":
                    scanner.close();
                    System.exit(0);
                default:
                    break;
            }
        }
    }
}

// 创建 HashTable 管理多条链表
class HashTable {
    private EmpLinkedList[] empLinkedListArray;
    private int size; // 表示共有多少条链表

    // 构造器
    public HashTable(int size) {
        this.size = size;
        empLinkedListArray = new EmpLinkedList[size];
        for (int i = 0; i < size; i++) {
            empLinkedListArray[i] = new EmpLinkedList();
        }
    }

    // 添加雇员
    public void add(Emp emp) {
        // 根据员工id, 决定添加到哪条链表
        int empLinkedListNo = hashFun(emp.id);

        // 添加到链表
        empLinkedListArray[empLinkedListNo].addEmp(emp);
    }

    // 编写散列函数， 使用一个简单取模法
    public int hashFun(int id) {
        return id % size;
    }

    // 遍历所有链表
    public void list() {
        for (int i = 0; i < size; i++) {
            empLinkedListArray[i].list(i);

        }
    }

}

// 创建链表
class EmpLinkedList {
    // 头指针，直接指向第一个Emp
    private Emp head;

    public boolean isEmpty(){
        if(head == null){
            return true;
        }
        return false;
    }
    public void addEmp(Emp emp) {
        if (head == null) {
            head = emp;
            return;
        }

        // 如果不是第一个，使用辅助指针，帮助定位到最后
        Emp curEmp = head;
        while (true) {
            if (curEmp.next == null) {
                break;
            }
            curEmp = curEmp.next;
        }

        // 退出时直接将 emp 加入链表
        curEmp.next = emp;
    }

    // 遍历链表
    public void list(int i) {
        if (head == null) {
            // 链表为空
            System.out.println("第"+i+"条链表为空");
            return;
        }
        System.out.println("第"+i+"条链表信息为： ");
        Emp curEmp = head;  // 辅助指针

        while (true) {
            System.out.printf("=> id=%d name=%s\t", curEmp.id, curEmp.name);
            if (curEmp.next == null) {
                break;
            }
            curEmp = curEmp.next;
        }

        System.out.println();
    }

}

// 创建雇员类， 表示链表中的节点
class Emp {
    public int id;
    public String name;
    public Emp next;

    public Emp(int id, String name) {
        this.id = id;
        this.name = name;
    }
}
