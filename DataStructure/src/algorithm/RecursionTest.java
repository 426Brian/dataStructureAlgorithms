package algorithm;

public class RecursionTest {
    public static void main(String[] args) {

        test(4);
    }

    public static void test(int a) {
        if (a > 2) {
            test(a - 1);
        }

        System.out.println("a = " + a);
    }
}
