package oreregistry.api.registry;

import java.util.List;

import net.minecraft.item.ItemStack;

public interface IProduct {
	
	List<ItemStack> getVariants();
	
	ItemStack getChosenProduct();
	
	IResource getResource();
	
}
