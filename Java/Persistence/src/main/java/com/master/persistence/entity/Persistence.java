package com.master.persistence.entity;

import java.io.Serializable;

import org.hibernate.Session;

public class Persistence {

	private final Session session;

	Persistence(Session session) {
		this.session = session;
	}

	public <T> T save(T t) {
		this.session.save(t);
		return t;
	}

	public <T> T delete(T t) {
		this.session.delete(t);
		return t;
	}

	public <T> T find(Class<T> t, Serializable id) {
		return this.session.get(t, id);
	}

}
