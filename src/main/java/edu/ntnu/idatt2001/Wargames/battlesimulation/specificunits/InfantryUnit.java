package edu.ntnu.idatt2001.Wargames.battlesimulation.specificunits;

import edu.ntnu.idatt2001.Wargames.battlesimulation.battle.Terrain;

/**
 * Infantry class that represents all the variables and methods of an infantry unit
 *
 * @author Tomas Beranek
 */
public class InfantryUnit extends Unit {

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
    public InfantryUnit(String name, int health, int attack, int armor, int melee) throws IllegalArgumentException {
        super(name, health, attack, armor, melee);
    }

    /**
     * a constructor with predefined attack and armor
     *
     * @param name   unit name
     * @param health unit health
     * @throws IllegalArgumentException if health sett < 0 or name is empty thr exception
     */
    public InfantryUnit(String name, int health) throws IllegalArgumentException {
        this(name, health, 15, 10, 2);
    }

    /**
     * this unit is a melee unit
     *
     * @param terrain specific terrain
     * @return which means that it gets a +2 as a AttackBonus
     * if the terrain type is forest the bonus is melee +1
     */
    @Override
    int getAttackBonus(int terrain) {
        if (terrain == Terrain.FOREST.getTerrainNum()) return this.getMelee() + 1;
        else return this.getMelee();
    }

    /**
     * infantry unit has only a small armor bonus
     * If the terrain type is forest the bonus is +2 instead
     *
     * @param terrain specific terrain
     * @return because of small armor bonus add 1 as a ResistBonus
     */
    @Override
    int getResistBonus(int terrain) {
        if (terrain == Terrain.FOREST.getTerrainNum()) {
            return 2;
        } else {
            return 1;
        }
    }

    /**
     * method of deep coping a unit
     *
     * @return new unit
     */
    @Override
    public Unit deepCopyUnit() {
        return new InfantryUnit(this.getName(), this.getHealth(), this.getAttack(), this.getArmor(), this.getMelee());
    }
}
