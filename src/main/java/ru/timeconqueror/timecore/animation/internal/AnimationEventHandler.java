package ru.timeconqueror.timecore.animation.internal;

import net.minecraft.entity.EntityLivingBase;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import ru.timeconqueror.timecore.api.animation.ActionController;
import ru.timeconqueror.timecore.api.animation.AnimationProvider;

@Mod.EventBusSubscriber
public class AnimationEventHandler {

	@SubscribeEvent
	public static void onEntityTick(LivingEvent.LivingUpdateEvent event) {
		EntityLivingBase entityLiving = event.getEntityLiving();

		if (entityLiving instanceof AnimationProvider<?>) {
			if (entityLiving.isServerWorld()) {
				//needed for animation ticking on server side.
				((AnimationProvider<?>) entityLiving).getActionController().getAnimationManager().applyAnimations(null);
			} else {
				ActionController<?> actionController = ((AnimationProvider<?>) entityLiving).getActionController();
				actionController.onTick();
			}
		}
	}
}
