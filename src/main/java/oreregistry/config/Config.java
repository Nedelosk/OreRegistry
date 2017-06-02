/*
 * MIT License
 *
 * Copyright (c) 2017 Nedelosk, Mezz
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package oreregistry.config;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.relauncher.Side;
import oreregistry.OreRegistry;
import oreregistry.api.OreRegistryApi;
import oreregistry.api.OreRegistryState;
import oreregistry.api.registry.IProduct;
import oreregistry.api.registry.IResource;
import oreregistry.util.Product;
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
        storage.setState(OreRegistryState.CHOSE);
        config = new Configuration(configFile, "1.0.0");
        String resourceCategory = "resource";
        String categoryComment = "This config category is used to chose the product variants that should be used by this mod.";
        categoryComment+="\n\n";
        categoryComment+="Every number presents one variant of a product. The value of the property is the number of the variant that is used be this mod.";
        config.setCategoryComment(resourceCategory, categoryComment);
        for(Map.Entry<String, IResource> resourceEntry : OreRegistryApi.registry.getRegisteredResources().entrySet()){
            IResource resource = resourceEntry.getValue();
            for(Map.Entry<String, IProduct> productEntry : resource.getRegisteredProducts().entrySet()){
                Product product = (Product) productEntry.getValue();
                String comment = "<";
                List<ItemStack> variants = product.getVariants();
                for(int i = 0;i < variants.size();i++){
                    ItemStack itemStack = variants.get(i);
                    Item item = itemStack.getItem();
                    comment+=item.getRegistryName().toString()+ ':' + itemStack.getItemDamage();
                    if(itemStack.hasTagCompound()){
                        NBTTagCompound tagCompound = itemStack.getTagCompound();
                        comment+=':' + tagCompound.toString();
                    }
                    comment+=">\n";
                }
                int variantIndex = config.getInt(productEntry.getKey(), resourceCategory + '.' + resourceEntry.getKey(), 0, 0, variants.size() - 1, comment);

                for(int i = 0;i < variants.size();i++){
                    ItemStack variant = variants.get(i);
                    if(i == variantIndex){
                        product.choseProduct(variant);
                    }else {
                        OreRegistry.unusedItems.add(variant.copy());
                    }
                }
            }
        }

        if(config.hasChanged()) {
            config.save();
        }
        storage.setState(OreRegistryState.INACTIVE);
    }
}
