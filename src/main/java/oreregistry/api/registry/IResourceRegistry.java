/*
 * Copyright (c) 2017 Nedelosk, Mezz
 *
 * This work (the MOD) is licensed under the "MIT" License, see LICENSE for details.
 */
package oreregistry.api.registry;

import java.util.Collection;
import java.util.Map;

import oreregistry.api.IUnificationHandler;
import oreregistry.api.OreRegistryApi;
import oreregistry.api.OreRegistryState;

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
	 * Register a {@link IUnificationHandler}.
	 */
	void registerUnificationHandler(String resourceType, IUnificationHandler unificationHandler);
	
	/**
	 * Returns a read-only map containing all {@link IUnificationHandler}s of the resource of this type.
	 */
	Collection<IUnificationHandler> getUnificationHandlers(String resourceType);
	
	/**
	 * The current state of the registry process.
	 *
	 * You only can register resources and products in the active state.
	 */
	OreRegistryState getState();

}
