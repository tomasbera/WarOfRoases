package edu.ntnu.idatt2001.Wargames.battlesimulation.specificunits;

import edu.ntnu.idatt2001.Wargames.battlesimulation.battle.Terrain;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CavalryUnitTest {

    @Test
    void Test_if_attack_method_deals_right_amount_of_damage() {
        Unit attackingUnit = new Unit("Rider", 1, 1, 0, 0) {
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

        assertEquals(expectedRemainingHealth, actualHealth);
    }

    @Test
    void Test_getName_method() {
        String expectedName = "Rider";
        Unit test = new CavalryUnit(expectedName,10);

        String actualName = test.getName();

        assertEquals(expectedName,actualName);
    }

    @Test
    void Test_getHealth_method() {
        int expectedHealth = 1;
        Unit test = new CavalryUnit("Rider",expectedHealth);

        int actualHealth = test.getHealth();

        assertEquals(expectedHealth, actualHealth);
    }

    @Test
    void Test_getAttack_method() {
        int expectedAttack = 2;
        Unit test = new CavalryUnit("Rider",1,expectedAttack,3,4);

        int actualAttack = test.getAttack();

        assertEquals(expectedAttack, actualAttack);
    }

    @Test
    void Test_getArmor_method() {
        int expectedArmor = 3;
        Unit test = new CavalryUnit("Rider",1,2,expectedArmor,4);

        int actualArmor = test.getArmor();

        assertEquals(expectedArmor, actualArmor);
    }

    @Test
    void Test_getMelee_method() {
        int expectedMelee = 4;
        Unit test = new CavalryUnit("Rider",1,2,3,expectedMelee);

        int actualMelee = test.getMelee();

        assertEquals(expectedMelee, actualMelee);
    }

    @Test
    void Test_setHealth_method() {
        int expectedNewHealth = 10;
        Unit test = new CavalryUnit("Rider",1,2,3,4);

        test.setHealth(expectedNewHealth);
        int actualNewHealth = test.getHealth();

        assertEquals(expectedNewHealth, actualNewHealth);
    }

    @Test
    void Test_ToString_method() {
        Unit test = new CavalryUnit("Rider",1,2,3,4);
        String expectedString = "\n\n"+ """
                The statistics of unit:\s
                Rider
                Health:
                1
                Damage/DP:
                2
                Armor/AP\s
                3""";

        String actualString = test.toString();

        assertEquals(expectedString, actualString);
    }

    @Test
    void Test_attack_based_on_attackBonus() {
        int  expectedHealthWithBonus = 8;
        Unit attackingUnit = new CavalryUnit("Rider",1,2,0,2);
        Unit defendingUnit = new InfantryUnit("Knight",20,0,0,0);

        attackingUnit.attack(defendingUnit, Terrain.HILL.getTerrainNum());
        attackingUnit.attack(defendingUnit, Terrain.HILL.getTerrainNum());
        attackingUnit.attack(defendingUnit, Terrain.HILL.getTerrainNum());
        int actualHealthWithBonus = defendingUnit.getHealth();

        assertEquals(expectedHealthWithBonus, actualHealthWithBonus);
    }

    @Test
    void Test_if_attack_deal_right_damage_based_on_getResistBonus() {
        int  expectedHealthWithBonus = 8;
        Unit defendingUnit = new CavalryUnit("Rider",10,0,0,0);
        Unit attackingUnit = new InfantryUnit("Knight",1,2,0,2);

        attackingUnit.attack(defendingUnit, Terrain.HILL.getTerrainNum());
        attackingUnit.attack(defendingUnit, Terrain.HILL.getTerrainNum());

        int actualHealthWithBonus = defendingUnit.getHealth();

        assertEquals(expectedHealthWithBonus, actualHealthWithBonus);
    }

    @Test
    void Test_getUnitType_method() {
        Unit cavalryUnit = new CavalryUnit("name", 1);
        String expectedString = "CavalryUnit";

        String actualUnitType = cavalryUnit.getUnitType();

        assertEquals(expectedString, actualUnitType);
    }

    @Test
    void Test_deepCopyUnit_method(){
        Unit oldUnit = new CavalryUnit("name", 1);

        Unit newUnit = oldUnit.deepCopyUnit();

        assertNotEquals(newUnit, oldUnit);
    }

    @Test
    void Test_if_terrain_attack_change_gives_the_right_bonus_based_on_Plains(){
        int expectedBonus = 5;
        Unit cavalryWithPlainsBonus = new CavalryUnit("name", 1, 0, 0, 0);

        int attackWithPlains = cavalryWithPlainsBonus.getAttackBonus(Terrain.PLAINS.getTerrainNum());

        assertEquals(expectedBonus, attackWithPlains);
    }

    @Test
    void Test_terrain_attack_change_after_firstCharge_plains(){
        int expectedBonusAfterCharge = 0;
        Unit attackingUnit = new CavalryUnit("name", 1, 0, 0, 0);
        Unit defendingUnit = new InfantryUnit("name", 100);

        attackingUnit.attack(defendingUnit,Terrain.PLAINS.getTerrainNum());
        int attackWithPlains = attackingUnit.getAttackBonus(Terrain.PLAINS.getTerrainNum());

        assertEquals(expectedBonusAfterCharge, attackWithPlains);
    }

    @Test
    void Test_terrain_no_attack_change_hill(){
        int expectedBonus = 3;
        Unit cavalryUnitWithNoBonus = new CavalryUnit("name", 1, 0, 0, 0);

        int attackWithoutForrest = cavalryUnitWithNoBonus.getAttackBonus(Terrain.HILL.getTerrainNum());

        assertEquals(expectedBonus, attackWithoutForrest);
    }

    @Test
    void Test_terrain_defence_change_based_on_Forest(){
        int expectedDefenceBonus = 0;
        Unit cavalryUnitWithForrest = new CavalryUnit("name", 1, 0, 0, 0);

        int resistWithForrest = cavalryUnitWithForrest.getResistBonus(Terrain.FOREST.getTerrainNum());

        assertEquals(expectedDefenceBonus, resistWithForrest);
    }

    @Test
    void Test_if_terrain_with_no_defence_change_gives_right_amount_of_resist(){
        int expectedDefenceWithNoChange = 3;
        Unit cavalryUnitWithoutChange = new CavalryUnit("name", 1, 0, 0, 0);

        int resistWithoutForrest = cavalryUnitWithoutChange.getResistBonus(Terrain.HILL.getTerrainNum());

        assertEquals(expectedDefenceWithNoChange, resistWithoutForrest);
    }
}