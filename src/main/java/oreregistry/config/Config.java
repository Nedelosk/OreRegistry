/*
 * Copyright (c) 2017 Nedelosk, Mezz
 *
 * This work (the MOD) is licensed under the "MIT" License, see LICENSE for details.
 */
package oreregistry.config;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.relauncher.Side;
import oreregistry.OreRegistry;
import oreregistry.api.OreRegistryApi;
import oreregistry.api.OreRegistryState;
import oreregistry.api.registry.IProduct;
import oreregistry.api.registry.IResource;
import oreregistry.util.Product;
import oreregistry.util.ProductUtils;
import oreregistry.util.ResourceStorage;

import javax.annotation.Nullable;
import java.io.File;
import java.util.List;
import java.util.Map;

public class Config {

    @Nullable
    public static Configuration config;

    public static void load(Side side) {
        File configFile = OreRegistry.instance.getConfigFile();
        loadConfig(side, configFile);
    }

    private static void loadConfig(Side side, File configFile) {
        ResourceStorage storage = OreRegistry.registry.getResourceStorage();
        storage.setState(OreRegistryState.CHOOSE);
        config = new Configuration(configFile, "1.0.0");
        String resourceCategory = "resource";
        String categoryComment = "This config category is used to chose the product variants that should be used by this mod.";
        categoryComment+="\n\n";
        categoryComment+="Every number presents one variant of a product. \n";
        categoryComment+="The value of the property is the number of the variant that is used be this mod.";
        config.setCategoryComment(resourceCategory, categoryComment);
        StringBuilder comment = new StringBuilder();
        for(Map.Entry<String, IResource> resourceEntry : OreRegistryApi.registry.getRegisteredResources().entrySet()){
            IResource resource = resourceEntry.getValue();
            for(Map.Entry<String, IProduct> productEntry : resource.getRegisteredProducts().entrySet()){
                Product product = (Product) productEntry.getValue();
                List<ItemStack> variants = product.getVariants();
                for(int i = 0;i < variants.size();i++){
                    ItemStack itemStack = variants.get(i);
                    Item item = itemStack.getItem();
                    ResourceLocation location = item.getRegistryName();
                    if(location == null){
                        continue;
                    }
                    comment.append(i + " = <" + location.toString() + ':' + itemStack.getItemDamage());
                    NBTTagCompound tagCompound = itemStack.getTagCompound();
                    if(tagCompound != null){
                        comment.append(':' + tagCompound.toString());
                    }
                    comment.append(">\n");
                }
                int variantIndex = config.getInt(productEntry.getKey(), resourceCategory + '.' + resourceEntry.getKey(), 0, 0, variants.size() - 1, comment.toString());
                comment.setLength(0);
                ProductUtils.chooseProduct(product, variantIndex);
            }
        }

        if(config.hasChanged()) {
            config.save();
        }
        storage.setState(OreRegistryState.INACTIVE);
    }
}
