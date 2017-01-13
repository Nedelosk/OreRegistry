package oreregistry.api.info;

import javax.annotation.Nullable;

import net.minecraft.item.ItemStack;
import oreregistry.api.OreRegistryApi;

/**
 * Get information about registered products.
 * Get the instance from {@link OreRegistryApi#info}.
 */
public interface IResourceInfo {
	/**
	 * Get information about a registered product.
	 * Returns null if the product has not been registered.
	 */
	@Nullable
	IProductInfo getProductInfo(ItemStack product);
}
