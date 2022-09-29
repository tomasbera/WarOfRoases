package edu.ntnu.idatt2001.Wargames.battlesimulation;

import edu.ntnu.idatt2001.Wargames.battlesimulation.battle.UnitFactory;
import edu.ntnu.idatt2001.Wargames.battlesimulation.specificunits.Unit;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


public class UnitFactoryTest {

    @Nested
    public class Tests_for_newUnitFactory_method {

        @Test
        public void Test_if_method_creates_a_infantryUnit() {
            Unit nullUnit = null;
            Unit expectedInfantryUnit;
            UnitFactory uf = new UnitFactory();

            expectedInfantryUnit = uf.newUnitFactory("InfantryUnit", "name", 1);

            assertNotEquals(nullUnit, expectedInfantryUnit);
        }

        @Test
        public void Test_if_method_creates_a_RangedUnit() {
            Unit nullUnit = null;
            Unit expectedRangedUnit;
            UnitFactory uf = new UnitFactory();

            expectedRangedUnit = uf.newUnitFactory("RangedUnit", "name", 1);

            assertNotEquals(nullUnit, expectedRangedUnit);
        }

        @Test
        public void Test_if_method_creates_a_CavalryUnit() {
            Unit nullUnit = null;
            Unit expectedCavalryUnit;
            UnitFactory uf = new UnitFactory();

            expectedCavalryUnit = uf.newUnitFactory("CavalryUnit", "name", 1);

            assertNotEquals(nullUnit, expectedCavalryUnit);
        }

        @Test
        public void Test_if_method_creates_a_CommanderUnit() {
            Unit nullUnit = null;
            Unit expectedCommanderUnit;
            UnitFactory uf = new UnitFactory();

            expectedCommanderUnit = uf.newUnitFactory("CommanderUnit", "name", 1);

            assertNotEquals(nullUnit, expectedCommanderUnit);
        }

        @Test
        public void Test_if_method_throws_exception_when_unitType_not_found() {
            String unitTypeDoesntExist = "abcd";

            UnitFactory uf = new UnitFactory();

            assertThrows(NullPointerException.class, () -> {
                uf.newUnitFactory(unitTypeDoesntExist, "name", 1);
            });
        }
    }

    @Nested
    public class  Tests_for_newNumberOfUnits_method {

        @Test
        public void Test_if_method_creates_n_number_of_a_unit(){
            int expectedNumberOfUnits = 10;

            UnitFactory uf = new UnitFactory();
            List<Unit> listTest = uf.newNumberOfUnits("InfantryUnit", "name", 1, expectedNumberOfUnits);

            assertEquals(10, listTest.size());
        }

        @Test
        public void Test_if_NullPointerException_Thrown_when_number_of_new_unit_is_zero(){
            int numberOfUnitCantBeZero = 0;

            UnitFactory unitFactory = new UnitFactory();

            assertThrows(NullPointerException.class, () -> {
                List<Unit> listTest = unitFactory.newNumberOfUnits("InfantryUnit", "name", 1, numberOfUnitCantBeZero);
            });
        }

        @Test
        public void Test_if_NullPointerException_Thrown_when_number_of_new_unit_is_less_than_zero(){
            int numberOfUnitsCantBeLessThanZero = -1;

            UnitFactory uf = new UnitFactory();

            assertThrows(NullPointerException.class, () -> {
                List<Unit> listTest = uf.newNumberOfUnits("InfantryUnit", "name", 1, numberOfUnitsCantBeLessThanZero);
            });
        }
    }
}