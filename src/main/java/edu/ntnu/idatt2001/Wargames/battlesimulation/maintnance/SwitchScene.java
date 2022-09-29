package edu.ntnu.idatt2001.Wargames.battlesimulation.maintnance;

import javafx.event.Event;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

/**
 * static class for switching between edu.ntnu.idatt2001.Wargames.battlesimulation.controllers
 *
 * @author Tomas Beranek
 */
public class SwitchScene {

    /**
     * method that switches between scenes with the same information as starting page
     *
     * @param location the new page
     * @param event    any button event
     * @throws IOException when path not found
     * @website https://gitlab.stud.idi.ntnu.no/G1-06/idatt-1002-2022-1-06/-/tree/final-prod
     * <p>
     * method written by me from older project
     * </p>
     */
    public static void switchScene(String location, Event event) throws IOException {
        Parent viewPage = FXMLLoader.load(Objects.requireNonNull(SwitchScene.class.getResource("/viewclasses/"
                + location + ".fxml")));

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.getScene().setRoot(viewPage);
        stage.show();
    }
}
