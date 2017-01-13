package oreregistry;

import java.util.List;

import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.translation.I18n;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import oreregistry.api.OreRegistryApi;
import oreregistry.api.info.IProductInfo;

public class EventHandler {

	@SideOnly(Side.CLIENT)
	@SubscribeEvent
	public void onTooltip(ItemTooltipEvent event) {
		if (event.isShowAdvancedItemTooltips()) {
			IProductInfo product = OreRegistryApi.info.getProductInfo(event.getItemStack());
			if (product != null) {
				List<String> tooltip = event.getToolTip();
				tooltip.add(TextFormatting.DARK_GRAY + I18n.translateToLocal("or.resource.name") + product.getResourceType());
				tooltip.add(TextFormatting.DARK_GRAY + I18n.translateToLocal("or.product.type.name") + product.getProductType());
			}
		}
	}

}
