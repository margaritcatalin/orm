package com.example.myfirstejb;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.example.myfirstejb.model.Persoana;

/**
 * Session Bean implementation class FirstStatelesEJB
 */
@Stateless
@LocalBean
public class FirstStatelesEJB implements FirstStatelesEJBRemote {

	@PersistenceContext
	private EntityManager entityManager;

	/**
	 * Default constructor.
	 */
	public FirstStatelesEJB() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void insert(String name) {
		getEntityManager().persist(new Persoana(name));
	}

	public EntityManager getEntityManager() {
		return entityManager;
	}

	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
}
