package edu.ntnu.idatt2001.Wargames.battlesimulation.controllers;

import edu.ntnu.idatt2001.Wargames.battlesimulation.battle.Army;
import edu.ntnu.idatt2001.Wargames.battlesimulation.battle.ArmyFiles;
import edu.ntnu.idatt2001.Wargames.battlesimulation.maintnance.PopupBoxes;
import edu.ntnu.idatt2001.Wargames.battlesimulation.maintnance.SwitchScene;
import edu.ntnu.idatt2001.Wargames.battlesimulation.specificunits.Unit;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * this is the class that allows user to get files for local files, and transfer them to armies
 * These armies can then be used to simulate a battle
 * This is the controller for {@link SwitchScene} ArmyDetailsView.fxml
 *
 * @author Tomas Beranek
 */
public class ArmyDetailsViewController {

    //Table View
    @FXML
    private TableView<Unit> armyOneTableView;
    @FXML
    private TableView<Unit> armyTwoTableView;

    @FXML
    private TableColumn<Unit, String> armyOneUT;
    @FXML
    private TableColumn<Unit, String> armyOneUN;
    @FXML
    private TableColumn<Unit, Integer> armyOneH;
    @FXML
    private TableColumn<Unit, Integer> armyOneA;
    @FXML
    private TableColumn<Unit, Integer> armyOneAt;

    @FXML
    private TableColumn<Unit, String> armyTwoUT;
    @FXML
    private TableColumn<Unit, String> armyTwoUN;
    @FXML
    private TableColumn<Unit, Integer> armyTwoH;
    @FXML
    private TableColumn<Unit, Integer> armyTwoA;
    @FXML
    private TableColumn<Unit, Integer> armyTwoAt;

    //army variables
    private Army armyOne;
    private Army armyTwo;

    //buttons
    @FXML
    private Button toArmyDetailsButton;

    //text fields or text boxes
    @FXML
    private Text armyOneText;
    @FXML
    private Text armyTwoText;


    /**
     * method used for initializing variables and other GUI elements when needed
     * the button to this page turns blue
     */
    public void initialize() {
        toArmyDetailsButton.setStyle("-fx-background-color: #6495ED;" + "-fx-border-color: black;");
    }

    /**
     * method used for choosing a local csv file, and store it as an army,
     * if possible
     */
    public void getFileArmyOne() {
        List<Unit> armyList = new ArrayList<>();
        ArmyFiles armyFiles = new ArmyFiles();
        String armyOneFilePath = armyFiles.getFilePath();

        if (armyOneFilePath == null) {
            PopupBoxes.alertError("File Doesnt exist");
            return;
        }

        try {
            SimulationSingleton.getInstance().setArmyOne(armyFiles.readFromCSV(armyOneFilePath));
            SimulationSingleton.getInstance().getArmyOne().getUnits()
                    .forEach(u -> armyList.add(u.deepCopyUnit()));

            armyOne = new Army(SimulationSingleton.getInstance().getArmyOne().getName(), armyList);
            PopupBoxes.confirmBox("Army File Path\n" + armyOneFilePath);
            updateArmyOneTable(armyOne);
        } catch (Exception e) {
            PopupBoxes.alertError(e.getMessage());
        }
    }

    /**
     * method used for choosing a local csv file, and store it as an army,
     * if possible
     */
    @FXML
    public void getFileArmyTwo() {
        List<Unit> armyList = new ArrayList<>();
        ArmyFiles armyFiles = new ArmyFiles();
        String armyTwoFilePath = armyFiles.getFilePath();

        if (armyTwoFilePath == null) {
            PopupBoxes.alertError("File Doesnt exist");
            return;
        }

        try {
            SimulationSingleton.getInstance().setArmyTwo(armyFiles.readFromCSV(armyTwoFilePath));
            SimulationSingleton.getInstance().getArmyTwo().getUnits()
                    .forEach(u -> armyList.add(u.deepCopyUnit()));

            armyTwo = new Army(SimulationSingleton.getInstance().getArmyTwo().getName(), armyList);
            PopupBoxes.confirmBox("Army File Path\n" + armyTwoFilePath);
            updateArmyTwoTable(armyTwo);
        } catch (Exception e) {
            PopupBoxes.alertError(e.getMessage());
        }
    }

    /**
     * This method updates an army one tableview
     *
     * @param thisArmy army that will be displayed
     */
    @FXML
    public void updateArmyOneTable(Army thisArmy) {
        updateTableView(thisArmy, armyOneUT, armyOneUN, armyOneH, armyOneText, armyOneTableView, armyOneA, armyOneAt);
    }

    /**
     * This method updates an army two tableview
     *
     * @param thisArmy army that will be displayed
     */
    @FXML
    public void updateArmyTwoTable(Army thisArmy) {
        updateTableView(thisArmy, armyTwoUT, armyTwoUN, armyTwoH, armyTwoText, armyTwoTableView, armyTwoA, armyTwoAt);
    }

    /**
     * method for updating a tableview based on multiple variables
     *
     * @param thisArmy      army that is going to tableview
     * @param armyUT        army Unit Type
     * @param armyUN        army Unit Name
     * @param armyH         army Unit Health
     * @param armyText      army name
     * @param armyTableView the tableview to be updated
     */

    private void updateTableView(Army thisArmy, TableColumn<Unit, String> armyUT, TableColumn<Unit, String> armyUN,
                                 TableColumn<Unit, Integer> armyH, Text armyText, TableView<Unit> armyTableView,
                                 TableColumn<Unit, Integer> armyA, TableColumn<Unit, Integer> armyAt) {

        armyUT.setCellValueFactory(new PropertyValueFactory<Unit, String>("unitType"));
        armyUN.setCellValueFactory(new PropertyValueFactory<Unit, String>("name"));
        armyH.setCellValueFactory(new PropertyValueFactory<Unit, Integer>("health"));
        armyA.setCellValueFactory(new PropertyValueFactory<Unit, Integer>("armor"));
        armyAt.setCellValueFactory(new PropertyValueFactory<Unit, Integer>("attack"));

        armyText.setText(thisArmy.getName());
        armyTableView.setItems(FXCollections.observableArrayList(
                thisArmy.getUnits().stream().toList()));
        armyTableView.refresh();
    }

    /**
     * method that takes the user to simulation page
     *
     * @param event any button event
     * @throws IOException if path not found
     */
    @FXML
    public void toSimulatorPage(ActionEvent event) throws IOException {
        if (armyOne == null || armyTwo == null) {
            PopupBoxes.alertError("You need to have two armies before simulation");
            return;
        }
        SwitchScene.switchScene("SimulatorView", event);
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
     * method that takes the user to main page
     *
     * @param event any button event
     * @throws IOException if path not found
     */
    @FXML
    public void toMainPage(ActionEvent event) throws IOException {
        SwitchScene.switchScene("MainPageView", event);
    }
}
