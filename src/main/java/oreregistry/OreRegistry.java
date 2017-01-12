package oreregistry;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import oreregistry.api.OreRegistryApi;
import oreregistry.config.Constants;
import oreregistry.util.ResourceRegistry;

@Mod(modid = Constants.MOD_ID, name = Constants.NAME, version = Constants.VERSION, acceptedMinecraftVersions = "[1.11]")
public class OreRegistry {

	@Instance(Constants.MOD_ID)
	public static OreRegistry INSTANCE;

	public static ResourceRegistry registry;

	@EventHandler
	public static void preInit(FMLPreInitializationEvent event) {
		OreRegistryApi.registry = registry = new ResourceRegistry();
		registry.registerVanilla();
		MinecraftForge.EVENT_BUS.register(new oreregistry.EventHandler());
	}

}
