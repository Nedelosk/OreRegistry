package oreregistry.util;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import com.google.common.base.Preconditions;
import net.minecraft.item.ItemStack;
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
	public ItemStack registerProduct(String productType, ItemStack productVariant) {
		Preconditions.checkNotNull(productType, "Product Type must not be null");

		Product product = products.computeIfAbsent(productType, k -> new Product(this));
		product.addVariant(productVariant);
		return product.getChosenProduct().copy();
	}
	
	@Override
	public boolean hasProduct(String productType) {
		Preconditions.checkNotNull(productType, "Product Type must not be null");
		return products.get(productType) != null;
	}

	@Override
	public Map<String, IProduct> getRegisteredProducts() {
		return Collections.unmodifiableMap(products);
	}

}
