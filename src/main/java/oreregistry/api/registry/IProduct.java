package oreregistry.api.registry;

import net.minecraft.item.ItemStack;

import java.util.List;

/**
 * Get an instance from {@link IResource#getProduct(String)} or {@link IResource#registerProduct(String, ItemStack)}.
 */
public interface IProduct {

	/**
	 * @return A list with all registered variants of this product.
	 */
	List<ItemStack> getVariants();

	/**
	 * @return A copy of the chosen product.
	 */
	ItemStack getChosenProduct();

	/**
	 * @return The resource of this product.
	 */
	IResource getResource();
	
}
