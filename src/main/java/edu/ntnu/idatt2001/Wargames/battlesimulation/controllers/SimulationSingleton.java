package edu.ntnu.idatt2001.Wargames.battlesimulation.controllers;

import edu.ntnu.idatt2001.Wargames.battlesimulation.battle.Army;
import javafx.stage.Stage;

/**
 * Singleton class that holds all static variables used between different edu.ntnu.idatt2001.Wargames.battlesimulation.controllers
 *
 * @author Tomas Beranek
 */
public class SimulationSingleton {

    //early initialized
    private static final SimulationSingleton sing = new SimulationSingleton();

    private Army armyOne;
    private Army armyTwo;
    private Stage stage;


    /**
     * private constructor for singleton classes
     */
    private SimulationSingleton() {
    }

    /**
     * method for getting instance of singleton, this allows to use the same singleton multiple places
     *
     * @return singleton instance
     */
    public static SimulationSingleton getInstance() {
        return sing;
    }

    public Army getArmyOne() {
        return armyOne;
    }

    public void setArmyOne(Army armyOne) {
        this.armyOne = armyOne;
    }

    public Army getArmyTwo() {
        return armyTwo;
    }

    public void setArmyTwo(Army armyTwo) {
        this.armyTwo = armyTwo;
    }

    public Stage getStage() {
        return stage;
    }
}
