package oreregistry.api;

import javax.annotation.Nullable;

import net.minecraft.item.ItemStack;

/**
 *
 */
public interface IResourceHelper {

	/**
	 * 
	 * @param itemStack
	 * @return
	 */
	@Nullable
	IProduct getProduct(ItemStack itemStack);
}
