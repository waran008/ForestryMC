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
package forestry.core.gui.widgets;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextFormatting;

import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import forestry.core.circuits.ISocketable;
import forestry.core.circuits.ISolderingIron;
import forestry.core.circuits.ItemCircuitBoard;
import forestry.core.gui.IContainerSocketed;
import forestry.core.gui.tooltips.ToolTip;
import forestry.core.proxy.Proxies;
import forestry.core.utils.Translator;

public class SocketWidget extends Widget {

	private final ISocketable tile;
	private final int slot;

	public SocketWidget(WidgetManager manager, int xPos, int yPos, ISocketable tile, int slot) {
		super(manager, xPos, yPos);
		this.tile = tile;
		this.slot = slot;
	}

	@Override
	public void draw(int startX, int startY) {
		ItemStack socketStack = tile.getSocket(slot);
		if (socketStack != null) {
			Proxies.common.getClientInstance().getRenderItem().renderItemIntoGUI(socketStack, startX + xPos, startY + yPos);
		}
	}

	@SideOnly(Side.CLIENT)
	@Override
	public ToolTip getToolTip(int mouseX, int mouseY) {
		return toolTip;
	}

	private final ToolTip toolTip = new ToolTip(250) {
		@Override
		@SideOnly(Side.CLIENT)
		public void refresh() {
			toolTip.clear();
			ItemStack stack = tile.getSocket(slot);
			if (stack != null) {
				Minecraft minecraft = Proxies.common.getClientInstance();
				EntityPlayer player = minecraft.thePlayer;
				toolTip.add(stack.getTooltip(player, minecraft.gameSettings.advancedItemTooltips));
				toolTip.add(TextFormatting.ITALIC + Translator.translateToLocal("for.gui.socket.remove"));
			} else {
				toolTip.add(Translator.translateToLocal("for.gui.emptysocket"));
			}
		}
	};

	@Override
	public void handleMouseClick(int mouseX, int mouseY, int mouseButton) {

		ItemStack itemstack = manager.minecraft.thePlayer.inventory.getItemStack();
		if (itemstack == null) {
			return;
		}

		Item held = itemstack.getItem();

		Container container = manager.gui.inventorySlots;
		if (!(container instanceof IContainerSocketed)) {
			return;
		}

		IContainerSocketed containerSocketed = (IContainerSocketed) container;

		// Insert chipsets
		if (held instanceof ItemCircuitBoard) {
			containerSocketed.handleChipsetClick(slot);
		} else if (held instanceof ISolderingIron) {
			containerSocketed.handleSolderingIronClick(slot);
		}
	}
}
