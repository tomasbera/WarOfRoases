package edu.ntnu.idatt2001.Wargames.battlesimulation.controllers;

import edu.ntnu.idatt2001.Wargames.battlesimulation.maintnance.SwitchScene;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import java.io.IOException;

/**
 * class that displays the user to how terrains work, and have possibility to go to next tutorial
 * This is the controller for {@link SwitchScene} TerrainTutorialView.fxml
 *
 * @author Tomas Beranek
 */
public class TerrainTutorialController {

    /**
     * method used for initializing variables and other GUI elements when needed
     */
    public void initialize() {
    }

    /**
     * method that takes the user to next tutorial page
     *
     * @param event any button event
     * @throws IOException if path not found
     */
    @FXML
    public void toNextPage(ActionEvent event) throws IOException {
        SwitchScene.switchScene("UnitTutorial", event);
    }
}
