package oreregistry.util;

import oreregistry.api.info.IProductInfo;

public class ProductInfo implements IProductInfo {

	private final String resourceType;
	private final String productType;

	public ProductInfo(String resourceType, String productType) {
		this.resourceType = resourceType;
		this.productType = productType;
	}

	@Override
	public String getResourceType() {
		return resourceType;
	}

	@Override
	public String getProductType() {
		return productType;
	}

}
