package oreregistry;

import java.util.List;

import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class EventHandler {

	@SideOnly(Side.CLIENT)
	@SubscribeEvent
	public void onTooltip(ItemTooltipEvent event){
		if(event.isShowAdvancedItemTooltips()){
			List<String> tooltip = event.getToolTip();
			//TODO: translate
			//tooltip.add(TextFormatting.DARK_GRAY + "Resource: ");
			//tooltip.add(TextFormatting.DARK_GRAY + "Product Type: ");
		}
	}
	
}
