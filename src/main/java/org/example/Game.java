package org.example;

import java.util.Scanner;

public class Game {
    private final char alive = '*';
    private final char dead = '.';
    private final int stateLength;
    private final int stateWidth;
    private char currentChar;
    private char[][] currentGameState;
    private char[][] nextGameState;

    public Game() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter states length and width of array of Life's: ");
        this.stateLength = scanner.nextInt();
        this.stateWidth = scanner.nextInt();
        this.currentGameState = new char[this.stateLength][this.stateWidth];
        this.nextGameState = new char[this.stateLength][this.stateWidth];
        System.out.print("Enter states * for alive and . for dead: ");
        for (int i = 0; i < stateLength; i++) {
            for (int j = 0; j < stateWidth; j++) {
                this.currentChar = scanner.next().charAt(0);
                currentGameState[i][j] = this.currentChar;
                nextGameState[i][j] = this.currentChar;
            }
        }
    }

    /**
     * prints current game state
     */
    public void printGameState() {
        System.out.println("Текущее состояние игры: ");
        for (int i = 0; i < stateLength; i++) {
            for (int j = 0; j < stateWidth; j++) {
                System.out.print(currentGameState[i][j] + " ");
            }
            System.out.println();
        }
    }

    /**
     * do next iterations of the game until 0 have been written
     */
    public void startGame() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter 0 to move to the next phase or other number to exit program: ");
        while (scanner.nextInt() != 0) {
            printGameState();
            nextIteration();
            System.out.println();
            System.out.println("Enter 0 to move to the next phase or other number to exit program: ");
        }
    }

    private void nextIteration () {
        // write new game state in copied array
        for (int i = 0; i < this.stateLength; i++) {
            for (int j = 0; j < this.stateWidth; j++) {
                nextGameState[i][j] = moveToNextState(i, j);
            }
        }
        // move data from copied array to actual used in game
        for (int i = 0; i < this.stateLength; i++) {
            for (int j = 0; j < this.stateWidth; j++) {
                currentGameState[i][j] = nextGameState[i][j];
            }
        }
    }

    /**
     * find count of alives for every single char in array
     * @param row currentGameState array row
     * @param col currentGameState array column
     * @return dead or alive char
     */
    private char moveToNextState(int row, int col) {
        int countAlive = 0;
        // check for * chars and count them
        if (row == 0 && col == 0) {
            countAlive += isAlive(currentGameState[row+1][col]);
            countAlive += isAlive(currentGameState[row+1][col+1]);
            countAlive += isAlive(currentGameState[row][col+1]); // done up left corner
        } else if (row == 0 && col == this.stateWidth - 1) {
            countAlive += isAlive(currentGameState[row][col-1]);
            countAlive += isAlive(currentGameState[row+1][col-1]);
            countAlive += isAlive(currentGameState[row+1][col]); // done up right corner
        } else if (row == this.stateLength - 1 && col == 0) {
            countAlive += isAlive(currentGameState[row-1][col]);
            countAlive += isAlive(currentGameState[row-1][col+1]);
            countAlive += isAlive(currentGameState[row][col+1]); // done down left corner
        } else if (row == this.stateLength - 1 && col == this.stateWidth - 1) {
            countAlive += isAlive(currentGameState[row-1][col-1]);
            countAlive += isAlive(currentGameState[row-1][col]);
            countAlive += isAlive(currentGameState[row][col-1]); // done down right corner
        } else if (row == 0) {
            countAlive += isAlive(currentGameState[row+1][col+1]);
            countAlive += isAlive(currentGameState[row+1][col]);
            countAlive += isAlive(currentGameState[row+1][col-1]);
            countAlive += isAlive(currentGameState[row][col-1]);
            countAlive += isAlive(currentGameState[row][col+1]); // done first row
        } else if (col == 0) {
            countAlive += isAlive(currentGameState[row][col+1]);
            countAlive += isAlive(currentGameState[row-1][col+1]);
            countAlive += isAlive(currentGameState[row+1][col+1]);
            countAlive += isAlive(currentGameState[row-1][col]);
            countAlive += isAlive(currentGameState[row+1][col]); // done first column
        } else if (row == this.stateLength - 1){
            countAlive += isAlive(currentGameState[row][col+1]);
            countAlive += isAlive(currentGameState[row][col-1]);
            countAlive += isAlive(currentGameState[row-1][col+1]);
            countAlive += isAlive(currentGameState[row-1][col]);
            countAlive += isAlive(currentGameState[row-1][col-1]); // done last row
        } else if (col == this.stateWidth - 1) {
            countAlive += isAlive(currentGameState[row+1][col]);
            countAlive += isAlive(currentGameState[row-1][col]);
            countAlive += isAlive(currentGameState[row][col-1]);
            countAlive += isAlive(currentGameState[row+1][col-1]);
            countAlive += isAlive(currentGameState[row-1][col-1]); // done last column
        } else {
            countAlive += isAlive(currentGameState[row-1][col-1]);
            countAlive += isAlive(currentGameState[row-1][col]);
            countAlive += isAlive(currentGameState[row-1][col+1]);
            countAlive += isAlive(currentGameState[row][col-1]);
            countAlive += isAlive(currentGameState[row][col+1]);
            countAlive += isAlive(currentGameState[row+1][col-1]);
            countAlive += isAlive(currentGameState[row+1][col]);
            countAlive += isAlive(currentGameState[row+1][col+1]); // done other cases
        }
        // game rules
        if (countAlive < 2) {
            return dead;
        } else if (countAlive == 2) {
            return currentGameState[row][col];
        } else if (countAlive == 3) {
            return alive;
        } else if (countAlive > 3) {
            return dead;
        } else {
            return '!'; // add return statement error
        }
    }

    private int isAlive(char s) {
        if (s == alive) {
            return 1;
        } else if (s == dead) {
            return 0;
        } else {
            System.out.println("Fail!!!"); // add return statement error
            return 0;
        }
    }
}

