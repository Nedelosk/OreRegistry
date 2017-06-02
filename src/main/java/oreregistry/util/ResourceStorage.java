/*
 * MIT License
 *
 * Copyright (c) 2017 Nedelosk, Mezz
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
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
