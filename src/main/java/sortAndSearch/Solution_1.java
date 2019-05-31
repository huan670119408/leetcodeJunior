package main.java.sortAndSearch;

/**
 * 给定两个有序整数数组 nums1 和 nums2，将 nums2 合并到 nums1 中，使得 num1 成为一个有序数组。
 * <p>
 * 说明:
 * <p>
 * 初始化 nums1 和 nums2 的元素数量分别为 m 和 n。
 * 你可以假设 nums1 有足够的空间（空间大小大于或等于 m + n）来保存 nums2 中的元素。
 */
public class Solution_1 {

    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int i = 0;
        int j = 0;
        int k = 0;
        int[] a = new int[m + n];
        while (i < m && j < n) {
            if (nums1[i] <= nums2[j]) {
                a[k++] = nums1[i++];
            } else {
                a[k++] = nums2[j++];
            }
        }
        while (i < m) {
            a[k++] =  nums1[i++];
        }
        while (j < n) {
            a[k++] = nums2[j++];
        }
        for(int t = 0; t< k; t++){
            nums1[t] = a[t];
        }
    }

}
