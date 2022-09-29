package edu.ntnu.idatt2001.Wargames.battlesimulation;

import edu.ntnu.idatt2001.Wargames.battlesimulation.battle.Army;
import edu.ntnu.idatt2001.Wargames.battlesimulation.specificunits.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ArmyTest {

    public Army armySimpleConstructor;
    public Army armyConstructorWithUnits;
    public List<Unit> listWithFourUnits;

    @BeforeEach
    void Initiate_army_with_units(){
        String expectedName = "HumanArmyTest.csv";
        Unit infantryTest = new InfantryUnit("Knight", 10);
        Unit RangeTest = new RangedUnit("Bowmen", 10);
        Unit CavalryTest = new CavalryUnit("Rider", 10);
        Unit CommanderUnit = new CommanderUnit("Commander", 10);
        armySimpleConstructor = new Army(expectedName);
        listWithFourUnits = new ArrayList<>();

        listWithFourUnits.add(infantryTest);
        listWithFourUnits.add(RangeTest);
        listWithFourUnits.add(CavalryTest);
        listWithFourUnits.add(CommanderUnit);

        armyConstructorWithUnits = new Army(expectedName, listWithFourUnits);
    }

    @Test
    void Test_if_constructor_Creates_a_Army(){
        Army expectedArmy = new Army("armyName");

        assertNotNull(expectedArmy);
    }

    @Test
    void Test_to_see_if_other_constructor_also_Creates_a_army(){
        Army expectedArmy = new Army("name", listWithFourUnits);

        assertNotNull(expectedArmy);
    }

    @Test
    void Test_If_getName_return_expected_name() {
        String expectedName = "expectedName";

        Army armyWithName = new Army(expectedName);

        assertEquals(expectedName, armyWithName.getName());
    }

    @Test
    void Test_if_add_adds_all_expected_units_to_a_unit() {
        Unit expectedInfantryTest = new InfantryUnit("Knight", 10);
        Army armyWithInfUnit = new Army("Name");

        armyWithInfUnit.add(expectedInfantryTest);

        assertEquals(expectedInfantryTest,armyWithInfUnit.getUnits().get(0));
    }

    @Test
    void Test_if_addAll_adds_all_expected_units() {
        int expectedNumberOfUnits = 4;

        armySimpleConstructor.addAll(listWithFourUnits);

        assertEquals(expectedNumberOfUnits, armyConstructorWithUnits.getUnits().size());
    }

    @Test
    void Test_if_remove_removes_a_specific_unit() {
        int expectedNumberAfterDelete = 0;
        Unit infantryTest = new InfantryUnit("Knight", 10);
        armySimpleConstructor = new Army("Name");

        armySimpleConstructor.add(infantryTest);
        armySimpleConstructor.remove(infantryTest);

        assertEquals(expectedNumberAfterDelete,armySimpleConstructor.getUnits().size());
    }

    @Test
    @DisplayName("This test check if a army with no units and a army " +
            "with units are equal based around the hasUnits method i army")
    void Test_if_hasUnits_return_true_or_false_based_on_situation() {
        boolean armyHasUnit;
        boolean armyHasNoUnits;
        Army armyWithNoUnits = new Army("Name");
        Army armyWithUnits = new Army("NAME");


        armyWithUnits.addAll(listWithFourUnits);
        armyHasUnit = armyWithUnits.hasUnits();
        armyHasNoUnits = armyWithNoUnits.hasUnits();


        assertNotEquals(armyHasNoUnits, armyHasUnit);
    }

    @Test
    void Test_if_getAllUnits_get_all_units() {
        int expectedNumOfUnits = 4;
        armySimpleConstructor = new Army("Name");

        armySimpleConstructor.addAll(listWithFourUnits);

        assertEquals(expectedNumOfUnits, armySimpleConstructor.getAllUnits().size());
    }

    @Test
    void Test_if_getRandom_returns_a_random_unit() {
        Unit nullUnit = null;
        Unit infantryToAdd = new InfantryUnit("Unit", 1);
        armySimpleConstructor = new Army("ArmyWithOneUnit");

        armySimpleConstructor.add(infantryToAdd);
        Unit expectedUnit = armySimpleConstructor.getRandom();

        assertNotEquals(nullUnit, expectedUnit);
    }

    @Nested
    public class Test_for_GetMethods_for_different_object_from_one_list {

        @Test
        public void Test_if_getInfantryUnits_responds_to_infantry_units_in_a_army(){
            Unit InfantryUnit = new InfantryUnit("Name", 1);
            armySimpleConstructor = new Army("ArmyWithOneInfantryUnit");

            armySimpleConstructor.add(InfantryUnit);

            assertTrue(armySimpleConstructor.getInfantryUnits().get(0) instanceof InfantryUnit);
        }

        @Test
        public void Test_if_getRangeUnits_responds_to_range_units_in_a_army(){
            Unit RangedUnit = new RangedUnit("Name", 1);
            armySimpleConstructor = new Army("ArmyWithOneRangedUnit");

            armySimpleConstructor.add(RangedUnit);

            assertTrue(armySimpleConstructor.getRangeUnits().get(0) instanceof RangedUnit);
        }

        @Test
        public void Test_if_getCavalryUnits_responds_to_cavalry_units_in_a_army(){
            Unit CavalryUnit= new CavalryUnit("Name", 1);
            armySimpleConstructor = new Army("ArmyWithOneCavalryUnit");

            armySimpleConstructor.add(CavalryUnit);

            assertTrue(armySimpleConstructor.getCavalryUnits().get(0) instanceof CavalryUnit);
        }

        @Test
        public void Test_if_getCommanderUnits_responds_to_commander_units_in_a_army(){
            Unit CommanderUnit = new CommanderUnit("Name", 1);
            armySimpleConstructor = new Army("ArmyWithOneCommanderUnit");

            armySimpleConstructor.add(CommanderUnit);

            assertTrue(armySimpleConstructor.getCommanderUnits().get(0) instanceof CommanderUnit);
        }

        @Test
        @DisplayName("Test if getCavalryUnits method also returns commander unit" +
                "tested through usage of size getCavalryUnits from a army")
        public void Test_to_see_if_commander_unit_can_be_accessed_from_getCavalryUnits(){
            int expectedNumberOfUnits = 1;
            Unit CavalryUnit = new CavalryUnit("Cav", 1);
            Unit CommanderUnit = new CommanderUnit("Com", 1);
            armySimpleConstructor = new Army("ArmyWithOneCavUnit");

            armySimpleConstructor.add(CavalryUnit);
            armySimpleConstructor.add(CommanderUnit);
            int numberOfCavalryUnits = armySimpleConstructor.getCavalryUnits().size();

            assertEquals(expectedNumberOfUnits, numberOfCavalryUnits);
        }
    }

    @Test
    @DisplayName("Checks first if armyTest1 equals armyTest2 which it shouldn't" +
            "then it test if armyTest1 and armyTest3 equals which it should" +
            "the tests if armyTest4 with the same name as armyTest1 is equals which should also be true")
    void Test_if_Equals_method_return_true_or_false_based_on_situation() {
        String testName1 = "HumanArmyTest.csv";
        String testName2 = "Orc Army";

        Army armyTest1 = new Army(testName1);
        Army armyTest2 = new Army(testName2);
        Army armyTest3 = armyTest1;
        Army armyTest4 = new Army(testName1);

        assertNotEquals(armyTest1, armyTest2);
        assertTrue(armyTest1.equals(armyTest3));
        assertTrue(armyTest1.equals(armyTest4));

    }

    @Test
    void Test_if_HashCode_creates_different_hashCodes_for_different_armies() {
        String testName1 = "HumanArmyTest.csv";
        String testName2 = "Orc Army";

        Army armyTest1 = new Army(testName1);
        Army armyTest2 = new Army(testName2);
        Army armyTest3 = armyTest1;

        assertNotEquals(armyTest1.hashCode(), armyTest2.hashCode());
        assertEquals(armyTest1.hashCode(), armyTest3.hashCode());
    }

    @Test
    void Test_if_toString_method_returns_a_expected_string() {
        String expectedToString = "HumanArmyTest.csv\nunits=[]";
        String expectedArmyName = "HumanArmyTest.csv";
        Army armyTest1 = new Army(expectedArmyName);

        assertEquals(expectedToString, armyTest1.toString());
    }
}