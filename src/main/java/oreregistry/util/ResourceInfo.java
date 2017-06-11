/*
 * Copyright (c) 2017 Nedelosk, Mezz
 *
 * This work (the MOD) is licensed under the "MIT" License, see LICENSE for details.
 */
package oreregistry.util;

import com.google.common.base.Preconditions;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.IdentityHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import oreregistry.OreRegistry;
import oreregistry.api.IUnificationHandler;
import oreregistry.api.OreRegistryApi;
import oreregistry.api.info.IProductInfo;
import oreregistry.api.info.IResourceInfo;
import oreregistry.api.registry.IProduct;
import oreregistry.api.registry.IResource;

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
					IProduct product = entry.getValue();
					for(ItemStack variant : product.getVariants()) {
						if (ProductUtils.needUnification(itemStack, variant)) {
							String productType = entry.getKey();
							ItemStack chosenProduct = product.getChosenProduct();
							return new ProductInfo(resource.getType(), productType, ProductUtils.needUnification(itemStack, chosenProduct));
						}
					}
				}
			}
		}
		return null;
	}
	
	@Override
	public ItemStack tryUnifyItem(ItemStack itemToUnify) {
		IProductInfo productInfo = getProductInfo(itemToUnify);
		if(productInfo != null){
			IResource resource = OreRegistry.registry.getResource(productInfo.getResourceType());
			if(resource == null){
				return ItemStack.EMPTY;
			}
			IProduct product = resource.getProduct(productInfo.getProductType());
			if(product == null){
				return ItemStack.EMPTY;
			}
			ItemStack unifiedItem = product.getChosenProduct();
			if(ProductUtils.needUnification(itemToUnify, unifiedItem)){
				return ItemStack.EMPTY;
			}
			unifiedItem.setCount(itemToUnify.getCount());
			for(IUnificationHandler handler : OreRegistryApi.registry.getUnificationHandlers(resource.getType())){
				handler.onUnifyItem(itemToUnify, unifiedItem, product);
			}
			return unifiedItem;
		}
		return ItemStack.EMPTY;
	}
	
	public void registerResourceItem(ItemStack itemStack, IResource resource) {
		Item item = itemStack.getItem();
		List<IResource> resources = resourceItems.computeIfAbsent(item, k -> new ArrayList<>());
		resources.add(resource);
	}
}
