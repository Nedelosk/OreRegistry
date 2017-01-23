package oreregistry.util;

import java.util.HashMap;
import java.util.Map;

import oreregistry.api.registry.IResource;

public class ResourceStorage {

	private final Map<String, IResource> resources = new HashMap<>();
	private State state;

	public ResourceStorage() {
		state = State.ACTIVE;
	}
	
	public void addResource(IResource resource){
		if(state != State.ACTIVE){
			return;
		}
		String type = resource.getType();
		if(!resources.containsKey(type)){
			resources.put(type, resource);
		}
	}
	
	public IResource replaceResource(IResource resource){
		if(state != State.SYNCHRONIZE){
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
	
	public void setState(State state) {
		this.state = state;
	}
	
	public State getState() {
		return state;
	}
	
	public enum State{
		ACTIVE, SYNCHRONIZE, FINISH;
	}
	
}
