package oreregistry.api;

import java.util.Map;

import javax.annotation.Nullable;

public interface IResourceRegistry {

	IResource registerResource(String resourceName);

	/**
	 * Returns a read-only map containing resource names and their associated
	 * resources.
	 */
	Map<String, IResource> getRegisteredResources();

}
