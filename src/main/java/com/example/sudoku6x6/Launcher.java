package com.example.sudoku6x6;

import javafx.application.Application;

/**
 * Class with the {@code main} method. It only launches {@link SudokuApplication}.
 *
 * @author Juan Camilo Morales
 * @author Nicolas Palacios
 * @author Juan Camilo Pinzon
 * @version 1.0
 */
public class Launcher {

    /**
     * Launches the JavaFX application.
     *
     * @param args command line arguments
     */
    public static void main(String[] args) {
        Application.launch(SudokuApplication.class, args);
    }
}
