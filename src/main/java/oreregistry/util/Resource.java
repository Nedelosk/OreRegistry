package oreregistry.util;

import java.util.HashMap;
import java.util.Map;

import jline.internal.Preconditions;
import net.minecraft.item.ItemStack;
import oreregistry.api.IResource;

public class Resource implements IResource {

	private final String name;
	protected final Map<String, ItemStack> products = new HashMap<>();

	public Resource(String name) {
		this.name = name;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public void registerProduct(String productType, ItemStack product) {
		Preconditions.checkNotNull(productType);
		Preconditions.checkNotNull(product);
		if (!products.containsKey(productType)) {
			products.put(productType, product);
		}
	}

	@Override
	public ItemStack getProduct(String productType) {
		ItemStack product = products.get(productType);
		if (product == null) {
			throw new IllegalArgumentException("Product has not been registered: " + productType + ". Always register your product before trying to get the chosen product.");
		}
		return product;
	}

}
