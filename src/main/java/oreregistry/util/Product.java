package oreregistry.util;

import net.minecraft.item.ItemStack;
import oreregistry.api.IProduct;

public class Product implements IProduct {

	private final String resourceName;
	private final String productType;
	private final ItemStack product;
	
	public Product(String resourceName, String productType, ItemStack product) {
		this.resourceName = resourceName;
		this.productType = productType;
		this.product = product;
	}
	
	@Override
	public String getResourceName() {
		return resourceName;
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
