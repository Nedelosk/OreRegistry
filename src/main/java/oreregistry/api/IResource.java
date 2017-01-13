package oreregistry.api;

import net.minecraft.item.ItemStack;

/**
 *
 */
public interface IResource {

	/**
	 * The name of the resource. For examples see {@link ResourceTypes}.
	 */
	String getName();

	/**
	 * 
	 * @param productType
	 *            The type of the product. For examples see {@link ProductTypes}.
	 * @param product
	 *            The product provided by your mod.
	 */
	void registerProduct(String productType, ItemStack product);

	/**
	 * Gets the one chosen product of the specified type.
	 * <p>
	 * Throws an exception if the type has not been registered.
	 * Always register your product before trying to get the chosen product.
	 */
	ItemStack getProduct(String productType);

}
