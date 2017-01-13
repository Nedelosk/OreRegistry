package oreregistry.api;

import java.util.Map;

import javax.annotation.Nullable;

public interface IResourceRegistry {

	IResource createResource(String resourceName);

	/**
	 * Register a new Resource.
	 *
	 * @param resource
	 *            The resource to register.
	 * @return True if the resource was registered
	 */
	boolean registerResource(IResource resource);

	@Nullable
	IResource getResource(String name);

	/**
	 * Does the supplied resource have an entry for it's name
	 * 
	 * @param resource
	 *            the resource we're testing
	 * @return if the resource's name has a registration entry
	 */
	boolean isResourceRegistered(IResource resource);

	boolean isResourceRegistered(String name);

	/**
	 * Returns a read-only map containing resource names and their associated
	 * resources.
	 */
	Map<String, IResource> getRegisteredResources();

}
