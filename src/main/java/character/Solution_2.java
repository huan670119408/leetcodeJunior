package scr.main.java.character;

/**
 * 整数反转
 */
public class Solution_2 {

    public static int reverse(int x) {
        if (x == 0 || x ==-2147483648) {
            return 0;
        }
        boolean flag = true;
        boolean min = false;
        if (x < 0) {
            flag = false;
            x = -x;
        }
        while (x % 10 == 0) {
            x = x / 10;
        }
        char[] chars = ("" + x).toCharArray();

        int i = 0;
        int j = chars.length - 1;
        while (i < j) {
            char temp = chars[i];
            chars[i] = chars[j];
            chars[j] = temp;
            i++;
            j--;
        }
        String s = new String(chars);

        if(chars.length==10) {
            if(flag&&s.compareTo("2147483647")>0) {
                return 0;
            } else if(!flag&&s.compareTo("2147483648")>0){
                return 0;
            }
        }
        int result = Integer.valueOf(s);
        if (!flag) {
            result = -result;
        }
        return result;
    }

    public static void main(String[] args) {
        int i = -2147483648;
        int r = reverse(i);
       System.out.print(r);
    }

}
