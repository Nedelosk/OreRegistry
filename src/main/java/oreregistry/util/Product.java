package oreregistry.util;

import net.minecraft.item.ItemStack;
import oreregistry.api.IProduct;

public class Product implements IProduct {

	private final String resourceType;
	private final String productType;
	private final ItemStack product;
	
	public Product(String resourceType, String productType, ItemStack product) {
		this.resourceType = resourceType;
		this.productType = productType;
		this.product = product;
	}
	
	@Override
	public String getResourceType() {
		return resourceType;
	}
	
	@Override
	public String getProductType() {
		return productType;
	}
	
	@Override
	public ItemStack getProduct() {
		return product;
	}
	
}
