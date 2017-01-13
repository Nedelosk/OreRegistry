package oreregistry.util;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import com.google.common.base.Preconditions;
import net.minecraft.item.ItemStack;
import oreregistry.OreRegistry;
import oreregistry.api.IResource;

public class Resource implements IResource {
	private final String type;
	private final Map<String, ItemStack> products = new HashMap<>();

	public Resource(String type) {
		this.type = type;
	}

	@Override
	public String getType() {
		return type;
	}

	@Override
	public ItemStack registerProduct(String productType, ItemStack product) {
		Preconditions.checkNotNull(productType, "Product Type must not be null");
		Preconditions.checkNotNull(product, "Product must not be null");
		Preconditions.checkArgument(!product.isEmpty(), "Product must not be empty");

		OreRegistry.helper.registerResourceItem(product, this);

		if (!products.containsKey(productType)) {
			ItemStack copy = product.copy();
			copy.setCount(1);
			products.put(productType, copy);
		}
		return getProduct(productType);
	}

	@Override
	public ItemStack getProduct(String productType) {
		Preconditions.checkNotNull(productType, "Product Type must not be null");

		ItemStack product = products.get(productType);
		if (product == null) {
			throw new IllegalArgumentException("Product has not been registered: " + productType + ". Always register your product before trying to get the chosen product.");
		}
		return product.copy();
	}

	@Override
	public Map<String, ItemStack> getRegisteredProducts() {
		return Collections.unmodifiableMap(products);
	}

}
