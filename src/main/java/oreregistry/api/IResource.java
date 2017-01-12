package oreregistry.api;

import javax.annotation.Nullable;

import net.minecraft.item.ItemStack;

/**
 *
 */
public interface IResource {

	/**
	 * The name of the resource.
	 */
	String getName();

	/**
	 * 
	 * @param productType
	 *            The type of the product. For exaples see {@link ProductTypes}.
	 * @param product
	 * @return True if the product was registered.
	 */
	boolean registerProduct(String productType, ItemStack product);

	@Nullable
	ItemStack getProduct(String productType);

}
