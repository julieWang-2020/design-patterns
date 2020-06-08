package com.wzh.builder;

/**
 * @author wzh
 * @date 2020-06-08 21:48
 */
public class BuilderMain {

    public static void main(String[] args) {
        TerrainBuilder builder=new ComplexTerrainBuilder();
        Terrain t=builder.builderFort().builderMine().builderWall().builder();

        Person p= new Person.PersonBuilder()
                .basicInfo(1,"test",21)
                .weight(100)
                .score(50)
                .location("street","room")
                .builder();
    }
}
