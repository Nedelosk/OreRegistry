package oreregistry.util;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import oreregistry.api.IResource;
import oreregistry.api.IResourceRegistry;

public final class ResourceRegistry implements IResourceRegistry {

	protected final Map<String, IResource> resources = new HashMap<>();

	@Override
	public IResource registerResource(String resourceName) {
		if (resources.containsKey(resourceName)) {
			return resources.get(resourceName);
		}

		Resource resource = new Resource(resourceName);
		resources.put(resource.getType(), resource);
		return resource;
	}

	@Override
	public Map<String, IResource> getRegisteredResources() {
		return Collections.unmodifiableMap(resources);
	}

}
