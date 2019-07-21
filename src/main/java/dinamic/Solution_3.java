package dinamic;

/**
 * Created by LiBingyi on 2019/7/21 15:15
 */
public class Solution_3 {

    public int maxSubArray(int[] nums) {
        int sum = 0;
        int ans = 0;
        for (int i = 0; i < nums.length; i++) {
            if (sum >= 0) {
                sum += nums[i];
            } else {
                sum = nums[i];
            }
            ans = sum > ans ? sum : ans;
        }
        return ans;
    }

}
