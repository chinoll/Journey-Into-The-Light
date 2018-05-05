package net.slayer.api.item;

import java.util.List;

import net.journey.JourneyItems;
import net.journey.JourneyTabs;
import net.journey.client.ItemDescription;
import net.journey.util.LangRegistry;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemModFood extends ItemFood {

	private int time = 32;
	
    public ItemModFood(String name, String f, int food, float sat, boolean wolfFood) {
        super(food, sat, wolfFood);
        LangRegistry.addItem(name, f);
        setUnlocalizedName(name);
        setCreativeTab(JourneyTabs.crops);
        JourneyItems.itemNames.add(name);
    }
    
    public ItemModFood(String name, String f, int food, float sat, int timeToEat, boolean wolfFood) {
       this(name, f, food, sat, wolfFood);
       time = timeToEat;
    }
    
    @Override
    public int getMaxItemUseDuration(ItemStack stack) {
    	return time;
    }

    public ItemModFood(String name, String f, int food, float sat, boolean wolfFood, PotionEffect potionID, float potionEffectProbability) {
        this(name, f, food, sat, wolfFood);
        setPotionEffect(potionID, potionEffectProbability);
    }
    
    @Override
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
		ItemDescription.addInformation(stack, worldIn, tooltip);
		tooltip.add("Fills " + (double) getHealAmount(stack) / 2 + " Hunger Bars");
		tooltip.add(getSaturationModifier(stack) + " Saturation");
        if(time <= 32) tooltip.add("Faster eating");
    }
}