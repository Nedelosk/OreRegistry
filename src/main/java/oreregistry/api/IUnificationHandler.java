/*
 * Copyright (c) 2017 Nedelosk, Mezz
 *
 * This work (the MOD) is licensed under the "MIT" License, see LICENSE for details.
 */
package oreregistry.api;

import net.minecraft.item.ItemStack;

import oreregistry.api.registry.IProduct;
import oreregistry.api.registry.IResourceRegistry;

/**
 * This can be used to unify items.
 * <p>
 * You can register this handler with {@link IResourceRegistry#registerUnificationHandler(String, IUnificationHandler)}
 * and get all handlers of a resource with {@link IResourceRegistry#getUnificationHandlers(String)}.
 */
@FunctionalInterface
public interface IUnificationHandler {
	
	void onUnifyItem(ItemStack oldStack, ItemStack newStack, IProduct product);
}
