/*
 * Copyright (c) 2017 Nedelosk, Mezz
 *
 * This work (the MOD) is licensed under the "MIT" License, see LICENSE for details.
 */
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
	
	/**
	 * Tries to unify a item.
	 * <p>
	 * Returns an empty ItemStack if the item is already unified or if the item is not a variant of a registered product.
	 */
	ItemStack tryUnifyItem(ItemStack itemStack);
}
