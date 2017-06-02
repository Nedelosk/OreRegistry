/*
 * Copyright (c) 2017 Nedelosk, Mezz
 *
 * This work (the MOD) is licensed under the "MIT" License, see LICENSE for details.
 */
package oreregistry.api;

import oreregistry.api.info.IResourceInfo;
import oreregistry.api.registry.IResourceRegistry;

public class OreRegistryApi {

	/**
	 * Register resources and get the chosen resource for each type.
	 */
	public static IResourceRegistry registry;

	/**
	 * Get information about registered products.
	 */
	public static IResourceInfo info;

}
