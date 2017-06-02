/*
 * Copyright (c) 2017 Nedelosk, Mezz
 *
 * This work (the MOD) is licensed under the "MIT" License, see LICENSE for details.
 */
package oreregistry.api.info;

import net.minecraft.item.ItemStack;
import oreregistry.api.registry.ProductTypes;
import oreregistry.api.registry.ResourceTypes;

/**
 * Information about a specific registered product.
 * Get this info for an ItemStack from {@link IResourceInfo#getProductInfo(ItemStack)}.
 */
public interface IProductInfo {

	/**
	 * The type of the resource. For examples see {@link ResourceTypes}.
	 */
	String getResourceType();

	/**
	 * The type of the product. For examples see {@link ProductTypes}.
	 */
	String getProductType();

}
