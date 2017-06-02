/*
 * Copyright (c) 2017 Nedelosk, Mezz
 *
 * This work (the MOD) is licensed under the "MIT" License, see LICENSE for details.
 */
package oreregistry.api;

import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.eventhandler.Event;
import oreregistry.api.registry.IProduct;

public class ChoseProductEvent extends Event{

    private final IProduct product;
    private final ItemStack chosenProduct;

    public ChoseProductEvent(IProduct product, ItemStack chosenProduct) {
        this.product = product;
        this.chosenProduct = chosenProduct;
    }

    public IProduct getProduct() {
        return product;
    }

    public ItemStack getChosenProduct() {
        return chosenProduct;
    }
}
