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
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import oreregistry.api.info.IProductInfo;
import oreregistry.api.info.IResourceInfo;
import oreregistry.api.registry.IProduct;
import oreregistry.api.registry.IResource;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.IdentityHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class ResourceInfo implements IResourceInfo {
	private final Map<Item, List<IResource>> resourceItems = new IdentityHashMap<>();

	@Nullable
	@Override
	public IProductInfo getProductInfo(ItemStack itemStack) {
		Preconditions.checkNotNull(itemStack, "itemStack must not be null");
		Preconditions.checkArgument(!itemStack.isEmpty(), "itemStack must not be empty");

		Item item = itemStack.getItem();
		List<IResource> resources = resourceItems.get(item);
		if (resources != null) {
			for (IResource resource : resources) {
				Map<String, IProduct> registeredProducts = resource.getRegisteredProducts();
				for (Entry<String, IProduct> entry : registeredProducts.entrySet()) {
					ItemStack product = entry.getValue().getChosenProduct();
					if (ItemStack.areItemsEqual(product, itemStack) && ItemStack.areItemStackTagsEqual(itemStack, product)) {
						String productType = entry.getKey();
						return new ProductInfo(resource.getType(), productType);
					}
				}
			}
		}
		return null;
	}

	public void registerResourceItem(ItemStack itemStack, IResource resource) {
		Item item = itemStack.getItem();
		List<IResource> resources = resourceItems.computeIfAbsent(item, k -> new ArrayList<>());
		resources.add(resource);
	}
}
