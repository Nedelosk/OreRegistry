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
	private final boolean isChosen;

	public ProductInfo(String resourceType, String productType, boolean isChosen) {
		this.resourceType = resourceType;
		this.productType = productType;
		this.isChosen = isChosen;
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
	public boolean isChosenVariant() {
		return isChosen;
	}
}
