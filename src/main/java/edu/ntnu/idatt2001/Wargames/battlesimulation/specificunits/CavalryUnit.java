package edu.ntnu.idatt2001.Wargames.battlesimulation.specificunits;

import edu.ntnu.idatt2001.Wargames.battlesimulation.battle.Terrain;

/**
 * Cavalry class that represents the cavalry unit with special features
 *
 * @author Tomas Beranek
 */
public class CavalryUnit extends Unit {

    private boolean firstCharge = true;

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
    public CavalryUnit(String name, int health, int attack, int armor, int melee) throws IllegalArgumentException {
        super(name, health, attack, armor, melee);
    }

    /**
     * an easier constructor with predefined attack and armor and melee
     *
     * @param name   unit name
     * @param health unit health
     * @throws IllegalArgumentException if health sett < 0 or name is empty thr exception
     */
    public CavalryUnit(String name, int health) throws IllegalArgumentException {
        this(name, health, 20, 12, 2);
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
        if (terrain == Terrain.PLAINS.getTerrainNum()) {
            if (firstCharge) {
                firstCharge = false;
                return this.getMelee() + 5;
            }
        } else {
            if (firstCharge) {
                firstCharge = false;
                return this.getMelee() + 3;
            }
        }
        return this.getMelee();
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
        int cavalry = 3;
        if (terrain == Terrain.FOREST.getTerrainNum()) {
            return 0;
        } else {
            return cavalry;
        }
    }

    /**
     * method that deep copies a unit
     *
     * @return the deep copied unit
     */
    @Override
    public Unit deepCopyUnit() {
        return new CavalryUnit(this.getName(), this.getHealth(), this.getAttack(), this.getArmor(), this.getMelee());
    }
}
