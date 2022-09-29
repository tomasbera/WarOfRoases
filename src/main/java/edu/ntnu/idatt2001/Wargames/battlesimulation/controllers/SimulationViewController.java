package edu.ntnu.idatt2001.Wargames.battlesimulation.controllers;

import edu.ntnu.idatt2001.Wargames.battlesimulation.battle.Army;
import edu.ntnu.idatt2001.Wargames.battlesimulation.battle.Battle;
import edu.ntnu.idatt2001.Wargames.battlesimulation.battle.Terrain;
import edu.ntnu.idatt2001.Wargames.battlesimulation.maintnance.SwitchScene;
import edu.ntnu.idatt2001.Wargames.battlesimulation.specificunits.Unit;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.util.Duration;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * class used for battle simulations between to armies
 * <p>
 * user can choose between different terrains
 * and other possibilities
 * </p>
 * This is the controller for {@link SwitchScene} SimulatorView.fxml
 *
 * @author Tomas Beranek
 */
public class SimulationViewController {

    //winning Army Tableview
    @FXML
    private TableView<Unit> winningArmyTable;
    @FXML
    private TableColumn<Unit, String> winningArmyUT;
    @FXML
    private TableColumn<Unit, String> winningArmyUN;
    @FXML
    private TableColumn<Unit, Integer> winningArmyH;

    //army One TableView
    @FXML
    private ListView<String> ArmyOneList;
    private List<String> armyOneArray;

    //army Two TableView
    @FXML
    private ListView<String> ArmyTwoList;
    private List<String> armyTwoArray;

    //picture layout
    @FXML
    private Text layoutText;
    @FXML
    private ImageView layoutImageView;
    @FXML
    private ChoiceBox<String> warGamesTerrainInput;
    @FXML
    private String chosenFormat;

    //terrain
    private int chosenTerrain;

    //TextInputs
    @FXML
    private Text scoreText;
    @FXML
    private Text armyOneText;
    @FXML
    private Text armyTwoText;
    @FXML
    private Button liveSimulationButtonText;
    @FXML
    private Button simulationButtonText;
    @FXML
    private Text winningArmyText;
    private int scoreArmyOne;
    private int scoreArmyTwo;

    //battle
    private Battle battle;
    private Army winningArmy;
    private boolean changeTextNumberOfSimulation;

    //buttons
    @FXML
    private Button toNewArmyButton;
    @FXML
    private Button resetButton;
    @FXML
    private Button toMenuButton;
    @FXML
    private Button toSimulateButton;
    @FXML
    private Button toArmyDetailButton;

    /**
     * method used for initializing variables and other GUI elements when needed
     * implements items to choice box
     * updates armies to tables
     * the button to this page turns blue
     */
    public void initialize() {

        warGamesTerrainInput.getItems().add(0, "Hill");
        warGamesTerrainInput.getItems().add(1, "Forest");
        warGamesTerrainInput.getItems().add(2, "Plains");

        updateArmyOneTable(SimulationSingleton.getInstance().getArmyOne());
        updateArmyTwoTable(SimulationSingleton.getInstance().getArmyTwo());

        toSimulateButton.setStyle("-fx-background-color: #6495ED;" + "-fx-border-color: black;");
        changeTextNumberOfSimulation = true;
    }

    /**
     * method that sets a specific image on the screen when chosen by user
     * {@linkhttps://gitlab.stud.idi.ntnu.no/G1-06/idatt-1002-2022-1-06/-/blob/final-prod/finalprod/src/main/java/edu/ntnu/idatt1002/g106/handballapp/finalprod/controller/SetUpTournamentController.java}
     * <p>
     * method from earlier project
     * this method was created by my group in different project
     * </p>
     */
    @FXML
    public void setImage() {
        warGamesTerrainInput.getSelectionModel().selectedIndexProperty().addListener((observableValue, number, number2) -> {
            chosenFormat = warGamesTerrainInput.getItems().get((Integer) number2);
            if (chosenFormat.equals("Hill")) {
                layoutImageView.setImage(getImageByName("hill"));
                layoutText.setText("Hill");
                chosenTerrain = Terrain.HILL.getTerrainNum();
            }
            if (chosenFormat.equals("Plains")) {
                layoutImageView.setImage(getImageByName("plains"));
                layoutText.setText("Plains");
                chosenTerrain = Terrain.PLAINS.getTerrainNum();
            }
            if (chosenFormat.equals("Forest")) {
                layoutImageView.setImage(getImageByName("forest"));
                layoutText.setText("Forest");
                chosenTerrain = Terrain.FOREST.getTerrainNum();
            }
        });
    }

    /**
     * method for updating tableview based on winning army
     */
    @FXML
    public void updateWinner() {
        //winning army table update
        winningArmyUT.setCellValueFactory(new PropertyValueFactory<Unit, String>("name"));
        winningArmyUN.setCellValueFactory(new PropertyValueFactory<Unit, String>("name"));
        winningArmyH.setCellValueFactory(new PropertyValueFactory<Unit, Integer>("health"));

        winningArmyTable.setItems(FXCollections.observableArrayList(
                winningArmy.getUnits().stream().toList()));

        winningArmyText.setText(winningArmy.getName());
        winningArmyTable.refresh();

    }

    /**
     * method that runs the simulation of the battle
     * and updates the lists and tableview
     */
    @FXML
    public void liveSimulate() {
        buttonStatus(true);

        if (changeTextNumberOfSimulation) {
            liveSimulationButtonText.setText("Reset and Simulate");
            simulationButtonText.setText("Reset and Simulate");
            winningArmyText.setText("Previous Winner");
        }

        if (chosenTerrain == 0) {
            chosenTerrain = Terrain.HILL.getTerrainNum();
            layoutText.setText("Hill");
            layoutImageView.setImage(getImageByName("hill"));
        }

        List<Army> armies = resetMethod();
        battle = new Battle(armies.get(0), armies.get(1));

        Timeline tm = new Timeline();
        tm.setCycleCount(Timeline.INDEFINITE);
        tm.getKeyFrames().add(new KeyFrame(Duration.millis(10), event -> {
            if (armies.get(0).hasUnits() && armies.get(1).hasUnits()) {
                battle.randomArmyAttackerDefender();
                battle.simulateAttack(chosenTerrain);
                updateAllTables(armies.get(0), armies.get(1));
            } else {
                tm.stop();
                if (armies.get(0).hasUnits()) winningArmy = armies.get(0);
                else winningArmy = armies.get(1);

                if (winningArmy == armies.get(0)) scoreArmyOne++;
                else scoreArmyTwo++;

                buttonStatus(false);
                scoreText.setText(scoreArmyOne + "-" + scoreArmyTwo);
                updateWinner();
            }
        }));
        tm.playFromStart();
    }

    /**
     * method for fast simulations
     */
    @FXML
    public void simulate() {
        if (changeTextNumberOfSimulation) {
            simulationButtonText.setText("Reset and Simulate");
            liveSimulationButtonText.setText("Reset and Simulate");
            winningArmyText.setText("Previous Winner");
        }

        if (chosenTerrain == 0) {
            chosenTerrain = Terrain.HILL.getTerrainNum();
            layoutText.setText("Hill");
            layoutImageView.setImage(getImageByName("hill"));
        }

        List<Army> armies = resetMethod();
        battle = new Battle(armies.get(0), armies.get(1));
        winningArmy = battle.simulate(chosenTerrain);

        if (armies.get(0).hasUnits()) winningArmy = armies.get(0);
        else winningArmy = armies.get(1);

        if (winningArmy == armies.get(0)) scoreArmyOne++;
        else scoreArmyTwo++;

        scoreText.setText(scoreArmyOne + "-" + scoreArmyTwo);

        updateAllTables(armies.get(0), armies.get(1));
        updateWinner();
    }

    /**
     * method that restarts the simulation, and resets the armies
     * <p>
     * here i choose to to not reset the number of wins of the different armies
     * and a also display the last winning army
     * </p>
     *
     * @return a list of rest armies
     */
    @FXML
    public List<Army> resetMethod() {
        List<Army> armies = new ArrayList<>();
        List<Unit> armyOneUnits = new ArrayList<>();
        List<Unit> armyTwoUnits = new ArrayList<>();

        SimulationSingleton.getInstance().getArmyOne().getUnits()
                .forEach(unit -> armyOneUnits.add(unit.deepCopyUnit()));
        SimulationSingleton.getInstance().getArmyTwo().getUnits()
                .forEach(unit -> armyTwoUnits.add(unit.deepCopyUnit()));

        Army armyOne = new Army(SimulationSingleton.getInstance().getArmyOne().getName());
        armyOne.addAll(armyOneUnits);
        Army armyTwo = new Army(SimulationSingleton.getInstance().getArmyTwo().getName());
        armyTwo.addAll(armyTwoUnits);

        armies.add(armyOne);
        armies.add(armyTwo);

        updateAllTables(armyOne, armyTwo);

        return armies;
    }

    /**
     * updates listview of army one based on unit types
     *
     * @param thisArmy army for display
     */
    @FXML
    public void updateArmyOneTable(Army thisArmy) {
        if (SimulationSingleton.getInstance().getArmyOne().getName().isEmpty()) armyOneText.setText("Army not chosen");
        else armyOneText.setText(SimulationSingleton.getInstance().getArmyOne().getName());


        unitToStringMethod(thisArmy, 1);
        ArmyOneList.setItems(FXCollections.observableArrayList(armyOneArray.stream().toList()));
    }

    /**
     * updates listview of army two based on unit types
     *
     * @param thisArmy army for display
     */
    @FXML
    public void updateArmyTwoTable(Army thisArmy) {
        if (SimulationSingleton.getInstance().getArmyTwo().getName().isEmpty()) armyTwoText.setText("Army not chosen");
        else armyTwoText.setText(SimulationSingleton.getInstance().getArmyTwo().getName());

        unitToStringMethod(thisArmy, 2);
        ArmyTwoList.setItems(FXCollections.observableArrayList(armyTwoArray.stream().toList()));
    }

    /**
     * method for updating both tableviews
     *
     * @param armyOne left army table
     * @param armyTwo right army table
     */
    public void updateAllTables(Army armyOne, Army armyTwo) {
        updateArmyOneTable(armyOne);
        updateArmyTwoTable(armyTwo);
    }

    /**
     * method that transfers armies to string based on unit types
     *
     * @param thisArmy army that is to be converted
     * @param armyNum  army number, ether army one or two
     */
    public void unitToStringMethod(Army thisArmy, int armyNum) {
        if (armyNum == 1) {
            armyOneArray = new ArrayList<>();
            armyOneArray.add("Total Units:" + thisArmy.getUnits().size());
            armyOneArray.add("Infantry Unit: " + thisArmy.getInfantryUnits().size());
            armyOneArray.add("Range Unit: " + thisArmy.getRangeUnits().size());
            armyOneArray.add("Cavalry Unit: " + thisArmy.getCavalryUnits().size());
            armyOneArray.add("Commander Unit: " + thisArmy.getCommanderUnits().size());

        } else {
            armyTwoArray = new ArrayList<>();
            armyTwoArray.add("Total Units:" + thisArmy.getUnits().size());
            armyTwoArray.add("Infantry Unit: " + thisArmy.getInfantryUnits().size());
            armyTwoArray.add("Range Unit: " + thisArmy.getRangeUnits().size());
            armyTwoArray.add("Cavalry Unit: " + thisArmy.getCavalryUnits().size());
            armyTwoArray.add("Commander Unit: " + thisArmy.getCommanderUnits().size());
        }
    }

    /**
     * method for disabling buttons
     * <p>
     * these buttons could crash the code if pressed during simulation
     * it was therefore important to disable these
     * </p>
     * <p>
     *
     * @param bool boolean variable, ether true or false
     * @website http://www.learningaboutelectronics.com/Articles/How-to-disable-button-after-click-in-JavaFX.php
     * Website used for understanding, no direct copyright from web.
     */
    public void buttonStatus(Boolean bool) {
        liveSimulationButtonText.setDisable(bool);
        toNewArmyButton.setDisable(bool);
        toMenuButton.setDisable(bool);
        toArmyDetailButton.setDisable(bool);
        toSimulateButton.setDisable(bool);
        resetButton.setDisable(bool);
        simulationButtonText.setDisable(bool);
        warGamesTerrainInput.setDisable(bool);
    }


    /**
     * Method sets an image by the image name
     * The method requires that all images is located in the resources under {@code TerrainPictures} and that the image is jpg
     *
     * @param imageName The name of the image
     * @return The image if found
     */
    private Image getImageByName(String imageName) {
        return new Image("/terrainpictures/" + imageName + ".jpg");
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
     * method that takes the user to army details page
     *
     * @param event any button event
     * @throws IOException if path not found
     */
    @FXML
    public void toArmyDetails(ActionEvent event) throws IOException {
        SwitchScene.switchScene("ArmyDetailView", event);
    }
}
