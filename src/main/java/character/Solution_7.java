package scr.main.java.character;

/**
 * 给定一个 haystack 字符串和一个 needle 字符串，在 haystack 字符串中找出 needle 字符串出现的第一个位置 (从0开始)。如果不存在，则返回  -1。
 *
 * 示例 1:
 *
 * 输入: haystack = "hello", needle = "ll"
 * 输出: 2
 * 示例 2:
 *
 * 输入: haystack = "aaaaa", needle = "bba"
 * 输出: -1
 *
 * 实测 用indexOf 4ms 超过99.25%的用户
 * 用KMP 9ms 36.05%
 */
public class Solution_7 {

    public static int strStr(String haystack, String needle) {
        if(null==haystack||null == needle) {
            return -1;
        }
        if(needle.length()>haystack.length()){
            return -1;
        }
        if (needle.length()==0) {
            return 0;
        }
        char[] h = haystack.toCharArray();
        char[] n = needle.toCharArray();
        int[] next = getNext(n);
        int i = 0;
        int j = 0;
        while (i<h.length&&j<n.length) {
            if(j == -1||h[i] == n[j]) {
                i++;
                j++;
            }else {
                j = next[j];
            }
        }
        if(j == n.length) {
            return i-j;
        }
        return -1;
    }

    private static int[] getNext(char[] a){
         int[] r = new int[a.length];
         int i =0;
         int j = -1;
         r[0] = -1;
         while (i<r.length-1) {
             if(j== -1 ||a[i] ==a[j] ) {
                 i++;
                 j++;
                 r[i] = j;
             } else {
                 j = r[j];
             }
         }
         return r;
    }

    public static void main(String[] args){
        String h = "hello";
        String n = "ll";
        System.out.println(strStr(h,n));
    }

}
