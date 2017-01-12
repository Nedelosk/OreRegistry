package oreregistry.util;

import javax.annotation.Nullable;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;

import jline.internal.Preconditions;
import net.minecraft.item.ItemStack;
import oreregistry.api.IResource;

public class Resource implements IResource {

	private final String name;
	protected final BiMap<String, ItemStack> products = HashBiMap.create();

	public Resource(String name) {
		this.name = name;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public boolean registerProduct(String productType, ItemStack product) {
		Preconditions.checkNotNull(productType);
		Preconditions.checkNotNull(product);
		if (products.containsKey(productType)) {
			throw new IllegalArgumentException("Product already exists with type: " + productType);
		}
		products.put(productType, product);
		return true;
	}

	@Nullable
	@Override
	public ItemStack getProduct(String productType) {
		return products.get(productType);
	}

}
