package character;

/**
 * 反转字符串
 */
public class Solution_1 {

    public static void reverseString(char[] s) {
        if(null == s || s.length<2){
            return;
        }
        int i = 0;
        int j = s.length-1;
        while (i<=j) {
            char temp = s[i];
            s[i] = s[j];
            s[j] = temp;
            i++;
            j--;
        }
    }

    public static void main(String[] args){
        char[] c = {'h','e','l','l','o'};
        reverseString(c);
        for(char k : c){
            System.out.print(k+";");
        }
    }

}
