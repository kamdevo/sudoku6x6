package com.example.sudoku6x6.controllers;

import com.example.sudoku6x6.views.GameView;
import com.example.sudoku6x6.views.MenuView;
import javafx.application.Platform;
import javafx.fxml.FXML;

import java.io.IOException;

/**
 * Controller of the main menu ({@code menu-view.fxml}).
 *
 * @author Juan Camilo Morales
 * @author Nicolas Palacios
 * @author Juan Camilo Pinzon
 * @version 1.0
 */
public class MenuController {

    /**
     * Opens the game window and closes the menu.
     *
     * @throws IOException if one of the windows cannot be loaded
     */
    @FXML
    private void onPlayClick() throws IOException {
        GameView.getInstance().show();
        MenuView.getInstance().close();
    }

    /**
     * Closes the application.
     */
    @FXML
    private void onExitClick() {
        Platform.exit();
    }
}
