package com.wzh.builder;

/**
 * @author wzh
 * @date 2020-06-08 21:44
 */
public class ComplexTerrainBuilder implements TerrainBuilder {
    Terrain terrain=new Terrain();

    @Override
    public TerrainBuilder builderWall() {
        terrain.w=new Wall(10,10,50,50);
        return this;
    }

    @Override
    public TerrainBuilder builderFort() {
        terrain.f=new Fort(10,10,50,50);
        return this;
    }

    @Override
    public TerrainBuilder builderMine() {
        terrain.m=new Mine(10,10,50,50);
        return this;
    }

    @Override
    public Terrain builder() {
        return terrain;
    }
}
