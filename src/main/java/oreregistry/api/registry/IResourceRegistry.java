package oreregistry.api.registry;

import java.util.Map;

import oreregistry.api.OreRegistryApi;

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

}
