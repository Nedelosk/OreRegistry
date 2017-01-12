package oreregistry.util;

import java.util.Map;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import com.google.common.collect.ImmutableMap;

import static oreregistry.api.ProductTypes.INGOT;
import static oreregistry.api.ProductTypes.METAL_BLOCK;
import static oreregistry.api.ProductTypes.GEM;
import static oreregistry.api.ProductTypes.NUGGET;
import static oreregistry.api.ProductTypes.ORE;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import oreregistry.api.IResource;
import oreregistry.api.IResourceRegistry;
import oreregistry.api.ResourceEvent;
import oreregistry.api.Resources;

public final class ResourceRegistry implements IResourceRegistry {

	protected final BiMap<String, IResource> resources = HashBiMap.create();

	public void registerVanilla() {
		final IResource IRON = createResource(Resources.IRON);
		final IResource GOLD = createResource(Resources.GOLD);
		final IResource EMERALD = createResource(Resources.EMERALD);
		final IResource DIAMOND = createResource(Resources.DIAMOND);
		registerResource(IRON);
		IRON.registerProduct(INGOT, new ItemStack(Items.IRON_INGOT));
		IRON.registerProduct(NUGGET, new ItemStack(Items.field_191525_da));
		IRON.registerProduct(METAL_BLOCK, new ItemStack(Blocks.IRON_BLOCK));
		IRON.registerProduct(ORE, new ItemStack(Blocks.IRON_ORE));
		registerResource(GOLD);
		GOLD.registerProduct(INGOT, new ItemStack(Items.GOLD_INGOT));
		GOLD.registerProduct(NUGGET, new ItemStack(Items.GOLD_NUGGET));
		GOLD.registerProduct(METAL_BLOCK, new ItemStack(Blocks.GOLD_BLOCK));
		GOLD.registerProduct(ORE, new ItemStack(Blocks.GOLD_ORE));
		registerResource(EMERALD);
		EMERALD.registerProduct(GEM, new ItemStack(Items.EMERALD));
		EMERALD.registerProduct(METAL_BLOCK, new ItemStack(Blocks.EMERALD_BLOCK));
		EMERALD.registerProduct(ORE, new ItemStack(Blocks.EMERALD_ORE));
		registerResource(DIAMOND);
		DIAMOND.registerProduct(GEM, new ItemStack(Items.DIAMOND));
		DIAMOND.registerProduct(METAL_BLOCK, new ItemStack(Blocks.DIAMOND_BLOCK));
		DIAMOND.registerProduct(ORE, new ItemStack(Blocks.DIAMOND_ORE));
	}

	@Override
	public IResource createResource(String resourceName) {
		return new Resource(resourceName);
	}

	@Override
	public boolean registerResource(IResource resource) {
		if (resources.containsKey(resource.getName())) {
			return false;
		}
		resources.put(resource.getName(), resource);

		MinecraftForge.EVENT_BUS.post(new ResourceEvent.ResourceRegisterEvent(resource));
		return true;
	}

	@Override
	public boolean isResourceRegistered(IResource resource) {
		return resource != null && resources.containsKey(resource.getName());
	}

	@Override
	public boolean isResourceRegistered(String resourceName) {
		return resources.containsKey(resourceName);
	}

	@Override
	public IResource getResource(String resourceName) {
		return resources.get(resourceName);
	}

	@Override
	public String getResourceName(IResource resource) {
		return resources.inverse().get(resource);
	}

	@Override
	public Map<String, IResource> getRegisteredResources() {
		return ImmutableMap.copyOf(resources);
	}

}
