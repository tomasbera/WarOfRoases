package edu.ntnu.idatt2001.Wargames.battlesimulation.specificunits;

import edu.ntnu.idatt2001.Wargames.battlesimulation.battle.Terrain;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RangedUnitTest {

    @Test
    void Test_if_attack_method_deals_right_amount_of_damage() {
        Unit attackingUnit = new Unit("Bowman", 10, 1, 0, 0) {
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
        Unit defendingUnit = new Unit("Knight", 10, 0, 0, 0) {
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

        assertEquals(actualHealth, expectedRemainingHealth);
    }

    @Test
    void Test_getName_method() {
        String expectedName = "Bowmen";
        Unit test = new RangedUnit("Bowmen",10);

        String actualName = test.getName();

        assertEquals(expectedName,actualName);
    }

    @Test
    void Test_getHealth_method() {
        int expectedHealth = 1;
        Unit test = new RangedUnit("Bowmen",expectedHealth);

        int actualHealth = test.getHealth();

        assertEquals(expectedHealth, actualHealth);
    }

    @Test
    void Test_getAttack_method() {
        int expectedAttack = 2;
        Unit test = new RangedUnit("Bowmen",1,expectedAttack,3,4);

        int actualAttack = test.getAttack();

        assertEquals(expectedAttack, actualAttack);
    }

    @Test
    void Test_getArmor_method() {
        int expectedArmor = 3;
        Unit test = new RangedUnit("Bowmen",1,2,expectedArmor,4);

        int actualArmor = test.getArmor();

        assertEquals(expectedArmor, actualArmor);
    }

    @Test
    void Test_getMelee_method() {
        int expectedMelee = 4;
        Unit test = new RangedUnit("Bowmen",1,2,3,expectedMelee);

        int actualMelee = test.getMelee();

        assertEquals(expectedMelee, actualMelee);
    }

    @Test
    void Test_setHealth_method() {
        int expectedNewHealth = 10;
        Unit test = new RangedUnit("Bowmen",1,2,3,4);

        test.setHealth(expectedNewHealth);
        int actualNewHealth = test.getHealth();

        assertEquals(expectedNewHealth, actualNewHealth);
    }

    @Test
    void Test_ToString_method() {
        String expectedString = "\n\n"+ """
                The statistics of unit:\s
                Bowmen
                Health:
                1
                Damage/DP:
                2
                Armor/AP\s
                3""";
        Unit test = new RangedUnit("Bowmen",1,2,3,4);

        String actualString = test.toString();

        assertEquals(expectedString, actualString);
    }

    @Test
    void Test_attack_based_on_attackBonus() {
        int  expectedHealthWithBonus = 4;
        Unit attackTest = new RangedUnit("Bowman",10,2,1,1);
        Unit defendingTest = new InfantryUnit("Knight",20,1,1,1);

        attackTest.attack(defendingTest, Terrain.HILL.getTerrainNum());
        attackTest.attack(defendingTest, Terrain.HILL.getTerrainNum());
        attackTest.attack(defendingTest, Terrain.HILL.getTerrainNum());
        attackTest.attack(defendingTest, Terrain.HILL.getTerrainNum());
        attackTest.attack(defendingTest, Terrain.HILL.getTerrainNum());
        int actualHealthWithBonus = defendingTest.getHealth();

        assertEquals(expectedHealthWithBonus, actualHealthWithBonus);
    }

    @Test
    void Test_if_attack_deal_right_damage_based_on_getResistBonus() {
        int  expectedHealthWithBonus = 1;
        Unit defendingUnit = new RangedUnit("Bowman",15,0,0,0);
        Unit attackingUnit = new InfantryUnit("Knight",1,3,0,2);

        attackingUnit.attack(defendingUnit, Terrain.HILL.getTerrainNum());
        attackingUnit.attack(defendingUnit, Terrain.HILL.getTerrainNum());
        attackingUnit.attack(defendingUnit, Terrain.HILL.getTerrainNum());
        attackingUnit.attack(defendingUnit, Terrain.HILL.getTerrainNum());
        attackingUnit.attack(defendingUnit, Terrain.HILL.getTerrainNum());
        int actualHealthWithBonus = attackingUnit.getHealth();

        assertEquals(expectedHealthWithBonus, actualHealthWithBonus);

    }

    @Test
    void Test_getUnitType_method() {
        Unit rangedUnit = new RangedUnit("name", 1);
        String expectedString = "RangedUnit";

        String actualUnitType = rangedUnit.getUnitType();

        assertEquals(expectedString, actualUnitType);
    }


    @Test
    void Test_deepCopyUnit_method(){
        Unit oldUnit = new RangedUnit("name", 1);

        Unit newUnit = oldUnit.deepCopyUnit();

        assertNotEquals(newUnit, oldUnit);
    }

    @Test
    void Test_terrain_attack_change_in_hill(){
        Unit rangedWithBonusHill = new RangedUnit("name", 1, 0, 0, 0);
        int expectedBonus = 4;

        int attackWithHill = rangedWithBonusHill.getAttackBonus(Terrain.HILL.getTerrainNum());

        assertEquals(expectedBonus, attackWithHill);
    }

    @Test
    void Test_terrain_attack_change_forest(){
        Unit rangedWithBonusForest = new RangedUnit("name", 1, 0, 0, 0);
        int expectedBonus = 1;

        int attackWithForest = rangedWithBonusForest.getAttackBonus(Terrain.FOREST.getTerrainNum());

        assertEquals(expectedBonus, attackWithForest);
    }

    @Test
    void Test_no_terrain_attack_change(){
        Unit rangedWithNoBonus = new RangedUnit("name", 1, 0, 0, 0);
        int expectedResistBonus = 2;

        int attackWithoutBonus = rangedWithNoBonus.getAttackBonus(Terrain.PLAINS.getTerrainNum());

        assertEquals(expectedResistBonus, attackWithoutBonus);
    }

    @Test
    void Test_terrain_attack_change_when_distance_changes_tre_times(){
        Unit test = new RangedUnit("name", 1, 0, 0, 0);
        Unit attackedUnit = new InfantryUnit("name", 100,0,0,0);
        int expectedBonus = 0;

        test.attack(attackedUnit, Terrain.PLAINS.getTerrainNum());
        test.attack(attackedUnit, Terrain.PLAINS.getTerrainNum());
        test.attack(attackedUnit, Terrain.PLAINS.getTerrainNum());
        int attackWithForrest = test.getAttackBonus(Terrain.PLAINS.getTerrainNum());

        assertEquals(expectedBonus, attackWithForrest);
    }
}