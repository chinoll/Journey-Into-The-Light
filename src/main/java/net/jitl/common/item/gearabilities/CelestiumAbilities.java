package net.jitl.common.item.gearabilities;

import net.jitl.common.helper.TooltipHelper;
import net.minecraft.entity.LivingEntity;
import net.minecraftforge.event.entity.living.LivingEvent;

public class CelestiumAbilities extends BaseAbilities {
    private int cooldown = 0;
    private boolean isJumpReady = false;


    @Override
    public void doTickAbility(LivingEvent.LivingUpdateEvent event) {
        System.out.println("Dash cooldown for " + event.getEntityLiving().getName().getString() + ": " + cooldown);
        System.out.println("Dash for " + event.getEntityLiving().getName().getString() + " " + (isJumpReady ? "is" : "is not") + " ready");
        if (cooldown <= 0) {
            LivingEntity entity = event.getEntityLiving();
            if (entity.isOnGround() && !isJumpReady) {
                isJumpReady = true;
                //TODO: add particles
            }
        } else {
            cooldown--;
        }
    }

    public void doCharge(LivingEntity entity, double rotation) {
        if (isJumpReady) {
            entity.setDeltaMovement(Math.cos(rotation) * 0.75, 0, Math.sin(rotation) * 0.75);
            entity.hurtMarked = true;
            isJumpReady = false;
            cooldown = 40;
        }
    }

    @Override
    public void addArmorTooltip(TooltipHelper helper) {
        helper.addOverview();
        helper.addDetail();
        helper.addDrawback();
        /*text.add(new TranslationTextComponent("\u00a76Jumping in the air allows you to dash."));
        text.add(new TranslationTextComponent("\u00a7BDash cancels your vertical movement and increases your horizontal movement."));
        text.add(new TranslationTextComponent("\u00a7CTo recharge this ability, touch the ground at least 2 seconds after previous dash."));*/
    }
}
