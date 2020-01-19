package stack;

public class Calculator {
    public static void main(String[] args) {
        // 表达式
        String expression = "70+2*6-2";

        // 创建两个栈， 一个数栈， 一个符号栈
        ArrayStack2 numStack = new ArrayStack2(10);
        ArrayStack2 operStack = new ArrayStack2(10);

        // 定义需要的相关变量
        int index = 0;
        int num1 = 0;
        int num2 = 0;
        int oper = 0;
        int res = 0;
        char ch = ' ';

        String keepNum = "";
        // 扫描字符串
        while (true) {
            // 依次得到expression 的每一个字符
            ch = expression.substring(index, index + 1).charAt(0);
            // 判断
            if (operStack.isOper(ch)) {
                // 是运算符
                if (operStack.isEmpty()) {
                    // 栈空则入栈
                    operStack.push(ch);
                } else {
                    if (operStack.priroty(ch) <= operStack.priroty(operStack.peek())){
                        num1 = numStack.pop();
                        num2 = numStack.pop();
                        oper = operStack.pop();
                        res = numStack.cal(num1, num2, oper);
                        numStack.push(res);
                        operStack.push(ch);
                    }else{
                        operStack.push(ch);
                    }

                }
            } else {
                // 当处理多位数时，不能发现是一个数就立即入栈，
                // 需要index 找到符号为止
                keepNum += ch;

                if (index == expression.length() - 1) {
                    // 最后一位
                    numStack.push(Integer.parseInt(keepNum));
                } else if (operStack.isOper(expression.substring(index + 1, index + 2).charAt(0))) {
                    numStack.push(Integer.parseInt(keepNum));
                    keepNum = "";
                }
            }

            index++;

            if (index == expression.length()) {
                break;
            }
        }

        // 表达式扫描完毕， 顺序从数栈和符号栈中pop 出相应的数字和符号，并运行
        while (true) {

            if (operStack.isEmpty()) {
                // 计算到最后，数栈中只有一个结果
                break;
            }
            num1 = numStack.pop();
            num2 = numStack.pop();
            oper = operStack.pop();
            res = numStack.cal(num1, num2, oper);
            numStack.push(res); // 入栈
        }

        int res2 = numStack.pop();
        System.out.printf("表达式%s=%d", expression, res2);

    }
}

/**
 * 定义一个ArrayStack 表示栈
 */
class ArrayStack2 {
    private int maxSize;
    private int[] stack;
    private int top = -1;

    public ArrayStack2(int maxSize) {
        this.maxSize = maxSize;
        stack = new int[maxSize];
    }

    // 栈满
    public boolean isFull() {
        return top == maxSize - 1;
    }

    // 栈空
    public boolean isEmpty() {
        return top == -1;
    }

    // 入栈
    public void push(int value) {
        if (isFull()) {
            System.out.println("栈满 === ");
            return;
        }

        top++;
        stack[top] = value;
    }

    // 出栈
    public int pop() {
        if (isEmpty()) {
            // 抛出异常
            throw new RuntimeException("栈空， 没有数据");
        }
        int value = stack[top];
        top--;
        return value;
    }

    // 遍历栈
    public void display() {
        if (isEmpty()) {
            // 抛出异常
            System.out.println("栈空， 没有数据");
            return;
        }
        for (int i = top; i >= 0; i--) {
            System.out.printf("stack[%d]=%d\n", i, stack[i]);
        }
    }

    // 返回运算符优先级（规定数组越大，优先级越高）
    public int priroty(int oper) {
        if (oper == '*' || oper == '/') {
            return 1;
        } else if (oper == '+' || oper == '-') {
            return 0;
        } else {
            return -1;
        }
    }

    // 判断运算符
    public boolean isOper(char val) {
        return val == '+' || val == '-' || val == '*' || val == '/';
    }

    // 计算方法
    public int cal(int num1, int num2, int oper) {
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

    // 返回当前栈顶的值，但不pop
    public int peek() {
        return stack[top];
    }
}