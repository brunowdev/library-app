package com.library.app.commontests.util;

import javax.persistence.EntityManager;

import org.junit.Ignore;

@Ignore
public class DBCommandTransactionalExecutor {

	private final EntityManager em;

	public DBCommandTransactionalExecutor(EntityManager em) {
		super();
		this.em = em;
	}

	public <T> T executeCommand(DBCommand<T> dbCommand) {

		try {
			em.getTransaction().begin();
			final T typeToReturn = dbCommand.execute();
			em.getTransaction().commit();
			em.clear();
			return typeToReturn;
		} catch (final Exception e) {
			e.printStackTrace();
			em.getTransaction().rollback();
			throw new IllegalStateException(e);
		}

	}

}
