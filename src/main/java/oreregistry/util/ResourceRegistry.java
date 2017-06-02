package oreregistry.util;

import com.google.common.base.Preconditions;
import oreregistry.api.OreRegistryState;
import oreregistry.api.registry.IResource;
import oreregistry.api.registry.IResourceRegistry;

import java.util.Collections;
import java.util.Map;

public final class ResourceRegistry implements IResourceRegistry {

	protected final ResourceStorage resourceStorage = new ResourceStorage();
	
	@Override
	public IResource registerResource(String resourceType) {
		Preconditions.checkNotNull(resourceType, "resourceType must not be null");

		if (resourceStorage.hasResource(resourceType)) {
			return resourceStorage.getResource(resourceType);
		}

		Resource resource = new Resource(resourceType);
		resourceStorage.addResource(resource);
		return resource;
	}

	@Override
	public Map<String, IResource> getRegisteredResources() {
		return Collections.unmodifiableMap(getResources());
	}
	
	public ResourceStorage getResourceStorage() {
		return resourceStorage;
	}

	@Override
	public OreRegistryState getRegistryState() {
		return resourceStorage.getState();
	}

	/* INTERNAL */
	
	public Map<String, IResource> getResources() {
		return resourceStorage.getResources();
	}

}
