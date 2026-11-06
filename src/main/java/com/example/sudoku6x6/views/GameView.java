package com.example.sudoku6x6.views;

import com.example.sudoku6x6.controllers.GameController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Window that contains the game screen.
 * <p>
 * It loads {@code game-view.fxml} and keeps a reference to its controller,
 * so other classes can pass data to it. Only one instance of this window
 * is ever created (Singleton pattern).
 *
 * @author Juan Camilo Morales
 * @author Nicolas Palacios
 * @author Juan Camilo Pinzon
 * @version 1.0
 */
public class GameView extends Stage {

    /** Controller created by the FXML loader for this view. */
    private final GameController controller;

    /**
     * Creates the game window by loading its FXML file.
     *
     * @throws IOException if the FXML file cannot be loaded
     */
    public GameView() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(
                getClass().getResource("/com/example/sudoku6x6/game-view.fxml")
        );
        Parent root = fxmlLoader.load();
        controller = fxmlLoader.getController();

        Scene scene = new Scene(root);

        setTitle("Sudoku 6x6 - Game");
        setScene(scene);
        setResizable(false);
    }

    /**
     * Returns the controller of this view, so other controllers can pass data to it.
     *
     * @return the game controller
     */
    public GameController getController() {
        return controller;
    }

    /**
     * Returns the single instance of the game window,
     * creating it the first time it is requested.
     *
     * @return the game view
     * @throws IOException if the view has to be created and its FXML file cannot be loaded
     */
    public static GameView getInstance() throws IOException {
        if (GameViewHolder.INSTANCE == null) {
            GameViewHolder.INSTANCE = new GameView();
        }
        return GameViewHolder.INSTANCE;
    }

    /**
     * Holds the single instance of {@link GameView}.
     */
    private static class GameViewHolder {

        /** The single instance of the view, or {@code null} if it has not been created yet. */
        private static GameView INSTANCE = null;
    }
}
