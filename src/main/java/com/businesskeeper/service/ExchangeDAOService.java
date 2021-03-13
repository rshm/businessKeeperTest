package com.businesskeeper.service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.businesskeeper.model.entity.ExchangeRate;

//Manager Entity. Saves and retrieves users from the database
@Repository
@Transactional
public class ExchangeDAOService {

	@PersistenceContext
	private EntityManager entityManager;

	public long insert(ExchangeRate ex){
		entityManager.persist(ex);
		return  ex.getId();
	}
}
