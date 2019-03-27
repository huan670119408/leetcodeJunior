package character;

/**
 * 验证回文字符串
 * 给定一个字符串，验证它是否是回文串，只考虑字母和数字字符，可以忽略字母的大小写。
 * 输入: "A man, a plan, a canal: Panama"
 * 输出: true
 * <p>
 * 输入: "race a car"
 * 输出: false
 */
public class Solution_5 {

    //    大写字母（A-Z）：65 （A）~ 90（Z）
//    小写字母（a-z）：97（a） ~ 122（z）
//    字符数字（'0' ~ '9'）：48（'0'） ~ 57（'9'）
    public static boolean isPalindrome(String s) {
        if (null == s) {
            return false;
        }
        if ("".equals(s)) {
            return true;
        }
        char[] chars = s.toCharArray();
        int m = 0;
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] >= 65 && chars[i] <= 90) {
                chars[m] = (char) (chars[i] + 32);
                m++;
            } else if ((chars[i] >= 97 && chars[i] <= 122) || (chars[i] >= 48 && chars[i] <= 57)) {
                chars[m] = chars[i];
                m++;
            }
        }
        int n = 0;
        m = m - 1;
        while (n < m) {
            if (chars[n] != chars[m]) {
                return false;
            }
            n++;
            m--;
        }
        return true;
    }

    public static void main(String[] args) {
//        String s = "A man, a plan, a canal: Panama";
        String s = "0P";
        System.out.println(isPalindrome(s));
    }

}
