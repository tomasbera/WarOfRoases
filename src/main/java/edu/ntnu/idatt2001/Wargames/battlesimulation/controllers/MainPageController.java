package edu.ntnu.idatt2001.Wargames.battlesimulation.controllers;

import edu.ntnu.idatt2001.Wargames.battlesimulation.maintnance.PopupBoxes;
import edu.ntnu.idatt2001.Wargames.battlesimulation.maintnance.SwitchScene;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import java.io.IOException;

/**
 * This is the main page class, that shows the user the different opportunities of this program
 * <p>
 * it consist of only button that takes the user to different pages
 * </p>
 * This is the controller for {@link SwitchScene} MainPageView.fxml
 *
 * @author Tomas Beranek
 */
public class MainPageController {

    /**
     * method used for initializing variables and other GUI elements when needed
     */
    public void initialize() {
    }

    /**
     * method that takes the user to tutorial page
     *
     * @param event any button event
     * @throws IOException if path not found
     */
    @FXML
    public void toTutorialPage(ActionEvent event) throws IOException {
        SwitchScene.switchScene("TerrainTutorialView", event);
    }

    /**
     * method that takes the user to army creation page
     *
     * @param event any button event
     * @throws IOException if path not found
     */
    @FXML
    public void toNewArmy(ActionEvent event) throws IOException {
        SwitchScene.switchScene("NewArmyView", event);
    }

    /**
     * method that takes the user to army detail page
     *
     * @param event any button event
     * @throws IOException if path not found
     */
    @FXML
    public void toArmyDetails(ActionEvent event) throws IOException {
        SwitchScene.switchScene("ArmyDetailView", event);
    }

    /**
     * method that takes the user to simulation page
     *
     * @param event any button event
     * @throws IOException if path not found
     */
    @FXML
    public void toSimulation(ActionEvent event) throws IOException {
        if (SimulationSingleton.getInstance().getArmyOne() == null || SimulationSingleton.getInstance().getArmyTwo() == null) {
            PopupBoxes.alertError("You have no armies to Simulate");
            return;
        }
        SwitchScene.switchScene("SimulatorView", event);
    }

    /**
     * method that exits the program
     * <p>
     * can be used for saving data in the future
     * </p>
     */
    @FXML
    public void exitButton() {
        System.exit(0);
    }
}
