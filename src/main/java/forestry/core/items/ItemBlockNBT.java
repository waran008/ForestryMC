/*******************************************************************************
 * Copyright (c) 2011-2014 SirSengir.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU Lesser Public License v3
 * which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/lgpl-3.0.txt
 *
 * Various Contributors including, but not limited to:
 * SirSengir (original work), CovertJaguar, Player, Binnie, MysteriousAges
 ******************************************************************************/
package forestry.core.items;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

public class ItemBlockNBT extends ItemBlockForestry<Block> {

	public ItemBlockNBT(Block block) {
		super(block);
	}

	@Override
	public void addInformation(ItemStack itemstack, EntityPlayer player, List<String> info, boolean advanced) {
		super.addInformation(itemstack, player, info, advanced);

		if (itemstack.hasTagCompound()) {
			info.add("There are still some scribbles on this.");
		}
	}
}
