/*
 * Copyright (c) 2017 Nedelosk, Mezz
 *
 * This work (the MOD) is licensed under the "MIT" License, see LICENSE for details.
 */
package oreregistry.util;

import com.google.common.base.Preconditions;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import oreregistry.OreRegistry;
import oreregistry.api.ChoseProductEvent;
import oreregistry.api.OreRegistryState;
import oreregistry.api.registry.IProduct;
import oreregistry.api.registry.IResource;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Product implements IProduct{

	private final List<ItemStack> variants = new ArrayList<>();
	private final IResource resource;
	private ItemStack chosenProduct;
	private int chosenProductIndex;
	
	public Product(IResource resource) {
		this.resource = resource;
		this.chosenProduct = ItemStack.EMPTY;
	}
	
	void choseProduct(ItemStack chosenProduct, int chosenProductIndex){
		OreRegistryState state = OreRegistry.registry.getRegistryState();
		if(state != OreRegistryState.CHOOSE && state != OreRegistryState.SYNCHRONIZE){
			return;
		}
		if(chosenProduct.isEmpty()){
			this.chosenProduct = ItemStack.EMPTY;
			return;
		}
		this.chosenProductIndex = chosenProductIndex;
		this.chosenProduct = chosenProduct;
		MinecraftForge.EVENT_BUS.post(new ChoseProductEvent(this, chosenProduct, chosenProductIndex));
		OreRegistry.helper.registerResourceItem(chosenProduct, resource);
	}

	public int getChosenProductIndex() {
		return chosenProductIndex;
	}

	@Override
	public List<ItemStack> getVariants() {
		return Collections.unmodifiableList(variants);
	}
	
	@Override
	public ItemStack getChosenProduct() {
		if(chosenProduct.isEmpty()){
			return ItemStack.EMPTY;
		}
		return chosenProduct.copy();
	}

	@Override
	public IResource getResource() {
		return resource;
	}
	
	void addVariant(ItemStack variant){
		Preconditions.checkNotNull(variant, "Product must not be null");
		Preconditions.checkArgument(!variant.isEmpty(), "Product must not be empty");
		variant = variant.copy();
		variant.setCount(1);
		this.variants.add(variant);
	}
	
}
