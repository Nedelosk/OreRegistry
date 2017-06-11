/*
 * Copyright (c) 2017 Nedelosk, Mezz
 *
 * This work (the MOD) is licensed under the "MIT" License, see LICENSE for details.
 */
package oreregistry;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.translation.I18n;
import net.minecraft.world.World;

import net.minecraftforge.event.entity.item.ItemTossEvent;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.event.entity.player.PlayerDropsEvent;
import net.minecraftforge.event.world.BlockEvent;

import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import oreregistry.api.OreRegistryApi;
import oreregistry.api.info.IProductInfo;
import oreregistry.config.Config;
import oreregistry.network.PacketHandler;

public class EventHandler {

	@SideOnly(Side.CLIENT)
	@SubscribeEvent
	public void handleTooltip(ItemTooltipEvent event) {
		if (event.isShowAdvancedItemTooltips()) {
			IProductInfo product = OreRegistryApi.info.getProductInfo(event.getItemStack());
			if (product != null) {
				List<String> tooltip = event.getToolTip();
				boolean isChosen = product.isChosenVariant();
				tooltip.add((isChosen ? TextFormatting.GOLD : TextFormatting.DARK_GRAY).toString() + I18n.translateToLocal("or.resource.name") + product.getResourceType());
				tooltip.add((isChosen ? TextFormatting.GOLD : TextFormatting.DARK_GRAY).toString() + I18n.translateToLocal("or.product.type.name") + product.getProductType());
			}
		}
	}
	
	@SubscribeEvent
	public void handleDrops(BlockEvent.HarvestDropsEvent event) {
		if(!Config.unifyItems){
			return;
		}
		List<ItemStack> drops = event.getDrops();
		List<ItemStack> newDrops = new ArrayList<>();
		Iterator<ItemStack> dropsIterator = drops.iterator();
		while(dropsIterator.hasNext()){
			ItemStack itemStack = OreRegistryApi.info.tryUnifyItem(dropsIterator.next());
			if(!itemStack.isEmpty()){
				dropsIterator.remove();
				newDrops.add(itemStack);
			}
		}
		drops.addAll(newDrops);
	}
	
	@SubscribeEvent
	public void handlePlayerDrops(PlayerDropsEvent event) {
		if(!Config.unifyItems){
			return;
		}
		for(EntityItem entityItem : event.getDrops()){
			ItemStack itemStack = OreRegistryApi.info.tryUnifyItem(entityItem.getEntityItem());
			if(!itemStack.isEmpty()){
				entityItem.setEntityItemStack(itemStack);
			}
		}
	}
	
	@SubscribeEvent
	public void handleToss(ItemTossEvent event) {
		if(!Config.unifyItems){
			return;
		}
		EntityItem entityItem = event.getEntityItem();
		ItemStack itemStack = OreRegistryApi.info.tryUnifyItem(entityItem.getEntityItem());
		if(!itemStack.isEmpty()){
			entityItem.setEntityItemStack(itemStack);
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
