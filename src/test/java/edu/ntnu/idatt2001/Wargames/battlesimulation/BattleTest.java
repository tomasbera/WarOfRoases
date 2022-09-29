package edu.ntnu.idatt2001.Wargames.battlesimulation;

import edu.ntnu.idatt2001.Wargames.battlesimulation.battle.Army;
import edu.ntnu.idatt2001.Wargames.battlesimulation.battle.Battle;
import edu.ntnu.idatt2001.Wargames.battlesimulation.battle.Terrain;
import edu.ntnu.idatt2001.Wargames.battlesimulation.specificunits.*;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
class BattleTest {

    @Test
    void Test_if_simulate_method_simulates_a_battle_between_two_armies() {
        Army armyTest1 = new Army("A");
        Army armyTest2 = new Army("B");
        Battle battleTest = new Battle(armyTest1, armyTest2);

        List<Unit> listTest1 = new ArrayList<>();
        Unit infantryTest1 = new InfantryUnit("Knight", 10);
        Unit RangeTest1 = new RangedUnit("Bowmen", 10);
        Unit CavalryTest1 = new CavalryUnit("Rider", 10);

        List<Unit> listTest2 = new ArrayList<>();
        Unit infantryTest2 = new InfantryUnit("Orc", 10);
        Unit RangeTest2 = new RangedUnit("Orc crossbow", 10);
        Unit CavalryTest2 = new CavalryUnit("Wolf Rider", 10);


        listTest1.add(infantryTest1);
        listTest1.add(RangeTest1);
        listTest1.add(CavalryTest1);

        listTest2.add(infantryTest2);
        listTest2.add(RangeTest2);
        listTest2.add(CavalryTest2);

        armyTest1.addAll(listTest1);
        armyTest2.addAll(listTest2);

        battleTest.randomArmyAttackerDefender();


        assertNotNull(battleTest.simulate(Terrain.HILL.getTerrainNum()));
    }

    @Test
    void test_if_simulateAttack_simulates_a_battle_between_to_unit() {
        Army armyTest1 = new Army("A");
        Army armyTest2 = new Army("B");
        Battle battleTest = new Battle(armyTest1, armyTest2);

        List<Unit> listTest1 = new ArrayList<>();
        Unit infantryTest1 = new InfantryUnit("Knight", 10);

        List<Unit> listTest2 = new ArrayList<>();
        Unit infantryTest2 = new InfantryUnit("Orc", 10);


        listTest1.add(infantryTest1);
        listTest2.add(infantryTest2);

        armyTest1.addAll(listTest1);
        armyTest2.addAll(listTest2);

        battleTest.randomArmyAttackerDefender();
        battleTest.simulateAttack(Terrain.HILL.getTerrainNum());


        assertTrue(infantryTest1.getHealth()<=0||infantryTest2.getHealth()<=0);
    }

    @Test
    void Test_if_randomAttackDefenderArmy_gives_a_attackingArmy() {
        Army armyTest1 = new Army("A");
        Army armyTest2 = new Army("B");
        Battle battleTest = new Battle(armyTest1, armyTest2);

        battleTest.randomArmyAttackerDefender();

        assertNotNull(battleTest.getAttackingArmy());
    }

    @Test
    void Test_if_randomAttackDefenderArmy_gives_a_defendingArmy() {
        Army armyTest1 = new Army("A");
        Army armyTest2 = new Army("B");
        Battle battleTest = new Battle(armyTest1, armyTest2);

        battleTest.randomArmyAttackerDefender();

        assertNotNull(battleTest.getDefendingArmy());
    }

    @Test
    void Test_if_attackingArmy_and_defendingArmy_are_the_same_based_on_randomArmyAttackerDefender_method() {
        Army armyTest1 = new Army("A");
        Army armyTest2 = new Army("B");
        Battle battleTest = new Battle(armyTest1, armyTest2);

        battleTest.randomArmyAttackerDefender();

        assertNotEquals(battleTest.getAttackingArmy(), battleTest.getDefendingArmy());
    }

    @Test
    void Test_if_simulateAttack_removes_dead_unit() {
        Army armyTest1 = new Army("A");
        Army armyTest2 = new Army("B");
        Battle battleTest = new Battle(armyTest1, armyTest2);

        List<Unit> listTest1 = new ArrayList<>();
        Unit infantryTest1 = new InfantryUnit("Knight", 10);

        List<Unit> listTest2 = new ArrayList<>();
        Unit infantryTest2 = new InfantryUnit("Orc", 10);


        listTest1.add(infantryTest1);
        listTest2.add(infantryTest2);

        armyTest1.addAll(listTest1);
        armyTest2.addAll(listTest2);

        battleTest.randomArmyAttackerDefender();
        battleTest.simulateAttack(Terrain.HILL.getTerrainNum());


        assertTrue(!armyTest1.hasUnits()||!armyTest2.hasUnits());
    }



    @Test
    void Test_if_toString_returns_a_expected_string() {
        Army test1 = new Army("A");
        Army test2 = new Army("B");
        String expectedTString = "A\n[]\n\nB\n[]";

        Battle battle = new Battle(test1, test2);

        assertEquals(expectedTString, battle.toString());
    }
}

