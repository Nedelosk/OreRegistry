package oreregistry.api;

import net.minecraftforge.fml.common.eventhandler.Event;

public abstract class ResourceEvent extends Event {

	private final IResource resource;

	protected ResourceEvent(IResource resource) {
		this.resource = resource;
	}

	public IResource getResource() {
		return resource;
	}

	/**
	 * Called when a new resource is registered.
	 */
	public static final class ResourceRegisterEvent extends ResourceEvent {

		public ResourceRegisterEvent(IResource resource) {
			super(resource);
		}

	}

}
