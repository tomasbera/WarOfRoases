package edu.ntnu.idatt2001.Wargames.battlesimulation.battle;

import edu.ntnu.idatt2001.Wargames.battlesimulation.specificunits.Unit;

import java.util.Random;

/**
 * this class represents the simulation of a battle
 * the battle is going to be between two armies from the army class
 * this class consists of different methods and variables
 *
 * @author Tomas Beranek
 */
public class Battle {

    private final Army armyOne;
    private final Army armyTwo;
    private Army attackingArmy;
    private Army defendingArmy;

    /**
     * this is a constructor for the battle class that takes two classes.
     *
     * @param armyOne army for battle
     * @param armyTwo army for battle
     */
    public Battle(Army armyOne, Army armyTwo) {
        this.armyOne = armyOne;
        this.armyTwo = armyTwo;
    }

    /**
     * this method is the simulation of the battle, two armies attack each other, and one wins
     *
     * @return return the winning army as an Army object
     */
    public Army simulate(int terrain) {
        boolean winner;
        while (armyOne.hasUnits() && armyTwo.hasUnits()) {

            randomArmyAttackerDefender();
            Unit attackingUnit = attackingArmy.getRandom();
            Unit defendingUnit = defendingArmy.getRandom();

            winner = false;
            while (!winner) {
                attackingUnit.attack(defendingUnit, terrain);
                if (defendingUnit.getHealth() <= 0) {
                    defendingArmy.remove(defendingUnit);
                    winner = true;
                } else {
                    defendingUnit.attack(attackingUnit, terrain);
                    if (attackingUnit.getHealth() <= 0) {
                        attackingArmy.remove(attackingUnit);
                        winner = true;
                    }
                }
            }
        }
        Army winningArmy;
        if (armyOne.hasUnits()) {
            winningArmy = armyOne;
        } else {
            winningArmy = armyTwo;
        }
        return winningArmy;
    }

    /**
     * method that runs the simulation based on one attack
     * <p>
     * used for when user runs simulation with updates during run
     * it easier to us a simulation based on one attack, because updating can happened in gui, and not in Battle
     * </p>
     *
     * @param chosenTerrain specified terrain from GUI
     */
    public void simulateAttack(int chosenTerrain) {
        boolean winningUnit = false;
        Unit unitOne = attackingArmy.getRandom();
        Unit unitTwo = defendingArmy.getRandom();

        while (!winningUnit) {
            unitOne.attack(unitTwo, chosenTerrain);
            if (unitTwo.getHealth() <= 0) {
                defendingArmy.remove(unitTwo);
                winningUnit = true;
            } else {
                unitTwo.attack(unitOne, chosenTerrain);
                if (unitOne.getHealth() <= 0) {
                    attackingArmy.remove(unitOne);
                    winningUnit = true;
                }
            }
        }
    }

    /**
     * randomly selects the attacking and defending army
     */
    public void randomArmyAttackerDefender() {
        Random random = new Random();
        boolean randomBool = random.nextBoolean();

        if (randomBool) {
            attackingArmy = armyOne;
            defendingArmy = armyTwo;
        } else {
            attackingArmy = armyTwo;
            defendingArmy = armyOne;
        }
    }

    /**
     * get method for attackingArmy
     * <p>
     * would have this variable final, if test not necessary
     * </p>
     *
     * @return Army attacking army
     */
    public Army getAttackingArmy() {
        return attackingArmy;
    }

    /**
     * get method for defendingArmy
     * <p>
     * would have this variable final, if test not necessary
     * </p>
     *
     * @return Army defending army
     */
    public Army getDefendingArmy() {
        return defendingArmy;
    }

    /**
     * the toString method that print out both armies
     *
     * @return both armies units
     */
    @Override
    public String toString() {
        return armyOne.getName() + "\n" + armyOne.getAllUnits() +
                "\n\n" + armyTwo.getName() + "\n" + armyTwo.getAllUnits();
    }
}