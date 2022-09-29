package edu.ntnu.idatt2001.Wargames.battlesimulation.specificunits;

import edu.ntnu.idatt2001.Wargames.battlesimulation.battle.Terrain;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class InfantryUnitTest {

    @Test
    void Test_if_attack_method_deals_right_amount_of_damage() {
        Unit attackingUnit = new Unit("knight",1, 1, 0, 0) {
            @Override
            int getAttackBonus(int terrain) {
                return 0;
            }

            @Override
            int getResistBonus(int terrain) {
                return 0;
            }

            @Override
            public Unit deepCopyUnit() {
                return null;
            }
        };
        Unit defendingUnit = new Unit("Orc", 10, 0, 0, 0) {
            @Override
            int getAttackBonus(int terrain) {
                return 0;
            }

            @Override
            int getResistBonus(int terrain) {
                return 0;
            }

            @Override
            public Unit deepCopyUnit() {
                return null;
            }
        };
        int basicTerrain = Terrain.HILL.getTerrainNum();
        int expectedRemainingHealth = 9;

        attackingUnit.attack(defendingUnit, basicTerrain);
        int actualHealth = defendingUnit.getHealth();

        assertEquals(expectedRemainingHealth, actualHealth);

    }

    @Test
    void Test_getName_method() {
        String expectedName = "Knight";
        Unit test = new InfantryUnit(expectedName, 10);

        String actualName = test.getName();

        assertEquals(expectedName,actualName);
    }

    @Test
    void Test_getHealth_method() {
        int expectedHealth = 10;
        Unit test = new InfantryUnit("Knight", expectedHealth);

        int actualHealth = test.getHealth();

        assertEquals(expectedHealth, actualHealth);
    }

    @Test
    void Test_getAttack_method() {
        int expectedAttack = 2;
        Unit test = new InfantryUnit("knight",1,expectedAttack,3,4);

        int actualAttack = test.getAttack();

        assertEquals(expectedAttack, actualAttack);
    }

    @Test
    void Test_getArmor_method() {
        int expectedArmor = 3;
        Unit test = new InfantryUnit("knight",1,2,expectedArmor,4);

        int actualArmor = test.getArmor();

        assertEquals(expectedArmor, actualArmor);
    }

    @Test
    void Test_getMelee_method() {
        int expectedMelee = 4;
        Unit test = new InfantryUnit("knight",1,2,3,expectedMelee);

        int actualMelee = test.getMelee();

        assertEquals(expectedMelee, actualMelee);
    }

    @Test
    void Test_setHealth_method() {
        int expectedNewHealth = 10;
        Unit test = new InfantryUnit("knight",1,2,3,4);

        test.setHealth(expectedNewHealth);
        int actualNewHealth = test.getHealth();

        assertEquals(expectedNewHealth, actualNewHealth);
    }

    @Test
    void Test_ToString_method() {
        String expectedString = "\n\n"+ """
                The statistics of unit:\s
                knight
                Health:
                1
                Damage/DP:
                2
                Armor/AP\s
                3""";
        Unit test = new InfantryUnit("knight",1,2,3,4);

        String actualString = test.toString();

        assertEquals(expectedString, actualString);
    }

    @Test
    void Test_attack_based_on_attackBonus() {
        int  expectedHealthWithBonus = 9;
        Unit attackTest = new InfantryUnit("knight",1,0,0,2);
        Unit defenceTest = new InfantryUnit("Orc",10,0,0,0);

        attackTest.attack(defenceTest, Terrain.HILL.getTerrainNum());
        int actualHealthWithBonus = defenceTest.getHealth();

        assertEquals(expectedHealthWithBonus, actualHealthWithBonus);
    }

    @Test
    void Test_if_attack_deal_right_damage_based_on_getResistBonus() {
        int  expectedHealthWithBonus = 9;
        Unit attackTest = new InfantryUnit("knight",1,0,0,2);
        Unit defenceTest = new InfantryUnit("Orc",10,0,0,0);

        attackTest.attack(defenceTest, Terrain.HILL.getTerrainNum());
        int actualHealthWithBonus = defenceTest.getHealth();

        assertEquals(expectedHealthWithBonus, actualHealthWithBonus);
    }

    @Test
    void Test_getUnitType_method() {
        Unit infantryUnit = new InfantryUnit("name", 1);
        String expectedString = "InfantryUnit";

        String actualUnitType = infantryUnit.getUnitType();

        assertEquals(expectedString, actualUnitType);
    }

    @Test
    void Test_deepCopyUnit_method(){
        Unit oldUnit = new InfantryUnit("name", 1);

        Unit newUnit = oldUnit.deepCopyUnit();

        assertNotEquals(newUnit, oldUnit);
    }

    @Test
    void Test_terrain_attack_change_in_forest(){
        Unit infantryWithTerrainBonus = new InfantryUnit("name", 1, 0, 0, 0);
        int expectedBonus = 1;

        int attackWithForrest = infantryWithTerrainBonus.getAttackBonus(Terrain.FOREST.getTerrainNum());

        assertEquals(expectedBonus, attackWithForrest);
    }

    @Test
    void Test_no_terrain_attack_change(){
        Unit infantryWithTerrainBonus = new InfantryUnit("name", 1, 0, 0, 0);
        int expectedBonus = 0;

        int attackWithForrest = infantryWithTerrainBonus.getAttackBonus(Terrain.HILL.getTerrainNum());

        assertEquals(expectedBonus, attackWithForrest);
    }

    @Test
    void Test_terrain_defence_change_in_forest(){
        Unit test = new InfantryUnit("name", 1, 0, 0, 0);
        int expectedResistBonus = 2;

        int resistWithForrest = test.getResistBonus(Terrain.FOREST.getTerrainNum());

        assertEquals(expectedResistBonus, resistWithForrest);
    }

    @Test
    void Test_no_terrain_defence_change(){
        Unit test = new InfantryUnit("name", 1, 0, 0, 0);
        int expectedResistBonus = 1;

        int resistWithForrest = test.getResistBonus(Terrain.HILL.getTerrainNum());

        assertEquals(expectedResistBonus, resistWithForrest);
    }
}