package net.slayer.api.block;

import net.journey.JourneyBlocks;
import net.journey.JourneyItems;
import net.journey.JourneyTabs;
import net.journey.util.LangRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.BlockFence;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.slayer.api.SlayerAPI;

public class BlockModFence extends BlockFence {

    public String name;

    public BlockModFence(Block block, String name, String finalName, boolean light) {
        super(Material.ROCK, null);
        this.name = name;
        LangRegistry.addBlock(name, finalName);
        setTranslationKey(name);
        setCreativeTab(JourneyTabs.blocks);
        if (light) setLightLevel(0.5F);
        setHardness(block.getBlockHardness(null, null, null));
        JourneyBlocks.blockName.add(SlayerAPI.PREFIX + name);
        JourneyBlocks.blocks.add(this);
        setRegistryName(SlayerAPI.MOD_ID, name);

        JourneyItems.items.add(new ItemBlock(this).setRegistryName(this.getRegistryName()));
    }

    public BlockModFence(Block b, String n, String finalName) {
        this(b, n, finalName, false);
        this.name = n;
    }

    public Item createItemBlock() {
        return new ItemBlock(this).setRegistryName(getRegistryName());
    }

	
	/*@Override
	public boolean canConnectTo(IBlockAccess blockAccess, BlockPos pos) {
		Block block = blockAccess.getBlockState(pos).getBlock();
		 return 
				 block == Blocks.barrier ? false : ((!(
				 block instanceof BlockFence) || 
				 block.getMaterial() != this.blockMaterial) && !(
				 block instanceof BlockFenceGate) ? (
				 block.getMaterial().isOpaque() && 
				 block.isFullCube() ? 
				 block.getMaterial() != Material.gourd : false) : true);
	}*/
}