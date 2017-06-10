/*
 * Copyright (c) 2017 Nedelosk, Mezz
 *
 * This work (the MOD) is licensed under the "MIT" License, see LICENSE for details.
 */
package oreregistry.util;

import com.google.common.base.Preconditions;
import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;

import javax.annotation.Nullable;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import oreregistry.api.IUnificationHandler;
import oreregistry.api.OreRegistryState;
import oreregistry.api.registry.IResource;
import oreregistry.api.registry.IResourceRegistry;

public final class ResourceRegistry implements IResourceRegistry {

	private final Map<String, IResource> resources = new HashMap<>();
	private final Multimap<String, IUnificationHandler> unificationHandlers = HashMultimap.create();
	private OreRegistryState state;

	public ResourceRegistry() {
		this.state = OreRegistryState.ACTIVE;
	}

	@Override
	public IResource registerResource(String resourceType) {
		Preconditions.checkNotNull(resourceType, "resourceType must not be null");

		if (hasResource(resourceType)) {
			return getResource(resourceType);
		}

		Resource resource = new Resource(resourceType);
		addResource(resource);
		return resource;
	}

	@Override
	public Map<String, IResource> getRegisteredResources() {
		return Collections.unmodifiableMap(resources);
	}
	
	@Override
	public void registerUnificationHandler(String resourceType, IUnificationHandler unificationHandler) {
		unificationHandlers.put(resourceType, unificationHandler);
	}
	
	@Override
	public Collection<IUnificationHandler> getUnificationHandlers(String resourceType) {
		return unificationHandlers.get(resourceType);
	}
	
	@Override
	public OreRegistryState getState() {
		return state;
	}

	/* INTERNAL */
	@Nullable
	public IResource getResource(String type){
		return resources.get(type);
	}

	public boolean hasResource(String type){
		return getResource(type) != null;
	}

	public void addResource(IResource resource){
		if(state != OreRegistryState.ACTIVE){
			return;
		}
		String type = resource.getType();
		if(!resources.containsKey(type)){
			resources.put(type, resource);
		}
	}

	public void setState(OreRegistryState state) {
		this.state = state;
	}

}
