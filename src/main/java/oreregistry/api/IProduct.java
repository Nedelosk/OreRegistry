package oreregistry.api;

import net.minecraft.item.ItemStack;

/**
 * This is a
 */
public interface IProduct {

	/**
	 *  The name of the resource. For examples see {@link ResourceTypes}.
	 */
	String getResourceName();
	
	/**
	 *  The type of the product. For examples see {@link ProductTypes}.
	 */
	String getProductType();
	
	/**
	 * Gets the one chosen product of the specified type.
	 */
	ItemStack getProduct();
	
}
