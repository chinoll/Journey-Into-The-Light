package net.journey.dimension.cloudia;

import net.journey.dimension.cloudia.zone.*;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biome.SpawnListEntry;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.gen.IChunkGenerator;

import java.util.*;

public class ChunkProviderCloudia implements IChunkGenerator {

    private ArrayList bottomrooms;
    private ArrayList toprooms;
    private CloudiaEmptyChunk emptyChunk;
    private CloudiaZoneBase[] bridges;
    private World worldObj;
    private Random random;
    private Map chunkTileEntityMap;
    public ChunkProviderCloudia(World worldIn, long seed) {
        worldObj = worldIn;
        random = new Random(seed);
        bottomrooms = new ArrayList(4);
        bottomrooms.add(new CloudiaDungeon1());
        bottomrooms.add(new CloudiaAltar());
        bottomrooms.add(new CloudiaDungeon2());
        bottomrooms.add(new CloudiaGarden());

        emptyChunk = new CloudiaEmptyChunk();

        toprooms = new ArrayList(2);
        toprooms.add(new CloudiaHouse1());
        toprooms.add(new CloudiaAltarRoom1());

        bridges = new CloudiaZoneBase[]{new CloudiaBridgeAll()/*, new CloudiaBridgeNS(), new CloudiaBridgeEW()*/};
        this.chunkTileEntityMap = new HashMap();
    }

    @Override
    public Chunk generateChunk(int chunkX, int chunkZ) {
        CloudiaChunkPrimer cloudiaChunk = new CloudiaChunkPrimer();

        int bottomLayer = 32;
        int secondLayer = 51;

        //Generates all rooms
        CloudiaZoneBase room = (CloudiaZoneBase) (toprooms.get(random.nextInt(toprooms.size())));
        CloudiaZoneBase room2 = (CloudiaZoneBase) (bottomrooms.get(random.nextInt(bottomrooms.size())));

        int emptyRarity = 2;

        //These double as a hallway and a blocker on the exit of the room next to it
        int hallwayRarity = emptyRarity * 2;
        if (random.nextInt(hallwayRarity) == 0)
            bridges[random.nextInt(bridges.length)].generate(cloudiaChunk, random, 0, bottomLayer, 0);

        if (random.nextInt(hallwayRarity) == 0)
            bridges[random.nextInt(bridges.length)].generate(cloudiaChunk, random, 0, secondLayer, 0);

        room = (CloudiaZoneBase) (toprooms.get(random.nextInt(toprooms.size())));
        if (random.nextInt(emptyRarity) == 0)
            room.generate(cloudiaChunk, random, 0, secondLayer, 0);

        room2 = (CloudiaZoneBase) (bottomrooms.get(random.nextInt(bottomrooms.size())));
        if (random.nextInt(emptyRarity) == 0)
            room2.generate(cloudiaChunk, random, 0, bottomLayer, 0);

        //Chance to generate stair room on all but top layer


        //figure out what height to fill with air and how often, or just scrap the idea
        //int emptyRarity = 20;
        //if(random.nextInt(emptyRarity) == 0)
        //	emptyChunk.generate(cloudiaChunk, random, 0, bottomLayer, 0, 12);

        //if(random.nextInt(emptyRarity) == 0)
        //	emptyChunk.generate(cloudiaChunk, random, 0, secondLayer, 0, 15);

        //These rooms need to be generated last

        //Forces a roof over the whole room, gets generated at final set
        chunkTileEntityMap.put(new ChunkCoords(chunkX, chunkZ), cloudiaChunk.chunkTileEntityPositions);

        Chunk chunk = new Chunk(this.worldObj, cloudiaChunk, chunkX, chunkZ);
        chunk.generateSkylightMap();
        return chunk;
    }

    @Override
    public void populate(int cx, int cz) {

    }

    @Override
    public List<SpawnListEntry> getPossibleCreatures(EnumCreatureType creatureType, BlockPos pos) {
        Biome biome = this.worldObj.getBiome(pos);
        return biome.getSpawnableList(creatureType);
    }

    @Override
    public boolean generateStructures(Chunk chunkIn, int x, int z) {
        return false;
    }

    @Override
    public BlockPos getNearestStructurePos(World worldIn, String structureName, BlockPos position, boolean findUnexplored) {
        return null;
    }

    @Override
    public void recreateStructures(Chunk chunkIn, int x, int z) {
    }

    @Override
    public boolean isInsideStructure(World worldIn, String structureName, BlockPos pos) {
        return false;
    }

    public class ChunkCoords {
        public final int chunkCoordX;
        public final int chunkCoordZ;

        public ChunkCoords(int X, int Z) {
            this.chunkCoordX = X;
            this.chunkCoordZ = Z;
        }

        public boolean equals(Object o) {
            if (!(o instanceof ChunkCoords)) {
                return false;
            } else {
                ChunkCoords chunkCoords = (ChunkCoords) o;
                return chunkCoords.chunkCoordX == this.chunkCoordX && chunkCoords.chunkCoordZ == this.chunkCoordZ;
            }
        }

        public int hashCode() {
            return this.chunkCoordX + this.chunkCoordZ * 31;
        }
    }
}