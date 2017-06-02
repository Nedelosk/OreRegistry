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
package oreregistry.util;

import com.google.common.base.Preconditions;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import oreregistry.OreRegistry;
import oreregistry.api.ChoseProductEvent;
import oreregistry.api.registry.IProduct;
import oreregistry.api.registry.IResource;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Product implements IProduct{

	private final List<ItemStack> variants = new ArrayList<>();
	private final IResource resource;
	private ItemStack chosenProduct;
	
	public Product(IResource resource) {
		this.resource = resource;
		this.chosenProduct = ItemStack.EMPTY;
	}
	
	public void choseProduct(ItemStack chosenProduct){
		if(chosenProduct.isEmpty()){
			this.chosenProduct = ItemStack.EMPTY;
			return;
		}
		this.chosenProduct = chosenProduct;
		MinecraftForge.EVENT_BUS.post(new ChoseProductEvent(this, chosenProduct));
		OreRegistry.helper.registerResourceItem(chosenProduct, resource);
	}
	
	@Override
	public List<ItemStack> getVariants() {
		return Collections.unmodifiableList(variants);
	}
	
	@Override
	public ItemStack getChosenProduct() {
		if(chosenProduct.isEmpty()){
			return ItemStack.EMPTY;
		}
		return chosenProduct.copy();
	}

	@Override
	public IResource getResource() {
		return resource;
	}
	
	public void addVariant(ItemStack variant){
		Preconditions.checkNotNull(variant, "Product must not be null");
		Preconditions.checkArgument(!variant.isEmpty(), "Product must not be empty");
		variant = variant.copy();
		variant.setCount(1);
		this.variants.add(variant);
	}
	
}
