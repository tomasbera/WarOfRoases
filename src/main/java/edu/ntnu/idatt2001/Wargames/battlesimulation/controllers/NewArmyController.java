package edu.ntnu.idatt2001.Wargames.battlesimulation.controllers;

import edu.ntnu.idatt2001.Wargames.battlesimulation.battle.Army;
import edu.ntnu.idatt2001.Wargames.battlesimulation.battle.ArmyFiles;
import edu.ntnu.idatt2001.Wargames.battlesimulation.battle.UnitFactory;
import edu.ntnu.idatt2001.Wargames.battlesimulation.maintnance.PopupBoxes;
import edu.ntnu.idatt2001.Wargames.battlesimulation.maintnance.SwitchScene;
import edu.ntnu.idatt2001.Wargames.battlesimulation.specificunits.Unit;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * class for creation of armies
 * <p>
 * this class allow for file conversion,
 * and also gives the user full control of how a army will look
 * which and how many units it consist of
 * </p>
 * This is the controller for {@link SwitchScene} NewArmyView.fxml
 *
 * @author Tomas Beranek
 */
public class NewArmyController {

    //private variables
    private Army newArmy;
    private List<String> stringArray;
    @FXML
    private Button newArmyButton;

    //tableView
    @FXML
    private TableView<Unit> newArmyTable;
    @FXML
    private TableColumn<Unit, String> UT;
    @FXML
    private TableColumn<Unit, String> UN;
    @FXML
    private TableColumn<Unit, Integer> UH;

    //ListView
    @FXML
    private ListView<String> armyListView;

    //TextFields
    @FXML
    private Text armyNameTitle;
    @FXML
    private ChoiceBox<String> unitTypeBox;
    @FXML
    private TextField unitNumberTXT;
    @FXML
    private TextField unitNameTXT;
    @FXML
    private TextField armyNameTXT;
    @FXML
    private TextField unitHealthTXT;

    /**
     * method used for initializing variables and other GUI elements when needed
     * method that loads unit type box with different unit types choices.
     * the button to this page turns blue
     */
    public void initialize() {
        unitTypeBox.getItems().add(0, "InfantryUnit");
        unitTypeBox.getItems().add(1, "RangedUnit");
        unitTypeBox.getItems().add(2, "CavalryUnit");
        unitTypeBox.getItems().add(3, "CommanderUnit");

        newArmyButton.setStyle("-fx-background-color: #6495ED;" + "-fx-border-color: black;");
    }

    /**
     * button that creates an army based on name from army name text field
     */
    @FXML
    public void createArmy() {
        try {
            newArmy = new Army(armyNameTXT.getText());
            armyNameTitle.setText(armyNameTXT.getText());
            PopupBoxes.confirmBox("Your army has been created");
            updateArmyLists(newArmy);
        } catch (Exception e) {
            PopupBoxes.alertError(e.getMessage());
        }
    }

    /**
     * this method adds all units created to new army
     */
    @FXML
    public void addUnitsToArmy() {
        if (newArmy == null) {
            PopupBoxes.alertError("No army created");
            return;
        }
        if (checkInput(unitHealthTXT.getText()) && checkInput(unitNumberTXT.getText())) {

            try {
                UnitFactory uf = new UnitFactory();
                List<Unit> unitList;

                unitList = uf.newNumberOfUnits(unitTypeBox.getValue(), unitNameTXT.getText(),
                        Integer.parseInt(unitHealthTXT.getText()), Integer.parseInt(unitNumberTXT.getText()));

                newArmy.addAll(unitList);
                updateArmyLists(newArmy);

                PopupBoxes.confirmBox("Your unit are now added to Army");

            } catch (Exception e) {
                PopupBoxes.alertError(e.getMessage());
            }
        }
    }

    /**
     * this method selects a unit from list, and removes it form army
     */
    @FXML
    public void deleteUnit() {
        Unit selectedUnit = newArmyTable.getSelectionModel().getSelectedItem();

        if (selectedUnit == null) {
            PopupBoxes.alertError("No Unit Selected");
            return;
        }

        newArmyTable.getItems().remove(selectedUnit);
        newArmy.remove(selectedUnit);
        updateArmyLists(newArmy);
    }

    /**
     * This method convert a unit to file if yes button pressed.
     */
    @FXML
    public void convertToFile() {
        if (newArmy == null) {
            PopupBoxes.alertError("No army for creation");
            return;
        }
        try {
            if (PopupBoxes.yesNoBox("Confirm", "Are you sure you want to create a file")) {
                ArmyFiles.makeCSVFile(newArmy);
                PopupBoxes.confirmBox("File " + newArmy.getName() + " was created");
            }
        } catch (Exception e) {
            PopupBoxes.alertError(e.getMessage());
        }
    }

    /**
     * method that updates a listview that hold string information of number of unit by type
     */
    @FXML
    public void updateListView() {
        armyListView.setItems(FXCollections.observableArrayList(stringArray.stream().toList()));
    }

    /**
     * method that updates new army table view, with all units
     */
    @FXML
    public void updateTable() {
        UT.setCellValueFactory(new PropertyValueFactory<Unit, String>("unitType"));
        UN.setCellValueFactory(new PropertyValueFactory<Unit, String>("name"));
        UH.setCellValueFactory(new PropertyValueFactory<Unit, Integer>("health"));

        newArmyTable.setItems(FXCollections.observableArrayList(
                newArmy.getUnits().stream().toList()));
        newArmyTable.refresh();
    }

    /**
     * method for updating all tableviews and all listviews
     *
     * @param newArmy army for updating
     */
    public void updateArmyLists(Army newArmy) {
        updateTable();
        unitToString(newArmy);
        updateListView();
    }

    /**
     * method that takes the user to main page
     *
     * @param event any button event
     * @throws IOException if path not found
     */
    @FXML
    public void toMenu(ActionEvent event) throws IOException {
        SwitchScene.switchScene("MainPageView", event);
    }

    /**
     * method that takes the user to army details page
     *
     * @param event any button event
     * @throws IOException if path not found
     */
    @FXML
    public void toArmyDetail(ActionEvent event) throws IOException {
        SwitchScene.switchScene("ArmyDetailView", event);
    }

    /**
     * method that takes the user to simulation page
     *
     * @param event any button event
     * @throws IOException if path not found
     */
    @FXML
    public void toSimulate(ActionEvent event) throws IOException {
        if (SimulationSingleton.getInstance().getArmyOne() == null || SimulationSingleton.getInstance().getArmyTwo() == null) {
            PopupBoxes.alertError("You need to have Two Armies");
            return;
        }
        SwitchScene.switchScene("SimulatorView", event);
    }

    /**
     * Method that convert unit types and number of types to a string
     *
     * @param thisArmy army for conversion
     */
    public void unitToString(Army thisArmy) {
        stringArray = new ArrayList<>();
        stringArray.add("Infantry Unit: " + thisArmy.getInfantryUnits().size());
        stringArray.add("Range Unit: " + thisArmy.getRangeUnits().size());
        stringArray.add("Cavalry Unit: " + thisArmy.getCavalryUnits().size());
        stringArray.add("Commander Unit: " + thisArmy.getCommanderUnits().size());
    }

    /**
     * checks if string consist of numbers
     *
     * @param lines string to be checked
     * @return true or false depending on outcome
     */
    public boolean checkInput(String lines) {
        String chekIfInt = "[0-9]+";
        Pattern pt = Pattern.compile(chekIfInt);
        Matcher mt = pt.matcher(lines);

        if (!mt.matches()) {
            PopupBoxes.alertError("Input is not a Number");
            return false;
        }
        return true;
    }
}
