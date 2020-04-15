package net.journey.dimension.depths.gen;

import net.journey.JourneyBlocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;

import java.util.Random;

public class WorldGenPlant1 extends WorldGenAbstractTree {

    private static final Random rand = new Random();
    private final int minTreeHeight;
    private final int metaWood;
    private final int metaLeaves;

    public WorldGenPlant1(boolean var1) {
        this(var1, rand.nextInt(10) + 10, 0, 0);
    }

    public WorldGenPlant1(boolean var1, int var2, int var3, int var4) {
        super(var1);
        this.minTreeHeight = var2;
        this.metaWood = var3;
        this.metaLeaves = var4;
    }

    @Override
    public boolean generate(World world, Random r, BlockPos pos) {
        int i = pos.getX() - 6, j = 2, k = pos.getZ() - 6;
        this.setBlockAndNotifyAdequately(world, new BlockPos(i + 0, j + 0, k + 0), JourneyBlocks.darkbloomBottom.getDefaultState());
        this.setBlockAndNotifyAdequately(world, new BlockPos(i + 0, j + 1, k + 0), JourneyBlocks.darkbloomBottom.getDefaultState());
        this.setBlockAndNotifyAdequately(world, new BlockPos(i + 0, j + 2, k + 0), JourneyBlocks.darkbloomBottom.getDefaultState());
        this.setBlockAndNotifyAdequately(world, new BlockPos(i + 0, j + 3, k + 0), JourneyBlocks.darkbloomBottom.getDefaultState());
        this.setBlockAndNotifyAdequately(world, new BlockPos(i + 0, j + 4, k + 0), JourneyBlocks.darkbloomTop.getDefaultState());
        return true;

    }
}