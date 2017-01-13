package oreregistry.util;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import net.minecraftforge.common.MinecraftForge;
import oreregistry.api.IResource;
import oreregistry.api.IResourceRegistry;
import oreregistry.api.ResourceEvent;

public final class ResourceRegistry implements IResourceRegistry {

	protected final Map<String, IResource> resources = new HashMap<>();

	@Override
	public IResource registerResource(String resourceName) {
		if (resources.containsKey(resourceName)) {
			return resources.get(resourceName);
		}

		Resource resource = new Resource(resourceName);
		MinecraftForge.EVENT_BUS.post(new ResourceEvent.ResourceRegisterEvent(resource));
		resources.put(resource.getType(), resource);
		return resource;
	}

	@Override
	public Map<String, IResource> getRegisteredResources() {
		return Collections.unmodifiableMap(resources);
	}

}
