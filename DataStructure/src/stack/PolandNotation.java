package stack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

public class PolandNotation {
    public static void main(String[] args) {
        // 先定义给定逆波兰表达式
        // (3+4)x5-6  => 3  4 + 5 * 6 -
        String suffixExpression = "3 4 + 5 * 6 -";

        // 1. 将 "3 4 + 5 * 6 -" => 放到 ArrayList 中
        // 2. 把 ArrayList 传递到方法中计算

        List<String> listStr = getListStr(suffixExpression);

        System.out.println("listStr == " + listStr);

        System.out.println("计算结果 "+calculate(listStr));
    }

    public static List<String> getListStr(String str) {
        String[] split = str.split(" ");
        List<String> list = new ArrayList<>();
        for (String ele : split) {
            list.add(ele);
        }

        return list;
    }

    // 对逆波兰表达式计算
    public static int calculate(List<String> list) {
        Stack<String> stack = new Stack<String>();
        for (String item : list) {
            // 正则表达式
            if (item.matches("\\d+")) {
                // 多位数直接入栈
                stack.push(item);
            } else {
                // pop 出两个数计算后入栈
                int num1 = Integer.parseInt(stack.pop());
                int num2 = Integer.parseInt(stack.pop());
                int res = 0;
                // 计算
                res =cal(num1, num2, item.charAt(0));

                stack.push("" + res);
            }
        }

        return Integer.parseInt(stack.pop());
    }

    // 计算方法
    public static int cal(int num1, int num2, int oper) {
        int res = 0;
        switch (oper) {
            case '+':
                res = num1 + num2;
                break;

            case '-':
                res = num2 - num1;
                break;


            case '*':
                res = num1 * num2;
                break;

            case '/':
                res = num2 / num1;
                break;

            default:
                break;
        }

        return res;
    }
}
