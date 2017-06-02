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
