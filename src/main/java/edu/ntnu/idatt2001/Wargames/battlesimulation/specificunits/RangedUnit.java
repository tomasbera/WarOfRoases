package edu.ntnu.idatt2001.Wargames.battlesimulation.specificunits;

import edu.ntnu.idatt2001.Wargames.battlesimulation.battle.Terrain;

/**
 * Range class that represents all the variables and methods of a ranger unit
 *
 * @author Tomas Beranek
 */
public class RangedUnit extends Unit {

    private int distanceDamage = 0;
    private int distanceResist = 0;

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
    public RangedUnit(String name, int health, int attack, int armor, int melee) throws IllegalArgumentException {
        super(name, health, attack, armor, melee);
    }

    /**
     * an easier constructor with predefined attack and armor
     *
     * @param name   unit name
     * @param health unit health
     * @throws IllegalArgumentException if health sett < 0 or name is empty thr exception
     */
    public RangedUnit(String name, int health) throws IllegalArgumentException {
        this(name, health, 15, 8, 2);
    }

    /**
     * Range unit has a range attack bonus
     * If the terrain type is hill the bonus is melee + 1 for distance greater than 3, else its 4
     * If the type is forest the bonus is melee +1
     *
     * @param terrain which terrain
     * @return because this unit is range, this unit gets +3 as AttackBonus
     */
    @Override
    int getAttackBonus(int terrain) {
        if (terrain == Terrain.HILL.getTerrainNum()) {
            if (distanceDamage >= 3) {
                return this.getMelee() + 1;
            }
            distanceDamage++;
            return 4;
        } else if (terrain == Terrain.FOREST.getTerrainNum()) {
            return getMelee() + 1;
        } else {
            if (distanceDamage >= 3) {
                return this.getMelee();
            }
            distanceDamage++;
            return 2;
        }
    }

    /**
     * Range units has an uniq resistantBonus that works as distance between him and his opponent
     *
     * @param terrain which terrain
     * @return because of this the range unit returns a bonus based on the variable multiplier (distance)
     */
    @Override
    int getResistBonus(int terrain) {
        int resistBonus = 6 - 2 * distanceResist;
        if (resistBonus <= 2) {
            resistBonus = 2;
        }
        distanceResist++;
        return resistBonus;
    }

    /**
     * method for deep coping a unit
     *
     * @return the new Unit
     */
    @Override
    public Unit deepCopyUnit() {
        return new RangedUnit(this.getName(), this.getHealth(), this.getAttack(), this.getArmor(), this.getMelee());
    }
}
