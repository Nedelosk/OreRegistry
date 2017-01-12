package oreregistry;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import oreregistry.config.Constants;

@Mod(modid=Constants.MOD_ID, name=Constants.NAME, version=Constants.VERSION, acceptedMinecraftVersions = "[1.11]")
public class OreRegistry {

	@EventHandler
	public static void preInit(FMLPreInitializationEvent event){
		
	}
	
	@EventHandler
	public static void init(FMLInitializationEvent event){
		
	}
	
	@EventHandler
	public static void postInit(FMLPostInitializationEvent event){
		
	}
	
}
