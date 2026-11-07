package com.example.sudoku6x6.models;

import java.util.Random;

/**
 * Stores the numbers of the 6x6 Sudoku board and creates the starting board.
 * <p>
 * The board is a two-dimensional array where 0 means an empty cell.
 *
 * @author Juan Camilo Morales
 * @author Nicolas Palacios
 * @author Juan Camilo Pinzon
 * @version 1.0
 */
public class SudokuBoard {

    /** Number of rows and columns of the board. */
    public static final int SIZE = 6;

    /** Number of rows in each block. */
    public static final int BLOCK_ROWS = 2;

    /** Number of columns in each block. */
    public static final int BLOCK_COLUMNS = 3;

    /** How many numbers each 2x3 block starts with. */
    private static final int NUMBERS_PER_BLOCK = 2;

    /** A complete board that follows all the Sudoku rules. */
    private static final int[][] SOLVED_BOARD = {
            {1, 2, 3, 4, 5, 6},
            {4, 5, 6, 1, 2, 3},
            {2, 3, 1, 5, 6, 4},
            {5, 6, 4, 2, 3, 1},
            {3, 1, 2, 6, 4, 5},
            {6, 4, 5, 3, 1, 2}
    };

    /** The numbers of the board, stored as [row][column]. */
    private int[][] numbers;

    /** Used to choose random cells. */
    private Random random = new Random();

    /**
     * Creates an empty board.
     */
    public SudokuBoard() {
        numbers = new int[SIZE][SIZE];
    }

    /**
     * Empties the board and places two random numbers in each 2x3 block.
     * The numbers come from a solved board, so the result is always valid
     * and can always be completed.
     */
    public void fillWithStartingNumbers() {
        numbers = new int[SIZE][SIZE];

        for (int startRow = 0; startRow < SIZE; startRow += BLOCK_ROWS) {
            for (int startColumn = 0; startColumn < SIZE; startColumn += BLOCK_COLUMNS) {
                fillBlock(startRow, startColumn);
            }
        }
    }

    /**
     * Copies two random numbers of the solved board into one block.
     *
     * @param startRow    first row of the block
     * @param startColumn first column of the block
     */
    private void fillBlock(int startRow, int startColumn) {
        int placed = 0;

        while (placed < NUMBERS_PER_BLOCK) {
            int row = startRow + random.nextInt(BLOCK_ROWS);
            int column = startColumn + random.nextInt(BLOCK_COLUMNS);

            if (isEmpty(row, column)) {
                numbers[row][column] = SOLVED_BOARD[row][column];
                placed++;
            }
        }
    }

    /**
     * Returns the number stored in a cell.
     *
     * @param row    row of the cell (0 to 5)
     * @param column column of the cell (0 to 5)
     * @return a number from 1 to 6, or 0 if the cell is empty
     */
    public int getNumber(int row, int column) {
        return numbers[row][column];
    }

    /**
     * Stores a number in a cell. Use 0 to empty the cell.
     *
     * @param row    row of the cell (0 to 5)
     * @param column column of the cell (0 to 5)
     * @param number a number from 1 to 6, or 0 to empty the cell
     */
    public void setNumber(int row, int column, int number) {
        numbers[row][column] = number;
    }

    /**
     * Tells if a cell is empty.
     *
     * @param row    row of the cell (0 to 5)
     * @param column column of the cell (0 to 5)
     * @return {@code true} if the cell has no number
     */
    public boolean isEmpty(int row, int column) {
        return numbers[row][column] == 0;
    }

    /**
     * Returns the whole board, so the controller can show it on the screen.
     *
     * @return a 6x6 array with numbers from 0 to 6
     */
    public int[][] getNumbers() {
        return numbers;
    }
}