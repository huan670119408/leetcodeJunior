package dinamic;

/**
 * Created by LiBingyi on 2019/7/21 14:45
 */
public class Solution_2 {

    public int maxProfit(int[] arr) {
        int minIndex = 0;
        int max = 0;
        for(int i = 1;i<arr.length;i++){
            if(arr[i] >=arr[minIndex]){
                int temp = arr[i] - arr[minIndex];
                max = temp > max?temp:max;
            } else {
                minIndex = i;
            }
        }
        return  max;
    }

}
