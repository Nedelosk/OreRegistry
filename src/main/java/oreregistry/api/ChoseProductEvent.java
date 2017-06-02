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
    private final int index;

    public ChoseProductEvent(IProduct product, ItemStack chosenProduct, int index) {
        this.product = product;
        this.chosenProduct = chosenProduct;
        this.index = index;
    }

    public IProduct getProduct() {
        return product;
    }

    public ItemStack getChosenProduct() {
        return chosenProduct;
    }

    public int getIndex() {
        return index;
    }
}
