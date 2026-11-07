package com.example.sudoku6x6.controllers;

import com.example.sudoku6x6.views.GameView;
import com.example.sudoku6x6.views.MenuView;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import com.example.sudoku6x6.models.SudokuBoard;
import com.example.sudoku6x6.models.BoardValidator;
import com.example.sudoku6x6.models.SudokuValidator;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import java.io.IOException;

/**
 * Controller of the game screen ({@code game-view.fxml}).
 * <p>
 * It handles the player's input on the 6x6 grid:
 * <ul>
 *     <li>Mouse: clicking a cell selects it.</li>
 *     <li>Keyboard: keys 1 to 6 write a number, Delete or Backspace clear it.
 *     Any other key is ignored.</li>
 * </ul>
 * The look of a cell is changed by adding or removing the CSS style classes
 * {@code fixed}, {@code selected}, {@code error} and {@code hint} from {@code styles.css}.

 *
 * @author Juan Camilo Morales
 * @author Nicolas Palacios
 * @author Juan Camilo Pinzon
 * @version 1.0
 */
public class GameController {

    /** Number of rows and columns of the board. */
    private static final int BOARD_SIZE = 6;

    /** How many hints the player can use in each game. */
    private static final int MAX_HINTS = 3;

   

    /** The board with the numbers of the game (the model). */
    private SudokuBoard board = new SudokuBoard();

    /** Checks the Sudoku rules on the board. */
    private BoardValidator validator = new SudokuValidator(board);

    /** How many hints the player can still use in this game. */
    private int hintsLeft = MAX_HINTS;

    /** The 6x6 grid declared in the FXML. */
    @FXML
    private GridPane boardGrid;

    /** Label used to show messages to the player. */
    @FXML
    private Label messageLabel;

    /** The cells of the grid, stored as [row][column]. */
    private final Label[][] cells = new Label[BOARD_SIZE][BOARD_SIZE];

    /** Cell the player clicked last, or {@code null} if none. */
    private Label selectedCell;

    /**
     * Called automatically after the FXML is loaded.
     * Stores every cell of the grid in the array, so it can be reached by row and column.
     */
    @FXML
    private void initialize() {
        for (Node node : boardGrid.getChildren()) {
            cells[getRow(node)][getColumn(node)] = (Label) node;
        }
    }

    /**
     * Mouse event: highlights the clicked cell and gives it the keyboard focus,
     * so the next key pressed goes to that cell.
     *
     * @param event the click made on a cell
     */
    @FXML
    private void onCellClicked(MouseEvent event) {
        Label cell = (Label) event.getSource();
        if (selectedCell != null) {
            selectedCell.getStyleClass().remove("selected");
        }
        selectedCell = cell;
        cell.getStyleClass().add("selected");
        cell.requestFocus();
    }

    /**
     * Keyboard event: writes 1 to 6 or clears the cell with Delete / Backspace.
     * Starting numbers cannot be changed and any other key is ignored.
     *
     * @param event the key pressed while a cell has the focus
     */
    @FXML
    private void onCellKeyPressed(KeyEvent event) {
        event.consume();

        Label cell = (Label) event.getSource();

        if (cell.getStyleClass().contains("fixed")) {
            return;
        }

        String key = event.getText();

        if (key.equals("1") || key.equals("2") || key.equals("3")
                || key.equals("4") || key.equals("5") || key.equals("6")) {
            editCell(cell, Integer.parseInt(key));
        }

        if (event.getCode() == KeyCode.BACK_SPACE || event.getCode() == KeyCode.DELETE) {
            editCell(cell, 0);
        }
    }

    /**
     * Saves the new value in the model, shows it and checks if it breaks the rules.
     *
     * @param cell  the edited cell
     * @param value the new number, or 0 if the cell was cleared
     */
    private void editCell(Label cell, int value) {
        int row = getRow(cell);
        int column = getColumn(cell);

        board.setNumber(row, column, value);
        showNumber(cell, value);
        cell.getStyleClass().removeAll("hint");

        markErrors();

        if (!validator.isValidBoard()) {
            messageLabel.setText("There are repeated numbers in a row, column or 2x3 block.");
        } else if (value == 0) {
            messageLabel.setText("Cell cleared.");
        } else {
            messageLabel.setText("You wrote " + value + " in row " + (row + 1)
                    + ", column " + (column + 1) + ".");
        }
    }

    /**
     * Checks every cell of the board and puts a red border on the repeated numbers.
     */
    private void markErrors() {
        for (int row = 0; row < BOARD_SIZE; row++) {
            for (int column = 0; column < BOARD_SIZE; column++) {
                Label cell = cells[row][column];
                int number = board.getNumber(row, column);

                cell.getStyleClass().remove("error");

                if (number != 0 && !validator.isValidMove(row, column, number)) {
                    cell.getStyleClass().add("error");
                }
            }
        }
    }

    /**
     * Shows a new board. Numbers different from 0 become fixed starting numbers,
     * and 0 means an empty cell the player can fill.
     *
     * @param numbers a 6x6 array with numbers from 0 to 6
     */
    private void loadBoard(int[][] numbers) {
        for (int row = 0; row < BOARD_SIZE; row++) {
            for (int column = 0; column < BOARD_SIZE; column++) {
                Label cell = cells[row][column];
                showNumber(cell, numbers[row][column]);
                cell.getStyleClass().removeAll("fixed", "selected", "error", "hint");
                if (numbers[row][column] != 0) {
                    cell.getStyleClass().add("fixed");
                }
            }
        }
        selectedCell = null;
    }

    /**
     * Shows a number in a cell. The value 0 leaves the cell empty.
     *
     * @param cell  the cell to change
     * @param value a number from 1 to 6, or 0 to clear the cell
     */
    private void showNumber(Label cell, int value) {
        cell.setText(value == 0 ? "" : String.valueOf(value));
    }

    /**
     * Returns the row of a cell inside the grid.
     *
     * @param cell a cell of the grid
     * @return the row (0 to 5). A cell without a row index is in row 0
     */
    private int getRow(Node cell) {
        Integer row = GridPane.getRowIndex(cell);
        return row == null ? 0 : row;
    }

    /**
     * Returns the column of a cell inside the grid.
     *
     * @param cell a cell of the grid
     * @return the column (0 to 5). A cell without a column index is in column 0
     */
    private int getColumn(Node cell) {
        Integer column = GridPane.getColumnIndex(cell);
        return column == null ? 0 : column;
    }

    /**
     * Asks for confirmation and, if the player accepts, starts a new game.
     */
    @FXML
    private void onNewGameClick() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("New Game");
        alert.setHeaderText("Start a new game?");
        alert.setContentText("The current board will be lost.");
        alert.showAndWait();

        if (alert.getResult() == ButtonType.OK) {
            hintsLeft = MAX_HINTS;
            board.fillWithStartingNumbers();
            loadBoard(board.getNumbers());
            messageLabel.setText("New game started. Good luck!");
        }
    }

    /**
     * Looks for the first empty cell where a number fits, marks it with a green border
     * and tells the player the number. The number is not written: the player decides.
     * The hints are limited, so the board cannot be finished by using only this button.
     */
    @FXML
    private void onHelpClick() {
        if (hintsLeft == 0) {
            messageLabel.setText("You have no hints left.");
            return;
        }

        for (int row = 0; row < BOARD_SIZE; row++) {
            for (int column = 0; column < BOARD_SIZE; column++) {
                if (board.isEmpty(row, column)) {
                    int number = validator.findValidNumber(row, column);
                    if (number != 0) {
                        cells[row][column].getStyleClass().add("hint");
                        hintsLeft--;
                        messageLabel.setText("Hint: you can put " + number + " in row " + (row + 1)
                                + ", column " + (column + 1) + ". Hints left: " + hintsLeft + ".");
                        return;
                    }
                }
            }
        }

        messageLabel.setText("No hint available. Check your numbers.");
    }

    /**
     * Goes back to the main menu.
     *
     * @throws IOException if one of the windows cannot be loaded
     */
    @FXML
    private void onMenuClick() throws IOException {
        MenuView.getInstance().show();
        GameView.getInstance().close();
    }
}
