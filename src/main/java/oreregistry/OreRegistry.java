package oreregistry;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import oreregistry.api.OreRegistryApi;
import oreregistry.api.registry.IResource;
import oreregistry.api.registry.IResourceRegistry;
import oreregistry.api.registry.ResourceTypes;
import oreregistry.config.Config;
import oreregistry.config.Constants;
import oreregistry.network.PacketHandler;
import oreregistry.util.ResourceInfo;
import oreregistry.util.ResourceRegistry;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static oreregistry.api.registry.ProductTypes.*;

@Mod(modid = Constants.MOD_ID, name = Constants.NAME, version = Constants.VERSION, acceptedMinecraftVersions = "[1.11]")
public class OreRegistry {

	@Mod.Instance(Constants.MOD_ID)
	public static OreRegistry instance;

	public static final ResourceRegistry registry;
	public static final ResourceInfo helper;
	public static final List<ItemStack> unusedItems = new ArrayList<>();
	public static File configFile;

	static {
		OreRegistryApi.registry = registry = new ResourceRegistry();
		OreRegistryApi.info = helper = new ResourceInfo();
	}

	@Mod.EventHandler
	public static void preInit(FMLPreInitializationEvent event) {
		registerVanilla(OreRegistryApi.registry);
		MinecraftForge.EVENT_BUS.register(new EventHandler());
		new PacketHandler();
		configFile = event.getSuggestedConfigurationFile();
	}

	@Mod.EventHandler
	public static void init(FMLInitializationEvent event) {
		Config.load(event.getSide());
	}

	@Mod.EventHandler
	public static void postInit(FMLPostInitializationEvent event) {
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

		final IResource redstone = resourceRegistry.registerResource(ResourceTypes.REDSTONE);
		redstone.registerProduct(DUST, new ItemStack(Items.REDSTONE));
		redstone.registerProduct(BLOCK, new ItemStack(Blocks.REDSTONE_BLOCK));
		redstone.registerProduct(ORE, new ItemStack(Blocks.REDSTONE_ORE));

		final IResource quartz = resourceRegistry.registerResource(ResourceTypes.QUARTZ);
		quartz.registerProduct(GEM, new ItemStack(Items.QUARTZ));
		quartz.registerProduct(BLOCK, new ItemStack(Blocks.QUARTZ_BLOCK));
		quartz.registerProduct(ORE, new ItemStack(Blocks.QUARTZ_ORE));

		final IResource coal = resourceRegistry.registerResource(ResourceTypes.COAL);
		coal.registerProduct(GEM, new ItemStack(Items.COAL));
		coal.registerProduct(BLOCK, new ItemStack(Blocks.COAL_BLOCK));
		coal.registerProduct(ORE, new ItemStack(Blocks.COAL_ORE));
	}

	public File getConfigFile(){
		return configFile;
	}
}
