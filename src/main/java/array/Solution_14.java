package main.java.array;

/**
 * 加一
 * <p>
 * 给定一个由整数组成的非空数组所表示的非负整数，在该数的基础上加一。
 * <p>
 * 最高位数字存放在数组的首位， 数组中每个元素只存储一个数字。
 * <p>
 * 你可以假设除了整数 0 之外，这个整数不会以零开头。
 */
public class Solution_14 {

    public static int[] plusOne(int[] digits) {
        int l = digits.length;
        boolean flag = true;
        for (int i = 0; i < l; i++) {
            if (digits[i] != 9) {
                flag = false;
                break;
            }
        }
        if (flag) {
            int[] arr = new int[l + 1];
            arr[0] = 1;
            for (int i = 1; i < arr.length; i++) {
                arr[i] = 0;
            }
            return arr;
        }
        for (int i = l - 1; i >= 0; i--) {
            if (digits[i] == 9) {
                digits[i] = 0;
            } else {
                digits[i] = digits[i] + 1;
                break;
            }
        }
        return digits;
    }

    public static void main(String[] args){
        int[] a = new int[1];
        a[0] = 0;
        plusOne(a);
    }

}
