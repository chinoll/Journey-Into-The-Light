package net.jitl.common.helper;

import mcp.MethodsReturnNonnullByDefault;
import net.jitl.JITL;
import net.jitl.common.item.gearabilities.BaseAbilities;
import net.jitl.common.item.gearabilities.IGearAbilities;
import net.jitl.common.item.gearabilities.ShadiumAbilities;
import net.jitl.init.JItems;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.IArmorMaterial;
import net.minecraft.item.Item;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;

public enum JArmorMaterial implements IArmorMaterial {

    //Change these
    SAPPHIRE("sapphire", 27, new int[]{3, 6, 8, 3}, 0.5F, 0.2F, SoundEvents.ARMOR_EQUIP_IRON, JItems.SAPPHIRE, new BaseAbilities()),
    LUNIUM("lunium", 27, new int[]{3, 6, 8, 3}, 0.5F, 0.2F, SoundEvents.ARMOR_EQUIP_IRON, JItems.LUNIUM_INGOT, new BaseAbilities()),
    SHADIUM("shadium", 27, new int[]{3, 6, 8, 3}, 0.5F, 0.2F, SoundEvents.ARMOR_EQUIP_IRON, JItems.SHADIUM_INGOT, new ShadiumAbilities()),
    BLOODCRUST("bloodcrust", 27, new int[]{3, 6, 8, 3}, 0.5F, 0.2F, SoundEvents.ARMOR_EQUIP_IRON, JItems.BLOODCRUST_INGOT, new BaseAbilities()),
    CELESTIUM("celestium", 27, new int[]{3, 6, 8, 3}, 0.5F, 0.2F, SoundEvents.ARMOR_EQUIP_IRON, JItems.CELESTIUM_INGOT, new BaseAbilities());

    private final String name;
    private final int durabilityMultiplier;
    private final float toughness, knockback;
    private final int[] protectionPerPiece;
    private final SoundEvent slotIn;
    private final Ingredient repairItem;
    private static final int[] BASE_DURABILITY = new int[]{13, 15, 16, 11};
    private final IGearAbilities abilities;

    JArmorMaterial(String name, int dur, int[] protection, float toughness, float knockback, SoundEvent putIn, Item repair, IGearAbilities abilityManager) {
        this.name = name;
        this.durabilityMultiplier = dur;
        this.protectionPerPiece = protection;
        this.slotIn = putIn;
        this.toughness = toughness;
        this.knockback = knockback;
        this.repairItem = Ingredient.of(repair);
        this.abilities = abilityManager;
    }

    @Override
    public int getDurabilityForSlot(EquipmentSlotType slot) {
        return BASE_DURABILITY[slot.getIndex()] * this.durabilityMultiplier;
    }

    @Override
    public int getDefenseForSlot(EquipmentSlotType slotIn) {
        return protectionPerPiece[slotIn.getIndex()];
    }

    @Override
    public int getEnchantmentValue() {
        return 30;
    }

    @MethodsReturnNonnullByDefault
    @Override
    public SoundEvent getEquipSound() {
        return slotIn;
    }

    @MethodsReturnNonnullByDefault
    @Override
    public Ingredient getRepairIngredient() {
        return repairItem;
    }

    /**
     * This sets the model texture name
     */
    @Override
    public String getName() {
        return JITL.MODID + ":" + this.name;
    }

    @Override
    public float getToughness() {
        return this.toughness;
    }

    @Override
    public float getKnockbackResistance() {
        return this.knockback;
    }

    public IGearAbilities getAbilities() {
        return this.abilities;
    }
}