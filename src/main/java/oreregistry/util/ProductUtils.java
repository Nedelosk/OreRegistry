/*
 * Copyright (c) 2017 Nedelosk, Mezz
 *
 * This work (the MOD) is licensed under the "MIT" License, see LICENSE for details.
 */
package oreregistry.util;

import java.util.List;

import net.minecraft.item.ItemStack;

import oreregistry.OreRegistry;
import oreregistry.api.IUnificationHandler;
import oreregistry.api.OreRegistryApi;
import oreregistry.api.info.IProductInfo;
import oreregistry.api.registry.IProduct;
import oreregistry.api.registry.IResource;

public class ProductUtils {

    public static void chooseProduct(Product product, int variantIndex){
        List<ItemStack> variants = product.getVariants();
        if(variantIndex >= variants.size()){
            variantIndex = variants.size();
        }
        for(int i = 0;i < variants.size();i++){
            ItemStack variant = variants.get(i);
            if(i == variantIndex){
                product.choseProduct(variant, variantIndex);
            }else {
                OreRegistry.unusedItems.add(variant.copy());
            }
        }
    }
    
    public static ItemStack tryUnifyItem(ItemStack oldStack){
        IProductInfo productInfo = OreRegistryApi.info.getProductInfo(oldStack);
        if(productInfo != null){
            IResource resource = OreRegistry.registry.getResource(productInfo.getResourceType());
            if(resource == null){
                return ItemStack.EMPTY;
            }
            IProduct product = resource.getProduct(productInfo.getProductType());
            if(product == null){
                return ItemStack.EMPTY;
            }
            ItemStack newStack = product.getChosenProduct();
            if(needUnification(oldStack, newStack)){
                return ItemStack.EMPTY;
            }
            newStack.setCount(oldStack.getCount());
            for(IUnificationHandler handler : OreRegistryApi.registry.getUnificationHandlers(resource.getType())){
                handler.onUnifyItem(oldStack, newStack, product);
            }
            return newStack;
        }
        return ItemStack.EMPTY;
    }
    
    public static boolean needUnification(ItemStack oldStack, ItemStack newStack){
        return ItemStack.areItemsEqual(oldStack, newStack) && ItemStack.areItemStackTagsEqual(oldStack, newStack);
    }
}
