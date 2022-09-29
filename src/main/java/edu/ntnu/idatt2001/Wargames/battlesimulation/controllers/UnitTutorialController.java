package edu.ntnu.idatt2001.Wargames.battlesimulation.controllers;

import edu.ntnu.idatt2001.Wargames.battlesimulation.maintnance.SwitchScene;
import javafx.event.ActionEvent;

import java.io.IOException;

/**
 * unit tutorial class that inform the user of different units'
 * user can go to main page
 * This is the controller for {@link SwitchScene} UnitTutorial.fxml
 *
 * @author Tomas Beranek
 */
public class UnitTutorialController {

    /**
     * method used for initializing variables and other GUI elements when needed
     */
    public void initialize() {
    }

    /**
     * method that takes the user to main page
     *
     * @param event any button event
     * @throws IOException if path not found
     */
    public void toMainPage(ActionEvent event) throws IOException {
        SwitchScene.switchScene("MainPageView", event);
    }
}
