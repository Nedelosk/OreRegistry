package oreregistry;

import java.util.ArrayList;
import java.util.List;

import static oreregistry.api.ProductTypes.*;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import oreregistry.api.IResource;
import oreregistry.api.IResourceRegistry;
import oreregistry.api.OreRegistryApi;
import oreregistry.api.ResourceTypes;
import oreregistry.config.Constants;
import oreregistry.util.ResourceHelper;
import oreregistry.util.ResourceRegistry;

@Mod(modid = Constants.MOD_ID, name = Constants.NAME, version = Constants.VERSION, acceptedMinecraftVersions = "[1.11]")
public class OreRegistry {
	public static final ResourceHelper helper;
	public static final List<ItemStack> unusedItems = new ArrayList<>();

	static {
		OreRegistryApi.registry = new ResourceRegistry();
		OreRegistryApi.helper = helper = new ResourceHelper();
	}

	@EventHandler
	public static void preInit(FMLPreInitializationEvent event) {
		registerVanilla(OreRegistryApi.registry);
		MinecraftForge.EVENT_BUS.register(new oreregistry.EventHandler());
	}

	private static void registerVanilla(IResourceRegistry resourceRegistry) {
		final IResource iron = resourceRegistry.registerResource(ResourceTypes.IRON);
		iron.registerProduct(INGOT, new ItemStack(Items.IRON_INGOT));
		iron.registerProduct(NUGGET, new ItemStack(Items.field_191525_da));
		iron.registerProduct(BLOCK, new ItemStack(Blocks.IRON_BLOCK));
		iron.registerProduct(ORE, new ItemStack(Blocks.IRON_ORE));

		final IResource gold = resourceRegistry.registerResource(ResourceTypes.GOLD);
		gold.registerProduct(INGOT, new ItemStack(Items.GOLD_INGOT));
		gold.registerProduct(NUGGET, new ItemStack(Items.GOLD_NUGGET));
		gold.registerProduct(BLOCK, new ItemStack(Blocks.GOLD_BLOCK));
		gold.registerProduct(ORE, new ItemStack(Blocks.GOLD_ORE));

		final IResource emerald = resourceRegistry.registerResource(ResourceTypes.EMERALD);
		emerald.registerProduct(GEM, new ItemStack(Items.EMERALD));
		emerald.registerProduct(BLOCK, new ItemStack(Blocks.EMERALD_BLOCK));
		emerald.registerProduct(ORE, new ItemStack(Blocks.EMERALD_ORE));

		final IResource diamond = resourceRegistry.registerResource(ResourceTypes.DIAMOND);
		diamond.registerProduct(GEM, new ItemStack(Items.DIAMOND));
		diamond.registerProduct(BLOCK, new ItemStack(Blocks.DIAMOND_BLOCK));
		diamond.registerProduct(ORE, new ItemStack(Blocks.DIAMOND_ORE));

		final IResource lapis = resourceRegistry.registerResource(ResourceTypes.LAPIS);
		lapis.registerProduct(GEM, new ItemStack(Items.DYE, 1, EnumDyeColor.BLUE.getDyeDamage()));
		lapis.registerProduct(BLOCK, new ItemStack(Blocks.LAPIS_BLOCK));
		lapis.registerProduct(ORE, new ItemStack(Blocks.LAPIS_ORE));
	}
}
