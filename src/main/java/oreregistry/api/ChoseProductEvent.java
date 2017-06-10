/*
 * Copyright (c) 2017 Nedelosk, Mezz
 *
 * This work (the MOD) is licensed under the "MIT" License, see LICENSE for details.
 */
package oreregistry.api;

import net.minecraft.item.ItemStack;

import net.minecraftforge.fml.common.eventhandler.Event;

import oreregistry.api.registry.IProduct;

/**
 * This is fired when OR has chosen a variant as his chosen variant of a product.
 */
public class ChoseProductEvent extends Event{
    
    /**
     * The product from that a variant was chosen.
     */
    private final IProduct product;
    /**
     * The chosen variant, one of the variants of the product. To get the variants of the product use {@link IProduct#getVariants()}.
     */
    private final ItemStack chosenProduct;
    /**
     * The config index of the chosen variant. It was used to chose the variant.
     */
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
