package design;

import java.util.Random;

/**
 * 打乱一个没有重复元素的数组。
 */
public class ShuffleAnArray {

    private int[] array;

    private int[] origin;

    private Random random = new Random();

    public ShuffleAnArray(int[] nums) {
        array = nums;
        origin = array.clone();
    }

    /** Resets the array to its original configuration and return it. */
    public int[] reset() {
        array = origin;
        origin = array.clone();
        return array;
    }

    private void swap(int i, int j){
        int temp = array[i];
        array[j] = array[i];
        array[i] = temp;
    }

    private int getRandom(int l,int r){
        return random.nextInt(r-l) +l;
    }

    /** Returns a random shuffling of the array. */
    public int[] shuffle() {
        for(int i = 0 ; i<array.length;i++){
            int random = getRandom(i + 1, array.length - 1);
            swap(i,random);
        }
        return array;
    }

}
