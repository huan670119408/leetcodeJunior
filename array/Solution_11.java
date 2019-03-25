package array;

/**
 * 旋转图像
 */
public class Solution_11 {

    public static void rotate(int[][] matrix) {
        if (null == matrix) {
            return;
        }
        int len = matrix.length;
        int level = len / 2;
        for (int i = 0; i < level; i++) {
            go(i, len, matrix);
            len = len - 2;
        }
        for (int i = 0; i < len - 1; i++) {

        }
    }

    private static void go(int n, int len, int[][] matrix) {
        for (int i = 0; i < len - 1; i++) {
            int curr = 0;
            int temp = 0;
            for (int j = 0; j < 4; j++) {
                switch (j) {
                    case 0:
                        curr = matrix[n+i][n + len - 1];
                        matrix[n+i][n + len - 1] = matrix[n][n+i];
                        break;
                    case 1:
                        temp = matrix[n + len - 1][n + len - 1-i];
                        matrix[n + len - 1][n + len - 1-i] = curr;
                        curr = temp;
                        break;
                    case 2:
                        temp = matrix[n + len - 1-i][n];
                        matrix[n + len - 1-i][n] = curr;
                        curr = temp;
                        break;
                    case 3:
                        matrix[n][n+i] = curr;
                        break;
                }

            }
        }
    }

    public static void main(String[] args) {
        int[][] arr = {{1,2,3},{4,5,6},{7,8,9}};
        rotate(arr);
        for(int[] m : arr) {
            for(int n : m){
                System.out.print(n+";");
            }
            System.out.println();
        }
    }


}
