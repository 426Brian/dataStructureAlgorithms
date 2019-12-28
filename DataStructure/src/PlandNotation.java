import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class PlandNotation {

    public static void main(String[] args) {
        // 逆波兰表达式
        // (3+4)*5-6 --> 3 4 + 5 * 6 -
        String suffixExpression = "3 4 + 5 * 6 -";

        // 1. 将3 4 + 5 * 6 - 放到ArrayList 中
        // 2. 将ArrayList 传递给一个方法，遍历ArrayList 配合栈完成计算

        List<String> listString = getListString(suffixExpression);
        System.out.println(listString);

        System.out.println("计算结果 == "+calculate(listString));
    }

    public static List<String> getListString(String suffixExpression) {
        String[] split = suffixExpression.split(" ");
        List<String> list = new ArrayList<String>();

        for (String ele : split) {
            list.add(ele);
        }
        return list;
    }

    public static int calculate(List<String> list) {
        Stack stack = new Stack();
        for (String item : list) {
            if (item.matches("\\d+")) {
                // 多位数
                stack.push(item);
            } else {
                // pop 两个数，并运算，再入栈
                int num1 = Integer.parseInt((String) stack.pop());
                int num2 = Integer.parseInt((String) stack.pop());
                int res = 0;

                if (item.equals("+")) {
                    res = num1 + num2;
                } else if (item.equals("-")) {
                    res = num2 - num1;
                } else if (item.equals("*")) {
                    res = num2 * num1;
                } else if (item.equals("/")) {
                    res = num2 / num1;
                } else {
                    throw new RuntimeException("运算符有问题");
                }

                stack.push("" + res);
            }
        }
        return Integer.parseInt((String) stack.pop());
    }
}


