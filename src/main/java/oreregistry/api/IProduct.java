package oreregistry.api;

import net.minecraft.item.ItemStack;

/**
 * This is a
 */
public interface IProduct {

	/**
	 *  The type of the resource. For examples see {@link ResourceTypes}.
	 */
	String getResourceType();
	
	/**
	 *  The type of the product. For examples see {@link ProductTypes}.
	 */
	String getProductType();
	
	/**
	 * Gets the one chosen product of the specified type.
	 */
	ItemStack getProduct();
	
}
