/*
 * Copyright (c) 2017 Nedelosk, Mezz
 *
 * This work (the MOD) is licensed under the "MIT" License, see LICENSE for details.
 */
package oreregistry.api.registry;

import javax.annotation.Nullable;
import java.util.Map;

import net.minecraft.item.ItemStack;

/**
 * A resource has a type (see {@link ResourceTypes} and contains several product types (see {@link ProductTypes}.
 * This is used to unify resources from many mods so that they all use the same items.
 * <p>
 * Get an instance from {@link IResourceRegistry#registerResource(String)}.
 */
public interface IResource {

	/**
	 * The type of the resource. For examples see {@link ResourceTypes}.
	 */
	String getType();

	/**
	 * Register a product added by your mod for this resource.
	 *
	 * @param productType The type of the product. For examples see {@link ProductTypes}.
	 * @param product     The product provided by your mod.
	 * @return the product that should be used by every mod.
	 */
	IProduct registerProduct(String productType, ItemStack product);

	/**
	 * 
	 * @param productType The type of the product. For examples see {@link ProductTypes}.
	 * @return True if it is already a product registered with this type.
	 */
	boolean hasProduct(String productType);

	/**
	 * @param productType The type of the product. For examples see {@link ProductTypes}.
	 * @return The product of the productType, if one is registered.
	 */
	@Nullable
	IProduct getProduct(String productType);
	
	/**
	 * Returns a read-only map containing product types and their associated products.
	 */
	Map<String, IProduct> getRegisteredProducts();

}
