package edu.ntnu.idatt2001.Wargames.battlesimulation;

import edu.ntnu.idatt2001.Wargames.battlesimulation.battle.Army;
import edu.ntnu.idatt2001.Wargames.battlesimulation.battle.ArmyFiles;
import edu.ntnu.idatt2001.Wargames.battlesimulation.specificunits.InfantryUnit;
import edu.ntnu.idatt2001.Wargames.battlesimulation.specificunits.RangedUnit;
import edu.ntnu.idatt2001.Wargames.battlesimulation.specificunits.Unit;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import java.io.File;
import java.io.IOException;
import java.nio.file.NoSuchFileException;
import static org.junit.jupiter.api.Assertions.*;

public class armyFilesTest {

    @Test
    public void Test_if_createCSVString_creates_string_of_a_Unit(){
        String expectedString = "InfantryUnit,Footman,10"+"\n";
        Unit InfantryTest = new InfantryUnit("Footman", 10);

        String equalString = ArmyFiles.makeCSVString(InfantryTest);

        assertEquals(expectedString, equalString);
    }

    @Nested
    public class Test_makeCVSFile_method_and_exceptions {

        @Test
        public void Test_if_makeCSVFile_makes_a_file_in_armyFile_in_CSV_format() throws IOException {
            Unit InfantryTest = new InfantryUnit("Footman", 10);
            Unit RangeTest = new RangedUnit("Bowman", 10);
            Army armyTest = new Army("Human Army");

            armyTest.add(InfantryTest);
            armyTest.add(RangeTest);
            File file = ArmyFiles.makeCSVFile(armyTest);

            assertNotNull(file);
            file.delete();
        }

        @Test
        public void Test_if_makeCVSFile_throws_IllegalArgumentException_when_a_file_already_exists(){
            Army armyFileExisting = new Army("ExsistingFiletest");

            assertThrows(IllegalArgumentException.class, () -> ArmyFiles.makeCSVFile(armyFileExisting));
        }
    }

    @Nested
    public class Test_readFromCVS_method_and_exceptions{

        @Test
        public void Test_if_readFromCSV_creates_a_army_of_a_wanted_file() throws IOException {
            int expectedUnitSize = 2;
            String pathToFile = "src/main/resources/armyFiles/HumanArmyTest.csv";
            ArmyFiles armyFiles = new ArmyFiles();

            Army armyFileTest = armyFiles.readFromCSV(pathToFile);

            assertEquals(expectedUnitSize, armyFileTest.getUnits().size());
        }

        @Test
        public void Test_if_readFromCSV_throws_IO_exception_when_no_path_existing(){
            ArmyFiles armyFiles = new ArmyFiles();

            assertThrows(IOException.class, () -> armyFiles.readFromCSV(""));
        }

        @Test
        public void Test_if_readFromCSV_throws_NullPointerException_when_file_has_no_units(){
            ArmyFiles armyFiles = new ArmyFiles();

            assertThrows(NullPointerException.class, () -> armyFiles.readFromCSV("src/main/resources/armyFiles/NullArmyTest.csv"));
        }

        @Test
        public void Test_if_readFromCSV_throws_IllegalArgumentException_when_file_not_readable(){
            ArmyFiles armyFiles = new ArmyFiles();

            assertThrows(IllegalArgumentException.class, () -> armyFiles.readFromCSV("src/main/resources/armyFiles/NotReadableFile.csv"));
        }

        @Test
        public void Test_if_readFromCSV_throws_NoSuchFileException_when_a_file_doenst_exits(){
            ArmyFiles armyFiles = new ArmyFiles();

            assertThrows(NoSuchFileException.class, () -> armyFiles.readFromCSV("NoFileExist"));
        }


        @Test
        public void Test_if_file_is_corrupt_or_has_illegal_characters_throw_IllegalArgumentException(){
            ArmyFiles armyFiles = new ArmyFiles();

            assertThrows(IllegalArgumentException.class, () -> {
                armyFiles.readFromCSV("src/main/resources/armyfiles/IllegalCharacters.csv");
            });
        }

        @Test
        public void Test_if_file_has_health_as_a_string_and_throws_IllegalArgumentException(){
            ArmyFiles armyFiles = new ArmyFiles();

            assertThrows(IllegalArgumentException.class, () -> {
                armyFiles.readFromCSV("src/main/resources/armyfiles/IntegerInnputString.csv");
            });
        }
    }
}
