package com.example.sudoku6x6;

import com.example.sudoku6x6.views.MenuView;
import javafx.application.Application;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Entry point of the JavaFX application. It opens the main menu.
 *
 * @author Juan Camilo Morales
 * @author Nicolas Palacios
 * @author Juan Camilo Pinzon
 * @version 1.0
 */
public class SudokuApplication extends Application {

    /**
     * Starts the application by showing the menu window.
     * The default stage given by JavaFX is not used because
     * {@link MenuView} is its own window.
     *
     * @param primaryStage the default stage created by JavaFX
     * @throws IOException if the menu cannot be loaded
     */
    @Override
    public void start(Stage primaryStage) throws IOException {
        MenuView.getInstance().show();
    }
}
