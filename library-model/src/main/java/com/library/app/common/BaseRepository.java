package com.library.app.common;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

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

	public T update(T entity) {
		em.merge(entity);
		return entity;
	}

	public T findById(I id) {

		if (id == null) {
			return null;
		}

		return em.find(type, id);
	}

	@SuppressWarnings("unchecked")
	public List<T> findAll() {
		return em.createQuery("from " + type.getSimpleName()).getResultList();
	}

	public boolean alreadyExists(final String propertyName, final String propertyValue, final Long id) {
		final StringBuilder jpql = new StringBuilder();
		jpql.append("Select 1 From " + type.getSimpleName() + " e where e." + propertyName + " = :propertyValue");
		if (id != null) {
			jpql.append(" and e.id != :id");
		}

		final Query query = em.createQuery(jpql.toString());
		query.setParameter("propertyValue", propertyValue);
		if (id != null) {
			query.setParameter("id", id);
		}

		return query.setMaxResults(1).getResultList().size() > 0;
	}

	public boolean existsById(final Long id) {
		return em.createQuery("Select 1 From " + type.getSimpleName() + " e where e.id = :id").setParameter("id", id).setMaxResults(1).getResultList().size() > 0;
	}

	/**
	 * Warning: This method is only for tests
	 */
	public void deleteAll() {
		em.getTransaction().begin();
		em.createQuery("delete from " + type.getName()).executeUpdate();
		em.getTransaction().commit();
	}

}
