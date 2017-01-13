package oreregistry.api;

import java.util.Map;

public interface IResourceRegistry {

	IResource registerResource(String resourceType);

	/**
	 * Returns a read-only map containing resource names and their associated
	 * resources.
	 */
	Map<String, IResource> getRegisteredResources();

}
