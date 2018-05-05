package net.journey.entity.mob.overworld;

import net.journey.JourneyItems;
import net.journey.entity.MobStats;
import net.journey.enums.EnumSounds;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.slayer.api.entity.EntityModMob;

public class EntitySpyclops extends EntityModMob {

	public static final int ENTITY_TYPE = 24;
	
	public EntitySpyclops(World par1World) {
		super(par1World);
		addAttackingAI();
		setSize(1.5F, 2.0F);
	}
	
	@Override
	public boolean getCanSpawnHere() {
		return 
			   this.world.getBlockState(new BlockPos(this.posX, this.posY-1, this.posZ)).getBlock() == Blocks.GRASS || 
			   		this.world.getBlockState(new BlockPos(this.posX, this.posY-1, this.posZ)).getBlock() == Blocks.LEAVES || 
			   			this.world.getBlockState(new BlockPos(this.posX, this.posY-1, this.posZ)).getBlock() == Blocks.SAND || 
			   				this.world.getBlockState(new BlockPos(this.posX, this.posY-1, this.posZ)).getBlock() == Blocks.DIRT;
	}

	@Override
	public double setAttackDamage(MobStats s) {
		return MobStats.lowJourneyDamage;
	}

	@Override
	public double setMaxHealth(MobStats s) {
		return MobStats.overworldHealth;
	}

	@Override
	public EnumSounds setLivingSound() {
		return EnumSounds.SPYCLOPS;
	}

	@Override
	public EnumSounds setHurtSound() {
		return EnumSounds.SPYCLOPS;
	}

	@Override
	public EnumSounds setDeathSound() {
		return EnumSounds.SPYCLOPS_HURT;
	}

	@Override
	public Item getItemDropped() {
		return JourneyItems.spyclopseEye;
	}
}