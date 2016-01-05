package com.master.persistence.entity;

public abstract class PersistenceManager {

	private static PersistenceManager INSTANCE;

	public static final PersistenceManager getInstance() {
		if (PersistenceManager.INSTANCE == null) {
			PersistenceManager.INSTANCE = new PersistenceManagerHibernate();
		}
		return PersistenceManager.INSTANCE;
	}

	public static final void getInstance(PersistenceManager pm) {
		PersistenceManager.INSTANCE = pm;
	}

	public abstract Persistence getPersistence();

}
