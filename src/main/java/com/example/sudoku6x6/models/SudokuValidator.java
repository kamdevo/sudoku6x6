package com.example.sudoku6x6.models;

/**
 * Checks the Sudoku rules on a board: a number cannot be repeated
 * in the same row, column or 2x3 block.
 *
 * @author Juan Camilo Morales
 * @author Nicolas Palacios
 * @author Juan Camilo Pinzon
 * @version 1.0
 */
public class SudokuValidator implements BoardValidator {

    /** The board this validator checks. */
    private SudokuBoard board;

    /**
     * Creates a validator for a board.
     *
     * @param board the board to check
     */
    public SudokuValidator(SudokuBoard board) {
        this.board = board;
    }

    @Override
    public boolean isValidMove(int row, int column, int number) {
        return isValidInRow(row, column, number)
                && isValidInColumn(row, column, number)
                && isValidInBlock(row, column, number);
    }

    @Override
    public boolean isValidBoard() {
        for (int row = 0; row < SudokuBoard.SIZE; row++) {
            for (int column = 0; column < SudokuBoard.SIZE; column++) {
                int number = board.getNumber(row, column);
                if (number != 0 && !isValidMove(row, column, number)) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Checks that the number is not in another cell of the same row.
     *
     * @param row    row of the cell
     * @param column column of the cell
     * @param number the number to check
     * @return {@code true} if the number is not repeated in the row
     */
    private boolean isValidInRow(int row, int column, int number) {
        for (int otherColumn = 0; otherColumn < SudokuBoard.SIZE; otherColumn++) {
            if (otherColumn != column && board.getNumber(row, otherColumn) == number) {
                return false;
            }
        }
        return true;
    }

    /**
     * Checks that the number is not in another cell of the same column.
     *
     * @param row    row of the cell
     * @param column column of the cell
     * @param number the number to check
     * @return {@code true} if the number is not repeated in the column
     */
    private boolean isValidInColumn(int row, int column, int number) {
        for (int otherRow = 0; otherRow < SudokuBoard.SIZE; otherRow++) {
            if (otherRow != row && board.getNumber(otherRow, column) == number) {
                return false;
            }
        }
        return true;
    }

    /**
     * Checks that the number is not in another cell of the same 2x3 block.
     *
     * @param row    row of the cell
     * @param column column of the cell
     * @param number the number to check
     * @return {@code true} if the number is not repeated in the block
     */
    private boolean isValidInBlock(int row, int column, int number) {
        int startRow = (row / SudokuBoard.BLOCK_ROWS) * SudokuBoard.BLOCK_ROWS;
        int startColumn = (column / SudokuBoard.BLOCK_COLUMNS) * SudokuBoard.BLOCK_COLUMNS;

        for (int otherRow = startRow; otherRow < startRow + SudokuBoard.BLOCK_ROWS; otherRow++) {
            for (int otherColumn = startColumn; otherColumn < startColumn + SudokuBoard.BLOCK_COLUMNS; otherColumn++) {
                boolean isSameCell = otherRow == row && otherColumn == column;
                if (!isSameCell && board.getNumber(otherRow, otherColumn) == number) {
                    return false;
                }
            }
        }
        return true;
    }
}