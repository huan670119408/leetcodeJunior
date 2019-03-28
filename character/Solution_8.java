package character;

/**
 * 报数
 * <p>
 * 报数序列是一个整数序列，按照其中的整数的顺序进行报数，得到下一个数。其前五项如下：
 * <p>
 * 1.     1
 * 2.     11
 * 3.     21
 * 4.     1211
 * 5.     111221
 * 1 被读作  "one 1"  ("一个一") , 即 11。
 * 11 被读作 "two 1s" ("两个一"）, 即 21。
 * 21 被读作 "one 2",  "one 1" （"一个二" ,  "一个一") , 即 1211。
 * <p>
 * 给定一个正整数 n（1 ≤ n ≤ 30），输出报数序列的第 n 项。
 * <p>
 * 注意：整数顺序将表示为一个字符串。
 * <p>
 * <p>
 * <p>
 * 示例 1:
 * <p>
 * 输入: 1
 * 输出: "1"
 * 示例 2:
 * <p>
 * 输入: 4
 * 输出: "1211"
 */
public class Solution_8 {

    public static String countAndSay(int n) {
        String r = "1";
        if (n == 1) {
            return r;
        }
        int i = 2;
        while (i <= n) {
            char[] a = r.toCharArray();
            int len = a.length;
            int j = 0;
            StringBuilder sb = new StringBuilder();
            while (j < len) {
                if (a[j] == '1' && j < len - 1 && a[j + 1] == '1') {
                    sb.append("21");
                    j += 2;
                } else if (a[j] == '1') {
                    sb.append("11");
                    j += 1;
                } else if (a[j] == '2') {
                    sb.append("1211");
                    j += 2;
                }
            }
            r = sb.toString();
            i++;
        }
        return r;
    }

    public static void main(String[] args) {
        System.out.println(countAndSay(5));
    }

}
