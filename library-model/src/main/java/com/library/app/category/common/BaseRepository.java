package com.library.app.category.common;

import javax.persistence.EntityManager;

public abstract class BaseRepository<T, I> extends InnerClassHandler<T> {

	protected EntityManager em;

	/**
	 * TODO Remove this method after implements CDI
	 * 
	 * @param em
	 */
	public void injectEntityManager(EntityManager em) {
		this.em = em;
	}

	public T add(T entity) {
		em.persist(entity);
		return entity;
	}

	public T findById(I id) {
		return em.find(type, id);
	}

}
