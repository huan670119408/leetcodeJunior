package scr.main.java.character;

/**
 * 字符串中第一个唯一字符
 */
public class Solution_3 {

    public static int firstUniqChar(String s) {
        if(null == s||"".equals(s)) {
            return -1;
        }
        char[] chars = s.toCharArray();
        int[] check = new int[chars.length];
        if(chars.length == 1) {
            return 0;
        }
        int i = 0;
        while (i<chars.length-1) {
            if(check[i] != 0){
                i++;
                continue;
            }
            boolean flag = true;
            for(int j = i+1;j<chars.length;j++) {
                if(chars[i] == chars[j]) {
                    check[j] = 1;
                    flag = false;
                    continue;
                }
                if(flag&&j == chars.length-1) {
                    return i;
                }
            }
            i++;
        }
        return check[chars.length-1]==0?chars.length-1:-1;
    }

    public static void main(String[] args){
        String a = "";
        System.out.println(firstUniqChar(a));
    }

}
