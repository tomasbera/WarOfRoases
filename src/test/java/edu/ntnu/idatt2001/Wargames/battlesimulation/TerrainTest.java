package edu.ntnu.idatt2001.Wargames.battlesimulation;

import edu.ntnu.idatt2001.Wargames.battlesimulation.battle.Terrain;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class TerrainTest {

    @Test
    void test_for_getTerrainNum_gives_right_terrain_number_for_forest() {
        int expectedTerrainNumber = Terrain.FOREST.getTerrainNum();

        Terrain terrain = Terrain.FOREST;

        assertEquals(terrain.getTerrainNum(),expectedTerrainNumber);
    }

    @Test
    void test_for_getTerrainNum_gives_right_terrain_number_for_hill() {
        int expectedTerrainNumber = Terrain.HILL.getTerrainNum();

        Terrain terrain = Terrain.HILL;

        assertEquals(terrain.getTerrainNum(), expectedTerrainNumber);
    }

    @Test
    void test_for_getTerrainNum_gives_right_terrain_number_for_plains() {
        int expectedTerrainNumber = Terrain.PLAINS.getTerrainNum();

        Terrain terrain = Terrain.PLAINS;

        assertEquals(terrain.getTerrainNum(), expectedTerrainNumber);
    }
}