package edu.ntnu.idatt2001.Wargames.battlesimulation.maintnance;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * class that starts the application but extends application
 *
 * @author Tomas Beranek
 */
public class WarGamesGUI extends Application {

    /**
     * main method that starts the application
     *
     * @param args argument
     */
    public static void main(String[] args) {
        launch(args);
    }

    /**
     * stating method with starting screen
     *
     * @throws Exception when path not found
     */
    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/viewclasses/MainPageView.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setMinWidth(820);
        stage.setMinHeight(640);
        stage.setTitle("WarGames");
        stage.setMaximized(true);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * {@inheritDoc}
     *
     * @throws Exception
     */
    @Override
    public void init() throws Exception {
        super.init();
    }

    /**
     * not in use
     * <p>
     * can be used for future development
     * </p>
     * {@inheritDoc}
     *
     * @throws Exception
     */
    @Override
    public void stop() throws Exception {
        super.stop();
    }
}
