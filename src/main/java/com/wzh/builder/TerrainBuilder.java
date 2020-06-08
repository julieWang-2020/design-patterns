package com.wzh.builder;

/**
 * @author wzh
 * @date 2020-06-08 21:43
 */
public interface TerrainBuilder {

    TerrainBuilder builderWall();
    TerrainBuilder builderFort();
    TerrainBuilder builderMine();
    Terrain builder();

}
