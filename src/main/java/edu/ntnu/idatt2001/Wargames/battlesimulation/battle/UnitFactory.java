package edu.ntnu.idatt2001.Wargames.battlesimulation.battle;

import edu.ntnu.idatt2001.Wargames.battlesimulation.specificunits.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * This is a Unit Factory class that creates different types of a unit
 *
 * @author Tomas Beranek
 */
public class UnitFactory {
    private Unit newUnit = null;

    /**
     * this method is used for creating a specific type of unit with different variables
     *
     * @param type   type of unit
     * @param name   unit name
     * @param health unit health
     * @return a new unit
     */
    public Unit newUnitFactory(String type, String name, int health) {
        if (Objects.equals(type, "InfantryUnit")) {
            newUnit = new InfantryUnit(name, health);
        } else if (Objects.equals(type, "RangedUnit")) {
            newUnit = new RangedUnit(name, health);
        } else if (Objects.equals(type, "CavalryUnit")) {
            newUnit = new CavalryUnit(name, health);
        } else if (Objects.equals(type, "CommanderUnit")) {
            newUnit = new CommanderUnit(name, health);
        } else {
            throw new NullPointerException("No unit such type");
        }
        return newUnit;
    }

    /**
     * this method is used for creating a specific unit n number of times
     *
     * @param type       unit type
     * @param name       unit name
     * @param health     unit health
     * @param numOfUnits number of that new unit
     * @return arraylist with all units
     */
    public List<Unit> newNumberOfUnits(String type, String name, int health, int numOfUnits) {
        List<Unit> newUnits = new ArrayList<>();

        if (numOfUnits <= 0) {
            throw new NullPointerException("Number of units is negative or zero");
        }

        for (int i = 0; i < numOfUnits; i++) {
            newUnits.add(newUnitFactory(type, name, health));
        }
        return newUnits;
    }
}
