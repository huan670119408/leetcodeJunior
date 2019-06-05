package main.java.array;

/**
 * 对角线遍历
 * <p>
 * 给定一个含有 M x N 个元素的矩阵（M 行，N 列），请以对角线遍历的顺序返回这个矩阵中的所有元素，对角线遍历如下图所示。
 */
public class Solution_15 {

    public int[] findDiagonalOrder(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        int[] result = new int[m * n];
        int i = 0, j = 0;
        int k = 0;
        while (k < m * n) {
            result[k] = matrix[i][j];

        }
    }

}
