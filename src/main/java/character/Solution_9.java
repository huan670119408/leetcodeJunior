package scr.main.java.character;

import java.util.ArrayList;
import java.util.List;

/**
 * 最长公共前缀
 * <p>
 * 编写一个函数来查找字符串数组中的最长公共前缀。
 * <p>
 * 如果不存在公共前缀，返回空字符串 ""。
 * <p>
 * 示例 1:
 * <p>
 * 输入: ["flower","flow","flight"]
 * 输出: "fl"
 * 示例 2:
 * <p>
 * 输入: ["dog","racecar","car"]
 * 输出: ""
 * 解释: 输入不存在公共前缀。
 * 说明:
 * <p>
 * 所有输入只包含小写字母 a-z 。
 */
public class Solution_9 {

    public String longestCommonPrefix(String[] strs) {
        int minLength = 0;
        if (null == strs || strs.length == 0) {
            return "";
        }
        for (String cell : strs) {
            if (null == cell || cell.length() == 0) {
                return "";
            }
            if (minLength == 0 || cell.length() < minLength) {
                minLength = cell.length();
            }
        }
        if (strs.length == 1) {
            return strs[0];
        }
        List<char[]> list = new ArrayList<char[]>();
        for (String s : strs) {
            char[] c = s.toCharArray();
            list.add(c);
        }
        int k = 1;
        while (k <= minLength) {
            boolean flag = true;
            for (int i = 0; i < strs.length - 1; i++) {
                if (list.get(i)[k - 1] != list.get(i + 1)[k - 1]) {
                    flag = false;
                    break;
                }
            }
            if (!flag) {
                break;
            }
            k++;
        }
        StringBuilder sb = new StringBuilder();
        char[] chars = list.get(0);
        for (int i = 0; i < k-1; i++) {
            sb.append(chars[i]);
        }
        return sb.toString();
    }

}
