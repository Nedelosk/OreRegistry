/*
 * Copyright (c) 2017 Nedelosk, Mezz
 *
 * This work (the MOD) is licensed under the "MIT" License, see LICENSE for details.
 */
package oreregistry.util;

import net.minecraft.item.ItemStack;
import oreregistry.OreRegistry;

import java.util.List;

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
}
