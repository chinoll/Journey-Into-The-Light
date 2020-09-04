package net.journey.integration.tconstruct;

import net.journey.JITL;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.ItemMeshDefinition;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.block.statemap.StateMapperBase;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.Fluid;

import javax.annotation.Nonnull;

public class TCFluidStateMapper extends StateMapperBase implements ItemMeshDefinition {

	public final Fluid fluid;
	public final ModelResourceLocation location;

	public TCFluidStateMapper(Fluid fluid) {
		this.fluid = fluid;
		this.location = new ModelResourceLocation(JITL.rl("tc_fluid_block"), fluid.getName());
	}

	@Nonnull
	@Override
	protected ModelResourceLocation getModelResourceLocation(@Nonnull IBlockState state) {
		return location;
	}

	@Nonnull
	@Override
	public ModelResourceLocation getModelLocation(@Nonnull ItemStack stack) {
		return location;
	}
}