package net.journey.client;

import net.journey.client.server.EssenceProvider;
import net.journey.client.server.IEssence;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.slayer.api.SlayerAPI;

public class JourneyCapabilityHandler {

	public static final ResourceLocation ESSENCE_CAP = new ResourceLocation(SlayerAPI.MOD_ID, "essence_mana");

	@SubscribeEvent
	public void attachCapability(AttachCapabilitiesEvent<Entity> event) {
		if(!(event.getObject() instanceof EntityPlayer)) return;

		event.addCapability(ESSENCE_CAP, new EssenceProvider());
	}
}