package net.journey.items.swords;

import java.util.List;
import java.util.Random;

import net.journey.client.render.particles.EntityDoomFX;
import net.journey.client.render.particles.EntityHellstoneFX;
import net.journey.client.render.particles.EntityModFireFX;
import net.journey.client.render.particles.EntityModLavaFX;
import net.journey.client.render.particles.EntityPoisionFX;
import net.journey.util.EssenceToolMaterial;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraftforge.fml.client.FMLClientHandler;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.slayer.api.SlayerAPI;
import net.slayer.api.item.ItemModSword;

public class ItemRegenSword extends ItemModSword {

	public ItemRegenSword(String name, String f , EssenceToolMaterial toolMaterial) {
		super(name, f, toolMaterial);
	}

	@Override
	public boolean hitEntity(ItemStack par1ItemStack, EntityLivingBase hit, EntityLivingBase player) {
		player.addPotionEffect(new PotionEffect(Potion.regeneration.id, 100, 5));
		addParticles(hit);
		return super.hitEntity(par1ItemStack, hit, player);
	}
	
	@SideOnly(Side.CLIENT)
	public void addParticles(EntityLivingBase hit) {
		Random r = new Random();
		for(int i = 0; i < 20; i++){
			FMLClientHandler.instance().getClient().effectRenderer.addEffect(new EntityHellstoneFX(hit.worldObj, hit.posX + r.nextFloat() - 0.5F, hit.posY + 0.5D + r.nextFloat(), hit.posZ + r.nextFloat() - 0.5F, 0.0D, 0.0D, 0.0D));
		}
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack item, EntityPlayer player, List infoList, boolean par4) {
		infoList.add(SlayerAPI.Colour.RED + "On hit: Grants player regeneration");
		if(item.getMaxDamage() != -1) infoList.add(item.getMaxDamage() - item.getItemDamage() + " Uses Remaining");
	}
}