package com.master.core.resource;

public abstract class ResourceFacotry {

	private static ResourceFacotry factory;

	public ResourceFacotry() {
	}

	public static <P extends Resource> P get(){
		return getFactory().getResource();
	}
	
	public static ResourceFacotry getFactory() {
		if (factory == null)
			factory = new ResourceFacotryImpl();
		return factory;
	}

	public static void setFactory(ResourceFacotry factory) {
		ResourceFacotry.factory = factory;
	}
	
	public abstract <P extends Resource> P getResource();

	public abstract <P extends Resource> void setResource(P p);

	public abstract void endResource();
	
	

}
