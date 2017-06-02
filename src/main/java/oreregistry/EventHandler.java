/*
 * MIT License
 *
 * Copyright (c) 2017 Nedelosk, Mezz
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
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
