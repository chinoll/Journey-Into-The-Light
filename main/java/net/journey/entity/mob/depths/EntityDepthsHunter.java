package net.journey.entity.mob.depths;

import java.util.List;

import net.journey.entity.MobStats;
import net.journey.enums.EnumSounds;
import net.journey.util.PotionEffects;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.world.World;
import net.slayer.api.entity.EntityModMob;

public class EntityDepthsHunter extends EntityModMob{

	public EntityDepthsHunter(World par1World) {
		super(par1World);
		addAttackingAI();
		setSize(1.2F, 2.5F);
	}
	
	@Override
    public void onLivingUpdate() {
        super.onLivingUpdate();
        if(this.world.isDaytime() && !this.world.isRemote) {
            float var1 = getBrightness();
        }
        
        List<Entity> e = this.world.getEntitiesWithinAABBExcludingEntity(this, this.getEntityBoundingBox());        
        for(Entity entity : e) {
        	if(entity instanceof EntityPlayer && canEntityBeSeen(entity)) ((EntityPlayer)entity).addPotionEffect(PotionEffects.setPotionEffect(PotionEffects.blindness, 60, 1));
        }        
    }

	@Override
	public double setAttackDamage(MobStats s) {
		return MobStats.baseJourneyDamage;
	}

	@Override
	public double setMaxHealth(MobStats s) {
		return MobStats.depthsHealth;
	}

	@Override
	public EnumSounds setLivingSound() {
		return EnumSounds.DEPTHS_HUNTER;
	}

	@Override
	public EnumSounds setHurtSound() {
		return EnumSounds.DEPTHS_HUNTER_HURT;
	}

	@Override
	public EnumSounds setDeathSound() {
		return EnumSounds.DEPTHS_HUNTER_HURT;
	}
	
	@Override
	public Item getItemDropped() {
		return null;
	}
}