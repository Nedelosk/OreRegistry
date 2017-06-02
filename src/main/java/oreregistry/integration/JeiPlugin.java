/*
 * Copyright (c) 2017 Nedelosk, Mezz
 *
 * This work (the MOD) is licensed under the "MIT" License, see LICENSE for details.
 */
package oreregistry.integration;

import mezz.jei.api.BlankModPlugin;
import mezz.jei.api.IModRegistry;
import mezz.jei.api.JEIPlugin;
import mezz.jei.api.ingredients.IIngredientBlacklist;
import net.minecraft.item.ItemStack;
import oreregistry.OreRegistry;

@JEIPlugin
public class JeiPlugin extends BlankModPlugin {
	@Override
	public void register(IModRegistry registry) {
		IIngredientBlacklist ingredientBlacklist = registry.getJeiHelpers().getIngredientBlacklist();
		for (ItemStack itemStack : OreRegistry.unusedItems) {
			ingredientBlacklist.addIngredientToBlacklist(itemStack);
		}
	}
}
