/*
 * MIT License
 *
 * Copyright (c) 2017 Nedelosk, Mezz
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package oreregistry.util;

import com.google.common.base.Preconditions;
import net.minecraft.item.ItemStack;
import oreregistry.api.registry.IProduct;
import oreregistry.api.registry.IResource;

import javax.annotation.Nullable;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

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
	public IProduct registerProduct(String productType, ItemStack productVariant) {
		Preconditions.checkNotNull(productType, "Product Type must not be null");
		Preconditions.checkNotNull(productVariant.isEmpty(), "Product Variant must not be empty");

		Product product = products.computeIfAbsent(productType, k -> new Product(this));
		product.addVariant(productVariant);
		return product;
	}
	
	@Override
	public boolean hasProduct(String productType) {
		return getProduct(productType) != null;
	}

	@Nullable
	@Override
	public IProduct getProduct(String productType) {
		Preconditions.checkNotNull(productType, "Product Type must not be null");
		return products.get(productType);
	}

	@Override
	public Map<String, IProduct> getRegisteredProducts() {
		return Collections.unmodifiableMap(products);
	}

}
