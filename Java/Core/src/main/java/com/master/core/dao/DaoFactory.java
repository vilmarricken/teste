package com.master.core.dao;

import com.master.core.exception.MasterException;

public class DaoFactory {

	@SuppressWarnings("unchecked")
	public static final <P extends Dao> P getDao(Class<P> dao) throws MasterException {
		String daoClass = dao.getName() + "Default";
		Class<P> daoDefault;
		try {
			daoDefault = (Class<P>) Class.forName(daoClass);
			P p = daoDefault.newInstance();
			return p;
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
			throw new MasterException("Error initializing " + dao);
		}
	}

}
