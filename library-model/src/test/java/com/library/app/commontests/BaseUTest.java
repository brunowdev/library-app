package com.library.app.commontests;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Ignore;

import com.library.app.commontests.util.DBCommandTransactionalExecutor;

@Ignore
public class BaseUTest {

	protected static EntityManagerFactory emf;
	protected static EntityManager em;
	protected static DBCommandTransactionalExecutor dbCommandTransactionalExecutor;

	@BeforeClass
	public static void initTestCase() {
		emf = Persistence.createEntityManagerFactory("libraryPU");
		em = emf.createEntityManager();
		dbCommandTransactionalExecutor = new DBCommandTransactionalExecutor(em);
	}

	@AfterClass
	public static void closeEntityManager() {
		em.close();
		emf.close();

	}

}
