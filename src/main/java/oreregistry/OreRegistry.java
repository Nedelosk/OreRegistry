package oreregistry;

import static oreregistry.api.ProductTypes.*;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import oreregistry.api.IResource;
import oreregistry.api.IResourceRegistry;
import oreregistry.api.OreRegistryApi;
import oreregistry.api.Resources;
import oreregistry.config.Constants;
import oreregistry.util.ResourceRegistry;

@Mod(modid = Constants.MOD_ID, name = Constants.NAME, version = Constants.VERSION, acceptedMinecraftVersions = "[1.11]")
public class OreRegistry {

	@Instance(Constants.MOD_ID)
	public static OreRegistry INSTANCE;

	public static ResourceRegistry registry;

	public OreRegistry() {
		OreRegistryApi.registry = registry = new ResourceRegistry();
	}

	@EventHandler
	public static void preInit(FMLPreInitializationEvent event) {
		registerVanilla(OreRegistryApi.registry);
		MinecraftForge.EVENT_BUS.register(new oreregistry.EventHandler());
	}

	private static void registerVanilla(IResourceRegistry resourceRegistry) {
		final IResource iron = resourceRegistry.createResource(Resources.IRON);
		resourceRegistry.registerResource(iron);
		iron.registerProduct(INGOT, new ItemStack(Items.IRON_INGOT));
		iron.registerProduct(NUGGET, new ItemStack(Items.field_191525_da));
		iron.registerProduct(BLOCK, new ItemStack(Blocks.IRON_BLOCK));
		iron.registerProduct(ORE, new ItemStack(Blocks.IRON_ORE));

		final IResource gold = resourceRegistry.createResource(Resources.GOLD);
		resourceRegistry.registerResource(gold);
		gold.registerProduct(INGOT, new ItemStack(Items.GOLD_INGOT));
		gold.registerProduct(NUGGET, new ItemStack(Items.GOLD_NUGGET));
		gold.registerProduct(BLOCK, new ItemStack(Blocks.GOLD_BLOCK));
		gold.registerProduct(ORE, new ItemStack(Blocks.GOLD_ORE));

		final IResource emerald = resourceRegistry.createResource(Resources.EMERALD);
		resourceRegistry.registerResource(emerald);
		emerald.registerProduct(GEM, new ItemStack(Items.EMERALD));
		emerald.registerProduct(BLOCK, new ItemStack(Blocks.EMERALD_BLOCK));
		emerald.registerProduct(ORE, new ItemStack(Blocks.EMERALD_ORE));

		final IResource diamond = resourceRegistry.createResource(Resources.DIAMOND);
		resourceRegistry.registerResource(diamond);
		diamond.registerProduct(GEM, new ItemStack(Items.DIAMOND));
		diamond.registerProduct(BLOCK, new ItemStack(Blocks.DIAMOND_BLOCK));
		diamond.registerProduct(ORE, new ItemStack(Blocks.DIAMOND_ORE));
	}
}
