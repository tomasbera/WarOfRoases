package edu.ntnu.idatt2001.Wargames.battlesimulation.specificunits;

/**
 * Commander class that represents all the variables and methods of a commander unit
 *
 * @author Tomas Beranek
 */
public class CommanderUnit extends CavalryUnit {

    /**
     * this is the constructor for all units
     *
     * @param name   unit name
     * @param health unit health
     * @param attack damage
     * @param armor  protection
     * @param melee  melee damage
     * @throws IllegalArgumentException if health sett < 0 or name is empty thr exception
     */
    public CommanderUnit(String name, int health, int attack, int armor, int melee) throws IllegalArgumentException {
        super(name, health, attack, armor, melee);
    }

    /**
     * an easier constructor with predefined attack and armor
     *
     * @param name   unit name
     * @param health unit health
     * @throws IllegalArgumentException if health sett < 0 or name is empty thr exception
     */
    public CommanderUnit(String name, int health) throws IllegalArgumentException {
        this(name, health, 25, 15, 2);
    }

    /**
     * This method represents the attackBonuses of a cavalry unit
     * the first attack of this unit will it will get a charge bonus +3
     * later on it will only have the melee attack as main attackBonus
     * If the terrain is Plains the cavalryUnit get additional +2 bonus
     *
     * @param terrain specific terrain
     * @return ether +5 or +2 as a attackBonus, depends on the firstCharge bonus
     */
    @Override
    int getAttackBonus(int terrain) {
        return super.getAttackBonus(terrain);
    }

    /**
     * this unit has a better protection than basic infantry, and has a bonus of +3 to resistance
     * if the terrain is of forest type the bonus is 0
     *
     * @param terrain specific terrain
     * @return bonus of 3 as resistance
     */
    @Override
    int getResistBonus(int terrain) {
        return super.getResistBonus(terrain);
    }

    /**
     * deep copy method for units
     *
     * @return new unit
     */
    @Override
    public Unit deepCopyUnit() {
        return new CommanderUnit(this.getName(), this.getHealth(), this.getAttack(), this.getArmor(), this.getMelee());
    }
}
