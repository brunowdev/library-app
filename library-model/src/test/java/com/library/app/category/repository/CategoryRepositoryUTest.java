package com.library.app.category.repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.After;
import org.junit.Test;

public class CategoryRepositoryUTest {

	private EntityManagerFactory emf;
	private EntityManager em;

	@Test
	public void initTestCase() {
		emf = Persistence.createEntityManagerFactory("libraryPU");
		em = emf.createEntityManager();
	}

	@After
	public void closeEntityManager() {
		em.close();
		emf.close();
	}

	@Test
	public void addCategoryAndFindIt() {

		try {
			em.getTransaction().begin();
			em.getTransaction().commit();

			em.clear();
		} catch (final Exception e) {
			e.printStackTrace();
			em.getTransaction().rollback();
		}

	}

}
