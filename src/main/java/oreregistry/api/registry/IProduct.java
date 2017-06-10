/*
 * Copyright (c) 2017 Nedelosk, Mezz
 *
 * This work (the MOD) is licensed under the "MIT" License, see LICENSE for details.
 */
package oreregistry.api.registry;

import java.util.List;

import net.minecraft.item.ItemStack;

/**
 * A product has a type (see {@link ProductTypes}.
 * This is used to unify resources from many mods so that they all use the same items.
 * <p>
 * All mods have to register there variant of this product in the pre init phase of fml with {@link IResource#registerProduct(String, ItemStack)}.
 * Later in or after the init phase of fml they can get the by OR chosen variant with {@link #getChosenProduct()}.
 * <p>
 * Get an instance from {@link IResource#getProduct(String)} or {@link IResource#registerProduct(String, ItemStack)}.
 */
public interface IProduct {
	
	/**
	 * The type of the product. For examples see {@link ProductTypes}.
	 */
	String getType();
	
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
