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
package oreregistry.network;

import com.google.common.base.Preconditions;
import io.netty.buffer.Unpooled;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.IThreadListener;
import net.minecraftforge.common.util.FakePlayer;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.network.FMLEventChannel;
import net.minecraftforge.fml.common.network.FMLNetworkEvent.ClientCustomPacketEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.internal.FMLProxyPacket;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import oreregistry.OreRegistry;
import oreregistry.api.OreRegistryState;
import oreregistry.api.registry.IProduct;
import oreregistry.api.registry.IResource;
import oreregistry.util.Log;
import oreregistry.util.Resource;
import oreregistry.util.ResourceStorage;

import java.util.Map;
import java.util.Map.Entry;

public class PacketHandler {

	public static final String channelId = "or";
	private static FMLEventChannel channel;
	
	public PacketHandler() {
		channel = NetworkRegistry.INSTANCE.newEventDrivenChannel(channelId);
		channel.register(this);
	}
	
	public static void sendToClients(EntityPlayer player){
		if (!(player instanceof EntityPlayerMP) || player instanceof FakePlayer) {
			return;
		}
		PacketBuffer buffer = new PacketBuffer(Unpooled.buffer());
		Map<String, IResource> resources = OreRegistry.registry.getRegisteredResources();
		buffer.writeVarInt(resources.size());
		for(Entry<String, IResource> resourceEntry : resources.entrySet()){
			IResource resource = resourceEntry.getValue();
			buffer.writeString(resource.getType());
			Map<String, IProduct> products = resource.getRegisteredProducts();
			buffer.writeVarInt(products.size());
			for(Entry<String, IProduct> product : products.entrySet()){
				buffer.writeString(product.getKey());
				buffer.writeItemStack(product.getValue().getChosenProduct());
			}
		}
		channel.sendTo(new FMLProxyPacket(buffer, channelId), (EntityPlayerMP)player);
	}
	
	@SideOnly(Side.CLIENT)
	@SubscribeEvent
	public void onPacket(ClientCustomPacketEvent event) {
		PacketBuffer buffer = new PacketBuffer(event.getPacket().payload());
		
		checkThreadAndEnqueue(buffer, Minecraft.getMinecraft());
	}
	
	@SideOnly(Side.CLIENT)
	private static void checkThreadAndEnqueue(final PacketBuffer buffer, IThreadListener threadListener) {
		if (!threadListener.isCallingFromMinecraftThread()) {
			threadListener.addScheduledTask(() -> {
				EntityPlayer player = Minecraft.getMinecraft().player;
				Preconditions.checkNotNull(player, "Tried to send data to client before the player exists.");
				ResourceStorage storage = OreRegistry.registry.getResourceStorage();
				
				storage.setState(OreRegistryState.SYNCHRONIZE);
				int size = buffer.readVarInt();
				while(size > 0){
					size--;
					readResource(buffer, storage);
				}
				
				storage.setState(OreRegistryState.INACTIVE);
			});
		}
	}
	
	private static void readResource(PacketBuffer buffer, ResourceStorage storage){
		String resourceType = buffer.readString(1024);
		IResource resource = new Resource(resourceType);
		storage.replaceResource(resource);
		int products = buffer.readVarInt();
		for(int i = 0;i < products;i++){
			String productType = buffer.readString(1024);
			try{
				ItemStack product;
				product = buffer.readItemStack();
				resource.registerProduct(productType, product);
			}catch(Exception e){
				Log.error("Failed to read a product of  the type " + productType + ".", e);
			}
		}
	}
}
