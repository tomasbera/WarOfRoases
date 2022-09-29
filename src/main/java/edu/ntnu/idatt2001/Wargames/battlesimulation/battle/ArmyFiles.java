package edu.ntnu.idatt2001.Wargames.battlesimulation.battle;

import edu.ntnu.idatt2001.Wargames.battlesimulation.controllers.SimulationSingleton;
import edu.ntnu.idatt2001.Wargames.battlesimulation.specificunits.Unit;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * ArmyFiles class is used for creating and reading from local files
 * and transfer these to armies.
 *
 * @author Tomas Beranek
 */
public class ArmyFiles {

    /**
     * method that creates a file from an army with the armys name
     *
     * @param army the army you want to create a file of
     * @return File with the new army
     * @throws IOException if file not found, or file is wrong throws exception
     */
    public static File makeCSVFile(Army army) throws IOException {
        //makes a new file in the armyFile folder with the army name
        File armyFile = new File("src/main/resources/ArmyFiles/" + army.getName().trim() + ".csv");

        //checks if file is already in registry
        if (armyFile.exists() && !armyFile.isDirectory()) {
            throw new IllegalArgumentException("This ArmyFile is already in the registry");
        }

        Writer writer = new FileWriter(armyFile.getAbsolutePath());
        writer.write(armyFile.getName() + "\n");

        // puts all units in an army to a specific list
        army.getUnits()
                .forEach(unit -> {
                    try {
                        writer.write(makeCSVString(unit));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });

        writer.close();
        return armyFile;
    }

    /**
     * method that takes a unit and converts it to a string of csv type
     *
     * @param u the unit you want to convert
     * @return string of csv type of the specific unit
     */
    public static String makeCSVString(Unit u) {
        return u.getClass().getSimpleName() + "," + u.getName() + "," + u.getHealth() + "\n";
    }

    /**
     * method that takes a file and creates an army form its context
     *
     * @return the new Army created from the file
     * @throws IOException any mistakes with the file will throw an exception
     */
    public Army readFromCSV(String armyPath) throws IOException {
        UnitFactory unitFactory = new UnitFactory();
        String armyName = Files.readAllLines(Paths.get(armyPath)).get(0).replace(".csv", "");

        Army newArmy = new Army(armyName);
        List<Unit> newUnits = new ArrayList<>();
        Files.lines(Path.of(armyPath))
                .skip(1)
                .forEach(line -> {
                    String[] column = line.split(",");
                    if (checkForCharacters(line)) {
                        if (column.length != 3) throw new IllegalArgumentException("File is not readable");
                        checkInputForIntegers(column[2]);
                        newUnits.add(unitFactory.newUnitFactory(column[0], column[1], Integer.parseInt(column[2])));
                    }
                });
        if (newUnits.size() == 0) throw new NullPointerException("This file doesnt contain any units");

        newArmy.addAll(newUnits);
        return newArmy;
    }

    /**
     * this method is used for file selection from local files.
     * it will only allow a .csv file
     *
     * @return returns file if file not null
     * @website https://gitlab.stud.idi.ntnu.no/carljgu/Wargames/-/blob/master/src/main/java/edu/ntnu/idatt2001/carljgu/client/controllers/BattleController.java
     * <p>
     * filechooser was showed to me by a friend, and a therefore link his gitlab here
     * this method is a little inspired by his method, but doesnt look alike
     * </p>
     */
    public String getFilePath() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select army from file");
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("CSV", "*.csv"));
        File file = fileChooser.showOpenDialog(SimulationSingleton.getInstance().getStage());
        if (file != null)
            return file.getPath();
        return null;
    }

    /**
     * method used for checking for illegal characters which can corrupt a file
     *
     * @param lines a line from the file
     * @return true if the line doesn't consist of illegal characters
     * @throws IllegalArgumentException if an illegal character is found
     */
    public static boolean checkForCharacters(String lines) throws IllegalArgumentException {
        String[] columns = lines.split(",");
        for (String column : columns) {
            String checkForIllegal = "[a-zA-Z0-9]+";
            Pattern pt = Pattern.compile(checkForIllegal);
            Matcher mt = pt.matcher(column);

            if (!mt.matches()) {
                throw new IllegalArgumentException("Illegal character found, file may be corrupted");
            }
        }
        return true;
    }

    /**
     * checks if string consist of numbers
     *
     * @param lines string to be checked
     */
    public void checkInputForIntegers(String lines) {
        String chekIfInt = "[0-9]+";
        Pattern pt = Pattern.compile(chekIfInt);
        Matcher mt = pt.matcher(lines);

        if (!mt.matches()) {
            throw new IllegalArgumentException("Health value in file is a String, not a Integer");
        }
    }
}
