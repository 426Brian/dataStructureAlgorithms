package algorithm.stringTest;


public class StringTest {
    public static void main(String[] args) {
        String str = "abcdefg";

        String res = reverse(str, 3, 5);
        System.out.println(res);

        String str1 = "abkkcadkabkebfkabkskab";
        String str2 = "ab";

        int num = count(str1, str2);
        System.out.println(num);

        String str3 = "abcwerthelloyuiodef";
        String str4 = "cvhellobnm";

        String tmp = containMaxStr(str3, str4);
        System.out.println(tmp);
    }

    /**
     * 字符串反转
     *
     * @param str
     * @param start
     * @param end
     * @return
     */
    public static String reverse(String str, int start, int end) {
        char[] ch = str.toCharArray();

        if (str != null) {
            for (int x = start, y = end; x < y; x++, y--) {
                char chtmp = ch[x];
                ch[x] = ch[y];
                ch[y] = chtmp;
            }
            return new String(ch);
        }

        return null;

    }


    /**
     * 两个字符串中包含相同最大的字符串
     */
    public static String containMaxStr(String str1, String str2) {
        int ligLen = str1.length();
        int len = str2.length();

        String longStr = (ligLen >= len ? str1 : str2);
        String shortStr = (ligLen < len ? str1 : str2);

        for (int i = 0; i < len; i++) {
            for (int x = 0, y = len - i; y <= len; x++, y++) {
                String substring = shortStr.substring(x, y);

                if(longStr.contains(substring)){
                    return substring;
                }
            }
        }

        return null;

    }

    public static int count(String str1, String str2) {
        int index = 0;

        int num = 0;
        while ((index = str1.indexOf(str2, index)) > -1) {
            index += str2.length();
            num++;
        }

        return num;
    }
}
