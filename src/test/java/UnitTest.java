import edu.ntnu.idatt2001.Wargames.battlesimulation.specificunits.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * A unit test class that tests all war units, and the unit blueprint
 */

public class UnitTest{

    @Test
    public void Test_if_Exception_Thrown_IfName_Is_Blank() {
        String failName = "";

        try {
            Unit unit = new InfantryUnit(failName, 1);
            fail("This constructor should throw an IllegalArgumentException");
        } catch (IllegalArgumentException e) {

            assertTrue(true);
        }
    }

    @Test
    public void Test_if_Exception_Thrown_If_Health_Is_Less_Than_Zero() {
        int failHealth = -1;

        try {
            Unit unit = new InfantryUnit("name", failHealth);
            fail("This constructor should throw IllegalArgumentException");
        } catch (IllegalArgumentException e) {

            assertTrue(true);
        }
    }

    @Test
    public void Test_If_Constructor_creates_a_Infantry () {
        Unit nullUnit = null;

        Unit InfantryTest = new InfantryUnit("Test",1,1,1,1);

        assertNotEquals(nullUnit, InfantryTest);
    }


    @Test
    public void Test_If_Constructor_creates_a_Range () {
        Unit nullUnit = null;

        Unit RangeTest = new RangedUnit("Test",1,1,1,1);

        assertNotEquals(nullUnit, RangeTest);
    }

    @Test
    public void Test_If_Constructor_creates_a_Cavalry () {
        Unit nullUnit = null;

        Unit CavalryTest = new CavalryUnit("Test",1,1,1,1);

        assertNotEquals(nullUnit, CavalryTest);
    }

    @Test
    public void Test_If_Constructor_creates_a_Commander () {
        Unit nullUnit = null;

        Unit CommanderTest = new CommanderUnit("Test",1,1,1,1);

        assertNotEquals(nullUnit, CommanderTest);
    }

    @Test
    public void Test_If_Constructor_creates_a_Infantry_Easier_Constructor () {
        Unit nullUnit = null;

        Unit InfantryTest = new InfantryUnit("Test",1);

        assertNotEquals(nullUnit, InfantryTest);
    }

    @Test
    public void Test_If_Constructor_creates_a_Range_Easier_Constructor () {
        Unit nullUnit = null;

        Unit RangeTest = new RangedUnit("Test",1);

        assertNotEquals(nullUnit, RangeTest);
    }
    @Test
    public void Test_If_Constructor_creates_a_Cavalry_Easier_Constructor () {
        Unit nullUnit = null;

        Unit CavalryTest = new CavalryUnit("Test",1);

        assertNotEquals(nullUnit, CavalryTest);
    }

    @Test
    public void Test_If_Constructor_creates_a_Commander_Easier_Constructor () {
        Unit nullUnit = null;

        Unit CommanderTest = new CommanderUnit("Test",1,1,1,1);

        assertNotEquals(nullUnit, CommanderTest);
    }
}
