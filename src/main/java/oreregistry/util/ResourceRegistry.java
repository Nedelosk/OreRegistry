package oreregistry.util;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import com.google.common.base.Preconditions;
import oreregistry.api.IResource;
import oreregistry.api.IResourceRegistry;

public final class ResourceRegistry implements IResourceRegistry {

	protected final Map<String, IResource> resources = new HashMap<>();

	@Override
	public IResource registerResource(String resourceType) {
		Preconditions.checkNotNull(resourceType, "resourceType must not be null");

		if (resources.containsKey(resourceType)) {
			return resources.get(resourceType);
		}

		Resource resource = new Resource(resourceType);
		resources.put(resource.getType(), resource);
		return resource;
	}

	@Override
	public Map<String, IResource> getRegisteredResources() {
		return Collections.unmodifiableMap(resources);
	}

}
