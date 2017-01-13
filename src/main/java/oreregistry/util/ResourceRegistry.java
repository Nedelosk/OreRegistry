package oreregistry.util;

import java.util.HashMap;
import java.util.Map;

import com.google.common.collect.ImmutableMap;
import net.minecraftforge.common.MinecraftForge;
import oreregistry.api.IResource;
import oreregistry.api.IResourceRegistry;
import oreregistry.api.ResourceEvent;

public final class ResourceRegistry implements IResourceRegistry {

	protected final Map<String, IResource> resources = new HashMap<>();

	@Override
	public IResource createResource(String resourceName) {
		return new Resource(resourceName);
	}

	@Override
	public boolean registerResource(IResource resource) {
		if (resources.containsKey(resource.getName())) {
			return false;
		}
		resources.put(resource.getName(), resource);

		MinecraftForge.EVENT_BUS.post(new ResourceEvent.ResourceRegisterEvent(resource));
		return true;
	}

	@Override
	public boolean isResourceRegistered(IResource resource) {
		return resource != null && resources.containsKey(resource.getName());
	}

	@Override
	public boolean isResourceRegistered(String resourceName) {
		return resources.containsKey(resourceName);
	}

	@Override
	public IResource getResource(String resourceName) {
		return resources.get(resourceName);
	}

	@Override
	public Map<String, IResource> getRegisteredResources() {
		return ImmutableMap.copyOf(resources);
	}

}
