package com.example.sudoku6x6.models;

/**
 * Defines the checks needed to follow the Sudoku rules.
 *
 * @author Juan Camilo Morales
 * @author Nicolas Palacios
 * @author Juan Camilo Pinzon
 * @version 1.0
 */
public interface BoardValidator {

    /**
     * Checks if a number can be placed in a cell without breaking the rules.
     *
     * @param row    row of the cell (0 to 5)
     * @param column column of the cell (0 to 5)
     * @param number the number to check (1 to 6)
     * @return {@code true} if the number is not repeated in its row, column or 2x3 block
     */
    boolean isValidMove(int row, int column, int number);

    /**
     * Checks the whole board.
     *
     * @return {@code true} if no number is repeated in any row, column or 2x3 block
     */
    boolean isValidBoard();
}