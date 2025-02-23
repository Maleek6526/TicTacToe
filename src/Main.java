import java.util.Random;
import java.util.Scanner;

public class Main {
    private static char[][] gameBoard;
    private static final Scanner scanner = new Scanner(System.in);
    private static int playerScore = 0;
    private static int cpuScore = 0;

    public static void main(String[] args) {
        while (true) {
            resetBoard();
            printGameBoard();

            while (true) {
                int playerResponse = promptForPlacement();

                if (!placeMarker(playerResponse, "player")) {
                    continue;
                }

                if (checkWin("player")) {
                    playerScore++;
                    printGameBoard();
                    print("Player wins!");
                    break;
                }

                if (isBoardFull()) {
                    printGameBoard();
                    print("It's a tie!");
                    break;
                }

                int computerResponse = getComputerMove();

                if (checkWin("CPU")) {
                    cpuScore++;
                    printGameBoard();
                    print("CPU wins!");
                    break;
                }

                printGameBoard();

                if (isBoardFull()) {
                    print("It's a tie!");
                    break;
                }
            }

            print("Scoreboard:");
            print("Player: " + playerScore + " | CPU: " + cpuScore);

            if (!promptForPlayAgain()) {
                print("Thanks for playing! Here is the final score:");
                print("Final Score:");
                print("Player: " + playerScore + " | CPU: " + cpuScore);
                print("Goodbye!");
                break;
            }
        }
    }

    private static void resetBoard() {
        gameBoard = new char[][] {
                {' ', '|', ' ', '|', ' '},
                {'-', '+', '-', '+', '-'},
                {' ', '|', ' ', '|', ' '},
                {'-', '+', '-', '+', '-'},
                {' ', '|', ' ', '|', ' '}
        };
    }

    private static void printGameBoard() {
        for (char[] row : gameBoard) {
            System.out.println(row);
        }
    }

    private static boolean placeMarker(int position, String player) {
        char marker = (player.equals("player")) ? 'X' : 'O';

        switch (position) {
            case 1 -> { if (gameBoard[0][0] == ' ') { gameBoard[0][0] = marker; return true; } }
            case 2 -> { if (gameBoard[0][2] == ' ') { gameBoard[0][2] = marker; return true; } }
            case 3 -> { if (gameBoard[0][4] == ' ') { gameBoard[0][4] = marker; return true; } }
            case 4 -> { if (gameBoard[2][0] == ' ') { gameBoard[2][0] = marker; return true; } }
            case 5 -> { if (gameBoard[2][2] == ' ') { gameBoard[2][2] = marker; return true; } }
            case 6 -> { if (gameBoard[2][4] == ' ') { gameBoard[2][4] = marker; return true; } }
            case 7 -> { if (gameBoard[4][0] == ' ') { gameBoard[4][0] = marker; return true; } }
            case 8 -> { if (gameBoard[4][2] == ' ') { gameBoard[4][2] = marker; return true; } }
            case 9 -> { if (gameBoard[4][4] == ' ') { gameBoard[4][4] = marker; return true; } }
            default -> print("Invalid Input");
        }
        return false;
    }

    private static int promptForPlacement() {
        int playerResponse;
        while (true) {
            playerResponse = prompt("Enter your placement (1 - 9): ");
            if (playerResponse >= 1 && playerResponse <= 9) {
                return playerResponse;
            } else {
                print("Invalid input. Please enter a number between 1 and 9.");
            }
        }
    }

    private static boolean promptForPlayAgain() {
        String response;
        while (true) {
            print("Do you want to play again? (yes/no): ");
            response = scanner.next().toLowerCase();
            if (response.equals("yes") || response.equals("no")) {
                return response.equals("yes");
            } else {
                print("Invalid input. Please enter 'yes' or 'no'.");
            }
        }
    }

    private static int getComputerMove() {
        Random random = new Random();
        int move;
        do {
            move = random.nextInt(9) + 1;
        } while (!placeMarker(move, "CPU"));
        return move;
    }

    private static boolean checkWin(String player) {
        char symbol = player.equals("player") ? 'X' : 'O';

        return (gameBoard[0][0] == symbol && gameBoard[0][2] == symbol && gameBoard[0][4] == symbol) ||
                (gameBoard[2][0] == symbol && gameBoard[2][2] == symbol && gameBoard[2][4] == symbol) ||
                (gameBoard[4][0] == symbol && gameBoard[4][2] == symbol && gameBoard[4][4] == symbol) ||
                (gameBoard[0][0] == symbol && gameBoard[2][0] == symbol && gameBoard[4][0] == symbol) ||
                (gameBoard[0][2] == symbol && gameBoard[2][2] == symbol && gameBoard[4][2] == symbol) ||
                (gameBoard[0][4] == symbol && gameBoard[2][4] == symbol && gameBoard[4][4] == symbol) ||
                (gameBoard[0][0] == symbol && gameBoard[2][2] == symbol && gameBoard[4][4] == symbol) ||
                (gameBoard[0][4] == symbol && gameBoard[2][2] == symbol && gameBoard[4][0] == symbol);
    }

    private static boolean isBoardFull() {
        for (int index = 0; index < 5; index += 2) {
            for (int count = 0; count < 5; count += 2) {
                if (gameBoard[index][count] == ' ') {
                    return false;
                }
            }
        }
        return true;
    }

    private static void print(String message) {
        System.out.println(message);
    }

    private static int prompt(String message) {
        print(message);
        return scanner.nextInt();
    }
}