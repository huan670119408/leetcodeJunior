package character;

import java.util.Arrays;

/**
 * 有效的字母异未词
 * 输入: s = "anagram", t = "nagaram"
 * 输出: true
 */
public class Solution_4 {

    public static boolean isAnagram(String s, String t) {
        if (null == s || null == t) {
            return false;
        }
        if (s.length() != t.length()) {
            return false;
        }
        char[] sArr = s.toCharArray();
        char[] tArr = t.toCharArray();
        Arrays.sort(sArr);
        Arrays.sort(tArr);
        for (int i = 0; i < sArr.length; i++) {
            if (sArr[i] != tArr[i]) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        String s = "";
        String t = "";
        System.out.println(isAnagram(s, t));
    }

}
