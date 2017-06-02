/*
 * Copyright (c) 2017 Nedelosk, Mezz
 *
 * This work (the MOD) is licensed under the "MIT" License, see LICENSE for details.
 */
package oreregistry;

import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.translation.I18n;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import oreregistry.api.OreRegistryApi;
import oreregistry.api.info.IProductInfo;
import oreregistry.network.PacketHandler;

import java.util.List;

public class EventHandler {

	@SideOnly(Side.CLIENT)
	@SubscribeEvent
	public void handleTooltip(ItemTooltipEvent event) {
		if (event.isShowAdvancedItemTooltips()) {
			IProductInfo product = OreRegistryApi.info.getProductInfo(event.getItemStack());
			if (product != null) {
				List<String> tooltip = event.getToolTip();
				tooltip.add(TextFormatting.DARK_GRAY + I18n.translateToLocal("or.resource.name") + product.getResourceType());
				tooltip.add(TextFormatting.DARK_GRAY + I18n.translateToLocal("or.product.type.name") + product.getProductType());
			}
		}
	}
	
	@SubscribeEvent
	public void handlePlayerLoggedIn(PlayerEvent.PlayerLoggedInEvent event) {
		World world = event.player.world;
		if(!world.isRemote){
			PacketHandler.sendToClients(event.player);
		}
	}

}
