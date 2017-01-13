package oreregistry.util;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import com.google.common.base.Preconditions;
import net.minecraft.item.ItemStack;
import oreregistry.OreRegistry;
import oreregistry.api.registry.IResource;

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

		ItemStack chosenProduct = products.get(productType);
		if (chosenProduct == null) {
			ItemStack copy = product.copy();
			copy.setCount(1);
			products.put(productType, copy);
			chosenProduct = product;

			OreRegistry.helper.registerResourceItem(product, this);
		} else {
			OreRegistry.unusedItems.add(product);
		}

		return chosenProduct;
	}
	
	@Override
	public boolean hasProduct(String productType) {
		Preconditions.checkNotNull(productType, "Product Type must not be null");
		return products.get(productType) != null;
	}

	@Override
	public Map<String, ItemStack> getRegisteredProducts() {
		return Collections.unmodifiableMap(products);
	}

}
