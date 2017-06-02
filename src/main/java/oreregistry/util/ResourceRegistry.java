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
