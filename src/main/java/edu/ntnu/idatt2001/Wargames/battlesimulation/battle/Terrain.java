package edu.ntnu.idatt2001.Wargames.battlesimulation.battle;

/**
 * Enum class created for terrain, which allows an easy use of terrain in program
 *
 * @author Tomas Beranek
 */
public enum Terrain {

    HILL(1),
    PLAINS(2),
    FOREST(3);
    private final int terrainNum;

    /**
     * constructor that gives a terrain a number
     *
     * @param terrainNum number for that terrain
     */
    Terrain(int terrainNum) {
        this.terrainNum = terrainNum;
    }

    /**
     * method that gets the terrain number
     *
     * @return terrain number as an integer
     */
    public int getTerrainNum() {
        return terrainNum;
    }
}
