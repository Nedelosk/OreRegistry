/*
 * Copyright (c) 2017 Nedelosk, Mezz
 *
 * This work (the MOD) is licensed under the "MIT" License, see LICENSE for details.
 */
package oreregistry.api.registry;

import oreregistry.api.OreRegistryApi;
import oreregistry.api.OreRegistryState;

import java.util.Map;

/**
 * Register resources and get the chosen resource for each type.
 * <p>
 * Get the instance from {@link OreRegistryApi#registry}.
 */
public interface IResourceRegistry {

	/**
	 * Register a type of resource added by your mod.
	 */
	IResource registerResource(String resourceType);

	/**
	 * Returns a read-only map containing resource names and their associated
	 * resources.
	 */
	Map<String, IResource> getRegisteredResources();

	/**
	 * The current state of the registry process.
	 *
	 * You only can register resources and products in the active state.
	 */
	OreRegistryState getRegistryState();

}
