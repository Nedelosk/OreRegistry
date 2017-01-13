package oreregistry.util;

import javax.annotation.Nullable;
import java.util.Map;
import java.util.Map.Entry;

import com.google.common.base.Preconditions;
import net.minecraft.item.ItemStack;
import oreregistry.api.IProduct;
import oreregistry.api.IResource;
import oreregistry.api.IResourceHelper;
import oreregistry.api.OreRegistryApi;

public class ResourceHelper implements IResourceHelper {

	@Nullable
	@Override
	public IProduct getProduct(ItemStack itemStack) {
		Preconditions.checkNotNull(itemStack, "itemStack must not be null");
		Preconditions.checkArgument(!itemStack.isEmpty(), "itemStack must not be empty");

		Map<String, IResource> registeredResources = OreRegistryApi.registry.getRegisteredResources();
		for(IResource resource : registeredResources.values()){
			Map<String, ItemStack> registeredProducts = resource.getRegisteredProducts();
			for(Entry<String, ItemStack> entry : registeredProducts.entrySet()){
				ItemStack product = entry.getValue();
				if(ItemStack.areItemsEqual(product, itemStack) && ItemStack.areItemStackTagsEqual(itemStack, product)){
					String productType = entry.getKey();
					return new Product(resource.getType(), productType, product);
				}
			}
		}
		return null;
	}
	
}
