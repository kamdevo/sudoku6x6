package com.example.sudoku6x6.views;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Window that contains the main menu.
 * <p>
 * It loads {@code menu-view.fxml}. Only one instance of this window
 * is ever created (Singleton pattern).
 *
 * @author Juan Camilo Morales
 * @author Nicolas Palacios
 * @author Juan Camilo Pinzon
 * @version 1.0
 */
public class MenuView extends Stage {

    /**
     * Creates the menu window by loading its FXML file.
     *
     * @throws IOException if the FXML file cannot be loaded
     */
    public MenuView() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(
                getClass().getResource("/com/example/sudoku6x6/menu-view.fxml")
        );
        Parent root = fxmlLoader.load();

        Scene scene = new Scene(root);

        setTitle("Sudoku 6x6 - Welcome");
        setScene(scene);
        setResizable(false);
    }

    /**
     * Returns the single instance of the menu window,
     * creating it the first time it is requested.
     *
     * @return the menu view
     * @throws IOException if the view has to be created and its FXML file cannot be loaded
     */
    public static MenuView getInstance() throws IOException {
        if (MenuViewHolder.INSTANCE == null) {
            MenuViewHolder.INSTANCE = new MenuView();
        }
        return MenuViewHolder.INSTANCE;
    }

    /**
     * Holds the single instance of {@link MenuView}.
     */
    private static class MenuViewHolder {

        /** The single instance of the view, or {@code null} if it has not been created yet. */
        private static MenuView INSTANCE = null;
    }
}
