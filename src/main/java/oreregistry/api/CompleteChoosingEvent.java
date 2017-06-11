/*
 * Copyright (c) 2017 Nedelosk, Mezz
 *
 * This work (the MOD) is licensed under the "MIT" License, see LICENSE for details.
 */
package oreregistry.api;

import net.minecraft.item.ItemStack;

import net.minecraftforge.fml.common.eventhandler.Event;

import oreregistry.api.registry.IProduct;
import oreregistry.api.registry.IResource;
import oreregistry.api.registry.IResourceRegistry;

/**
 * This is fired when all products have chosen there chosen variant.
 * <p>
 * The best way to get the chosen variant of a product is to call {@link IResourceRegistry#registerResource(String)} in the pre init phase of fml.
 * The result of this method is a {@link IResource}. Than you register your product variant with {@link IResource#registerProduct(String, ItemStack)}.
 * The result of this method is a {@link IProduct}, later with this event you can get the chosen variant from the {@link IProduct} with {@link IProduct#getChosenProduct()}.
 */
public class CompleteChoosingEvent extends Event {
	
	public CompleteChoosingEvent() {
	}
}
