/*
 * Copyright (c) 2017 Nedelosk, Mezz
 *
 * This work (the MOD) is licensed under the "MIT" License, see LICENSE for details.
 */
package oreregistry.util;

import com.google.common.base.Preconditions;

import javax.annotation.Nullable;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import net.minecraft.item.ItemStack;

import oreregistry.OreRegistry;
import oreregistry.api.OreRegistryState;
import oreregistry.api.registry.IProduct;
import oreregistry.api.registry.IResource;

public class Resource implements IResource {
	private final String type;
	private final Map<String, Product> products = new HashMap<>();

	public Resource(String type) {
		this.type = type;
	}

	@Override
	public String getType() {
		return type;
	}

	@Override
	public IProduct registerProduct(String productType, ItemStack productVariant) {
		Preconditions.checkNotNull(productType, "Product Type must not be null");
		Preconditions.checkNotNull(productVariant.isEmpty(), "Product Variant must not be empty");

		OreRegistryState state = OreRegistry.registry.getState();
		if(state != OreRegistryState.ACTIVE){
			throw new UnsupportedOperationException("Products must not be registered in other states than the ACTIVE state, current state is: " + state);
		}

		Product product = products.computeIfAbsent(productType, k -> new Product(this, k));
		product.addVariant(productVariant);
		return product;
	}
	
	@Override
	public boolean hasProduct(String productType) {
		return getProduct(productType) != null;
	}

	@Nullable
	@Override
	public IProduct getProduct(String productType) {
		Preconditions.checkNotNull(productType, "Product Type must not be null");
		return products.get(productType);
	}

	@Override
	public Map<String, IProduct> getRegisteredProducts() {
		return Collections.unmodifiableMap(products);
	}

}
