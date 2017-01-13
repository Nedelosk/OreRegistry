package oreregistry.api;

import java.util.Map;

import net.minecraft.item.ItemStack;

/**
 * A resource has a type (see {@link ResourceTypes} and contains several product types (see {@link ProductTypes}.
 *
 */
public interface IResource {

	/**
	 * The type of the resource. For examples see {@link ResourceTypes}.
	 */
	String getType();

	/**
	 * 
	 * @param productType
	 *            The type of the product. For examples see {@link ProductTypes}.
	 * @param product
	 *            The product provided by your mod.
	 * @return the one chosen product that should be used by every mod.
	 */
	ItemStack registerProduct(String productType, ItemStack product);

	/**
	 * Gets the one chosen product of the specified type.
	 * <p>
	 * Throws an exception if the type has not been registered.
	 * Always register your product before trying to get the chosen product.
	 */
	ItemStack getProduct(String productType);

	/**
	 * Returns a read-only map containing product types and their associated
	 * products.
	 */
	Map<String, ItemStack> getRegisteredProducts();
	
}
