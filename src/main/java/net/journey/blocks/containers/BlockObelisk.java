package net.journey.blocks.containers;

import net.journey.api.block.IHasCustomItemPath;
import net.journey.api.block.IHasTeisr;
import net.journey.blocks.tileentity.TileEntityObelisk;
import net.journey.client.render.block.JourneyChestTESR;
import net.journey.client.render.block.ObeliskRenderer;
import net.journey.client.render.model.block.ModelCloudAltar;
import net.journey.client.render.model.block.ModelObelisk;
import net.journey.client.render.particles.EntityBoilPotalFX;
import net.journey.client.render.particles.EntityHellstoneFX;
import net.journey.client.render.particles.EntityKnowledgeTableFX;
import net.journey.init.JourneySounds;
import net.journey.init.JourneyTabs;
import net.journey.init.blocks.JourneyBlocks;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.particle.ParticleSmokeNormal;
import net.minecraft.client.renderer.tileentity.TileEntityItemStackRenderer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.client.FMLClientHandler;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.slayer.api.SlayerAPI;
import net.slayer.api.block.BlockMod;
import net.slayer.api.entity.tileentity.container.BlockModContainer;

import java.util.Random;

import org.jetbrains.annotations.NotNull;

public class BlockObelisk extends BlockModContainer implements IHasTeisr, IHasCustomItemPath {

    private AxisAlignedBB size = new AxisAlignedBB(0.0F, 0.0F, 0.0F, 1.0F, 2.8F, 1.0F);
    
    public BlockObelisk(String name, String finalName) {
        super(name, finalName);
        setCreativeTab(JourneyTabs.MACHINE_BLOCKS);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void randomDisplayTick(IBlockState stateIn, World w, BlockPos pos, Random random) {
        if (random.nextInt(10) == 0)
            w.playSound((double) pos.getX() + 0.5D, (double) pos.getY() + 0.5D, (double) pos.getZ() + 0.5D, JourneySounds.OBELISK_IDLE, SoundCategory.BLOCKS, 0.5F, rand.nextFloat() * 0.4F + 0.8F, false);
        for (int i = 0; i < 4; ++i) {
            double d0 = (float) pos.getX() + rand.nextFloat();
            double d1 = (float) pos.getY() + rand.nextFloat();
            double d2 = (float) pos.getZ() + rand.nextFloat();
            double d3 = (rand.nextFloat() - 0.5D) * 0.5D;
            double d4 = (rand.nextFloat() - 0.5D) * 0.5D;
            double d5 = (rand.nextFloat() - 0.5D) * 0.5D;
            w.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, d0, d1, d2, d3, d4, d5, 5);
           // FMLClientHandler.instance().getClient().effectRenderer.addEffect(var20);
        }
    }

	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta) {
		return new TileEntityObelisk();
	}


    @Override
    public EnumBlockRenderType getRenderType(IBlockState state) {
        return EnumBlockRenderType.INVISIBLE;
    }

    @SideOnly(Side.CLIENT)
    public BlockRenderLayer getRenderLayer() {
        return BlockRenderLayer.SOLID;
    }

    @Override
    public boolean isOpaqueCube(IBlockState state) {
        return false;
    }

    @Override
    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
        return size;
    }

    @Override
    public AxisAlignedBB getCollisionBoundingBox(IBlockState blockState, IBlockAccess worldIn, BlockPos pos) {
        return size;
    }
    
	@NotNull
	@Override
	public TileEntityItemStackRenderer createTeisr() {
		return new ObeliskRenderer.ObeliskTEISR();
	}

	@NotNull
	@Override
	public ResourceLocation getItemModelResourceLocation() {
		return new ResourceLocation(SlayerAPI.MOD_ID, "block/obselisk");
	}
}
