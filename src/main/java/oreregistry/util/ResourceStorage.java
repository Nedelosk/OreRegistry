/*
 * Copyright (c) 2017 Nedelosk, Mezz
 *
 * This work (the MOD) is licensed under the "MIT" License, see LICENSE for details.
 */
package oreregistry.util;

import oreregistry.api.OreRegistryState;
import oreregistry.api.registry.IResource;

import java.util.HashMap;
import java.util.Map;

public class ResourceStorage {

	private final Map<String, IResource> resources = new HashMap<>();
	private OreRegistryState state;

	public ResourceStorage() {
		state = OreRegistryState.ACTIVE;
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
	
	public IResource replaceResource(IResource resource){
		if(state != OreRegistryState.SYNCHRONIZE){
			return null;
		}
		String type = resource.getType();
		return resources.put(type, resource);
	}
	
	public IResource getResource(String type){
		return resources.get(type);
	}
	
	public boolean hasResource(String type){
		return getResource(type) != null;
	}
	
	public Map<String, IResource> getResources() {
		return resources;
	}
	
	public void setState(OreRegistryState state) {
		this.state = state;
	}
	
	public OreRegistryState getState() {
		return state;
	}
	
}
