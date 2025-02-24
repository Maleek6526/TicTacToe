package Game;

import java.util.Random;
import java.util.Scanner;

public class App {
    private char[][] gameBoard;
    private int playerScore;
    private int cpuScore;
    private final Scanner scanner;

    public App() {
        this.playerScore = 0;
        this.cpuScore = 0;
        this.scanner = new Scanner(System.in);
        resetBoard();
    }

    public void play() {
        while (true) {
            resetBoard();
            printGameBoard();

            while (true) {
                int playerResponse = promptForPlacement();
                if (!placeMarker(playerResponse, 'X')) continue;

                if (checkWin('X')) {
                    playerScore++;
                    printGameBoard();
                    System.out.println("Player wins!");
                    break;
                }

                if (isBoardFull()) {
                    printGameBoard();
                    System.out.println("It's a tie!");
                    break;
                }

                int computerResponse = getComputerMove();
                if (checkWin('O')) {
                    cpuScore++;
                    printGameBoard();
                    System.out.println("CPU wins!");
                    break;
                }
                printGameBoard();
            }

            System.out.println("Scoreboard:");
            System.out.println("Player: " + playerScore + " | CPU: " + cpuScore);

            if (!promptForPlayAgain()) {
                System.out.println("Thanks for playing! Final Score:");
                System.out.println("Player: " + playerScore + " | CPU: " + cpuScore);
                System.out.println("Goodbye!");
                break;
            }
        }
    }

    public void resetBoard() {
        gameBoard = new char[][] {
                {' ', '|', ' ', '|', ' '},
                {'-', '+', '-', '+', '-'},
                {' ', '|', ' ', '|', ' '},
                {'-', '+', '-', '+', '-'},
                {' ', '|', ' ', '|', ' '}
        };
    }

    public void printGameBoard() {
        for (char[] row : gameBoard) {
            System.out.println(row);
        }
    }

    public boolean placeMarker(int position, char marker) {
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
            default -> System.out.println("Invalid Input");
        }
        return false;
    }

    private int promptForPlacement() {
        int playerResponse;
        while (true) {
            System.out.print("Enter your placement (1 - 9): ");
            if (scanner.hasNextInt()) {
                playerResponse = scanner.nextInt();
                if (playerResponse >= 1 && playerResponse <= 9) {
                    return playerResponse;
                }
            } else {
                scanner.next(); // Clear invalid input
            }
            System.out.println("Invalid input. Please enter a number between 1 and 9.");
        }
    }

    private boolean promptForPlayAgain() {
        while (true) {
            System.out.print("Do you want to play again? (yes/no): ");
            String response = scanner.next().toLowerCase();
            if (response.equals("yes")) return true;
            if (response.equals("no")) return false;
            System.out.println("Invalid input. Please enter 'yes' or 'no'.");
        }
    }

    public int getComputerMove() {
        Random random = new Random();
        int move;
        do {
            move = random.nextInt(9) + 1;
        } while (!placeMarker(move, 'O'));
        return move;
    }

    public boolean checkWin(char symbol) {
        return (gameBoard[0][0] == symbol && gameBoard[0][2] == symbol && gameBoard[0][4] == symbol) ||
                (gameBoard[2][0] == symbol && gameBoard[2][2] == symbol && gameBoard[2][4] == symbol) ||
                (gameBoard[4][0] == symbol && gameBoard[4][2] == symbol && gameBoard[4][4] == symbol) ||
                (gameBoard[0][0] == symbol && gameBoard[2][0] == symbol && gameBoard[4][0] == symbol) ||
                (gameBoard[0][2] == symbol && gameBoard[2][2] == symbol && gameBoard[4][2] == symbol) ||
                (gameBoard[0][4] == symbol && gameBoard[2][4] == symbol && gameBoard[4][4] == symbol) ||
                (gameBoard[0][0] == symbol && gameBoard[2][2] == symbol && gameBoard[4][4] == symbol) ||
                (gameBoard[0][4] == symbol && gameBoard[2][2] == symbol && gameBoard[4][0] == symbol);
    }

    public boolean isBoardFull() {
        for (int row = 0; row < 5; row += 2) {
            for (int col = 0; col < 5; col += 2) {
                if (gameBoard[row][col] == ' ') {
                    return false;
                }
            }
        }
        return true;
    }
}
