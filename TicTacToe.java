import java.util.Scanner;

public class TicTacToe {

    // check state of a game
    public static String checkState(char[][] grid) {
        int countX = 0;
        int countO = 0;

        // count occurrences of X and O
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (grid[i][j] == 'X') countX++;
                if (grid[i][j] == 'O') countO++;
            }
        }

        // check if there is too many X's or O's
        if (countX > countO + 1 || countO > countX + 1) {
            return "Impossible";
        }

        int winX = 0;
        int winO = 0;

        // check if there is a win for X's or O's in vertical anr horizontal
        for (int i = 0; i < 3; i++) {
            int rowX = 0;
            int rowO = 0;
            int colX = 0;
            int colO = 0;
            for (int j = 0; j < 3; j++) {
                if (grid[i][j] == 'X') rowX++;
                if (grid[i][j] == 'O') rowO++;
                if (grid[j][i] == 'X') colX++;
                if (grid[j][i] == 'O') colO++;
            }
            if (rowX == 3) winX++;
            if (rowO == 3) winO++;
            if (colX == 3) winX++;
            if (colO == 3) winO++;
        }

        // check for wins in diagonal
        if (grid[0][0] == 'X' && grid[1][1] == 'X' && grid[2][2] == 'X') {
            winX++;
        }
        if (grid[0][0] == 'O' && grid[1][1] == 'O' && grid[2][2] == 'O') {
            winO++;
        }
        if (grid[0][2] == 'X' && grid[1][1] == 'X' && grid[2][0] == 'X') {
            winX++;
        }
        if (grid[0][2] == 'O' && grid[1][1] == 'O' && grid[2][0] == 'O') {
            winO++;
        }

        // check if both X's and O's have a win
        if (winO == 1 && winX == 1) {
            return "Impossible";
        }
        // check for single wins
        if (winO == 1) return "O wins";
        if (winX == 1) return "X wins";
        // check for draw
        if (countO + countX == 9) return "Draw";
            // check for unfinished game
        else return "Game not finished";
    }

    // checks if given input is valid and returns correct input
    public static String validate(String input, char[][] grid) {
        Scanner scanner = new Scanner(System.in);
        boolean ok = false;

        while (!ok) {
            // check if input consists of numbers
            if (!input.matches("\\d\\s\\d")) {
                System.out.println("You should enter numbers!");
                System.out.print("Enter the coordinates: ");
                input = scanner.nextLine();
                continue;
            }
            // check if numbers are within 1 - 3 range
            else if (!input.matches("[1-3]\\s[1-3]")) {
                System.out.println("Coordinates should be from 1 to 3!");
                System.out.print("Enter the coordinates: ");
                input = scanner.nextLine();
                continue;
            }
            // convert string input into coordinates
            int x = Integer.parseInt(input.substring(0, 1));
            int y = Integer.parseInt(input.substring(2, 3));
            // check if cell if occupied
            if (grid[x - 1][y - 1] == 'X' || grid[x - 1][y - 1] == 'O') {
                System.out.println("This cell is occupied! Choose another one!");
                System.out.print("Enter the coordinates: ");
                input = scanner.nextLine();
            }
            else ok = true;
        }
        return input;
    }

    // prints the board
    public static void printBoard(char[][] grid) {
        System.out.println("---------");
        System.out.println(
                "| " + grid[0][2] + " " + grid[1][2] + " " + grid[2][2] + " |");
        System.out.println(
                "| " + grid[0][1] + " " + grid[1][1] + " " + grid[2][1] + " |");
        System.out.println(
                "| " + grid[0][0] + " " + grid[1][0] + " " + grid[2][0] + " |");
        System.out.println("---------");
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Create empty grid that will keep track of players' moves
        char[][] grid = new char[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                grid[i][j] = ' ';
            }
        }

        printBoard(grid);

        // Keeps track of state of a game
        String state = "Game not finished";
        // Keeps track of whether it is O's or X's move
        int move = 0;

        while (state.equals("Game not finished")) {

            // Ask user for coordinates
            System.out.print("Enter the coordinates: ");
            String input = scanner.nextLine();

            String coor = validate(input, grid);

            int x = Integer.parseInt(coor.substring(0, 1));
            int y = Integer.parseInt(coor.substring(2, 3));

            // update the board
            if (move % 2 == 0) {
                grid[x - 1][y - 1] = 'X';
            }
            else grid[x - 1][y - 1] = 'O';
            move++;

            printBoard(grid);

            // Update state of the game
            state = checkState(grid);

            if (!state.equals("Game not finished")) System.out.println(state);

        }
    }
}
