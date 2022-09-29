package edu.ntnu.idatt2001.Wargames.battlesimulation.battle;

import edu.ntnu.idatt2001.Wargames.battlesimulation.specificunits.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Class representing a full army, with different methods for handling the mechanics of an army
 *
 * @author Tomas Beranek
 */
public class Army {

    private final List<Unit> units;
    private final String name;

    /**
     * A constructor with a new list, and the name of the army
     *
     * @param name army name
     */
    public Army(String name) {
        if (name == null || name.isBlank()) throw new NullPointerException("Army name is empty");
        this.name = name;
        units = new ArrayList<>();
    }

    /**
     * A constructor created with an already existing list and a name of the army object created
     *
     * @param name  name of army
     * @param units list of units
     */
    public Army(String name, List<Unit> units) {
        if (name == null || name.isBlank()) throw new NullPointerException("name is empty");
        this.name = name;
        this.units = units;
    }

    /**
     * method for adding a unit to the army
     *
     * @param unit chosen unit to add to army
     */
    public void add(Unit unit) {
        units.add(unit);
    }

    /**
     * method for adding multiple units at one
     *
     * @param newUnits the list of the new units
     */
    public void addAll(List<Unit> newUnits) {
        units.addAll(newUnits);
    }

    /**
     * method for removing a specific unit in the army
     *
     * @param unit the unit to remove
     */
    public void remove(Unit unit) {
        units.remove(unit);
    }

    /**
     * method that return true if a list is empty or not
     *
     * @return true if the list is empty, false if it has units
     */
    public boolean hasUnits() {
        return units.size() > 0;
    }

    /**
     * method for getting all units
     *
     * @return a list of the unit
     */
    public List<Unit> getAllUnits() {
        return units;
    }

    /**
     * method for getting a random unit for a list
     *
     * @return a random unit
     */
    public Unit getRandom() {
        java.util.Random random = new java.util.Random();
        int randomNum = random.nextInt(units.size());
        return units.get(randomNum);
    }

    /**
     * this is a getmethod for getting the list of all units in an army
     *
     * @return list of unit in the army
     */
    public List<Unit> getUnits() {
        return units;
    }

    /**
     * methods for getting all infantryUnits in a army
     *
     * @return a new list of all infantryUnits
     */
    public List<Unit> getInfantryUnits() {
        return getUnits().stream()
                .filter(u -> u instanceof InfantryUnit)
                .collect(Collectors.toCollection(ArrayList::new));
    }

    /**
     * methods for getting all RangeUnitsUnits in a army
     *
     * @return a new list of all RangeUnits
     */
    public List<Unit> getRangeUnits() {
        return getUnits().stream()
                .filter(u -> u instanceof RangedUnit)
                .collect(Collectors.toCollection(ArrayList::new));
    }

    /**
     * methods for getting all CavalryUnits in a army
     *
     * @return a new list of all CavalryUnits
     */
    public List<Unit> getCavalryUnits() {
        return getUnits().stream()
                .filter(u -> u instanceof CavalryUnit && !(u instanceof CommanderUnit))
                .collect(Collectors.toCollection(ArrayList::new));
    }

    /**
     * methods for getting all CommanderUnits in a army
     *
     * @return a new list of all CommanderUnits
     */
    public List<Unit> getCommanderUnits() {
        return getUnits().stream()
                .filter(u -> u instanceof CommanderUnit)
                .collect(Collectors.toCollection(ArrayList::new));
    }

    /**
     * the get method for the name of the army
     *
     * @return String with the name of the army
     */
    public String getName() {
        return name;
    }

    /**
     * if a object is the same as a different unit
     *
     * @param o object to check if equals
     * @return true if the units are equal, false if not
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Army army)) return false;

        if (!Objects.equals(units, army.units)) return false;
        return getName() != null ? getName().equals(army.getName()) : army.getName() == null;
    }

    /**
     * method that makes a hashcode for an object
     *
     * @return int as a hashcode for the different objects
     */
    @Override
    public int hashCode() {
        int result = units != null ? units.hashCode() : 0;
        result = 31 * result + (getName() != null ? getName().hashCode() : 0);
        return result;
    }

    /**
     * a toString method for printing a units list
     *
     * @return a string of units
     */
    @Override
    public String toString() {
        return name +
                "\nunits=" + units;
    }
}
