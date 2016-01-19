package com.master.core.resource;

class ResourceFacotryImpl extends ResourceFacotry {

	private static final ThreadLocal<Resource> RESOURCE = new ThreadLocal<>();

	public ResourceFacotryImpl() {
	}

	@Override
	@SuppressWarnings("unchecked")
	public <P extends Resource> P getResource() {
		return (P) RESOURCE.get();
	}

	@Override
	public <P extends Resource> void setResource(P p) {
		RESOURCE.set(p);
	}

	@Override
	public void endResource() {
		RESOURCE.remove();
	}

}
