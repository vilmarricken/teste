package com.master.persistence.entity;

import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataBuilder;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.model.naming.ImplicitNamingStrategyJpaCompliantImpl;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class PersistenceManagerHibernate extends PersistenceManager {

	private SessionFactory sessionFactory;

	public PersistenceManagerHibernate() {
		this.setUp();
	}

	private void setUp() {
		final StandardServiceRegistryBuilder srb = new StandardServiceRegistryBuilder();
		srb.configure("org/hibernate/example/MyCfg.xml");
		final StandardServiceRegistry sr = srb.build();

		final MetadataSources ms = new MetadataSources(sr);
		/*
		 * ms.addAnnotatedClass("MyEntity.class".getClass());
		 * ms.addAnnotatedClassName("org.hibernate.example.Customer");
		 * ms.addResource("org/hibernate/example/Order.hbm.xml");
		 * ms.addResource("org/hibernate/example/Product.orm.xml");
		 */
		final MetadataBuilder mb = ms.getMetadataBuilder();
		final Metadata metadata = mb.applyImplicitNamingStrategy(ImplicitNamingStrategyJpaCompliantImpl.INSTANCE).build();
		this.sessionFactory = metadata.getSessionFactoryBuilder().build();
	}

	@Override
	public Persistence getPersistence() {
		return new Persistence(this.sessionFactory.openSession());
	}

}
