/* N-size sudoku is a game with a square table of N^2 width and height divided into N^2 smaller squares of N width and height.
In a solved state, each of these smaller squares, as well as each row and column of a full square,
contains all numbers from 1 to N^2 without repetition.
Given a number N on the first line and a full sudoku table on the next N^2 lines. Every line contains N^2 integers.
Your task is to determine whether this sudoku is solved or not. Output "YES" if this sudoku table is solved, otherwise "NO".
N can be from 1 to 10. */

import java.util.Scanner;

public class Sudoku {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[][] matrix = new int[n * n][n * n];

        // build sudoku matrix from StdIn
        for (int i = 0; i < n * n; i++) {
            for (int j = 0; j < n * n; j++) {
                matrix[i][j] = scanner.nextInt();
            }
        }

        boolean solved = true;

        for (int i = 0; i < n * n; i++) {

            // boolean array to check unique numbers in each row
            boolean[] numRow = new boolean[n * n + 1];
            // boolean array to check unique numbers in each column
            boolean[] numCol = new boolean[n * n + 1];

            for (int j = 0; j < n * n; j++) {

                // for rows
                // check if the number is in the range
                if (matrix[i][j] > n * n || matrix[i][j] < 1) {
                    solved = false;
                    break;
                }
                // check for uniqueness
                if (numRow[matrix[i][j]]) {
                    solved = false;
                    break;
                }
                else numRow[matrix[i][j]] = true;

                // for columns
                // check if the number is in the range
                if (matrix[j][i] > n * n || matrix[j][i] < 1) {
                    solved = false;
                    break;
                }
                // check for uniqueness
                if (numCol[matrix[j][i]]) {
                    solved = false;
                    break;
                }
                else numCol[matrix[j][i]] = true;
            }
            if (!solved) break;
        }

        // check sub-matrices
        for (int r = 0; r < n * n; r += n) {
            if (!solved) break;
            for (int c = 0; c < n * n; c += n) {

                // boolean array to check unique numbers in each row
                boolean[] num = new boolean[n * n + 1];

                for (int i = 0; i < n; i++) {
                    for (int j = 0; j < n; j++) {
                        // check if the number is in the range
                        if (matrix[j][i] > n * n || matrix[j][i] < 1) {
                            solved = false;
                            break;
                        }

                        // check for uniqueness
                        if (num[matrix[j][i]]) {
                            solved = false;
                            break;
                        }
                        else num[matrix[j][i]] = true;
                    }
                    if (!solved) break;
                }
                if (!solved) break;
            }
        }

        if (solved) System.out.println("YES");
        else System.out.println("NO");
    }
}
