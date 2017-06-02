/*
 * Copyright (c) 2017 Nedelosk, Mezz
 *
 * This work (the MOD) is licensed under the "MIT" License, see LICENSE for details.
 */
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
