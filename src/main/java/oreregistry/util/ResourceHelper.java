package oreregistry.util;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.IdentityHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.google.common.base.Preconditions;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import oreregistry.api.IProduct;
import oreregistry.api.IResource;
import oreregistry.api.IResourceHelper;

public class ResourceHelper implements IResourceHelper {
	private final Map<Item, List<IResource>> resourceItems = new IdentityHashMap<>();

	@Nullable
	@Override
	public IProduct getProduct(ItemStack itemStack) {
		Preconditions.checkNotNull(itemStack, "itemStack must not be null");
		Preconditions.checkArgument(!itemStack.isEmpty(), "itemStack must not be empty");

		Item item = itemStack.getItem();
		List<IResource> resources = resourceItems.get(item);
		if (resources != null) {
			for (IResource resource : resources) {
				Map<String, ItemStack> registeredProducts = resource.getRegisteredProducts();
				for (Entry<String, ItemStack> entry : registeredProducts.entrySet()) {
					ItemStack product = entry.getValue();
					if (ItemStack.areItemsEqual(product, itemStack) && ItemStack.areItemStackTagsEqual(itemStack, product)) {
						String productType = entry.getKey();
						return new Product(resource.getType(), productType, product);
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
