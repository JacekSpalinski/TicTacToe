/* Output the matrix of size n×n, filled by the integers from 1 to n*n in a spiral,
coming from the top left corner and twisted clockwise */

public class SpiralMatrix {
    public static void main(String[] args) {

        int n = Integer.parseInt(args[0]);

        int[][] mat = new int[n][n];

        int u = 0;
        int r = n - 1;
        int d = n - 1;
        int l = 0;

        int num = 1;

        while (d >= u && r >= l) {

            for (int i = l; i <= r; i++) {
                mat[u][i] = num;
                num++;
            }
            u++;

            for (int i = u; i <= d; i++) {
                mat[i][r] = num;
                num++;
            }
            r--;

            for (int i = r; i >= l; i--) {
                mat[d][i] = num;
                num++;
            }
            d--;

            for (int i = d; i >= u; i--) {
                mat[i][l] = num;
                num++;
            }
            l++;
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(mat[i][j] + " ");
            }
            System.out.println();
        }
    }
}

