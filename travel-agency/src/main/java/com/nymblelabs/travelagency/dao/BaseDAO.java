package com.nymblelabs.travelagency.dao;

import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;

public class BaseDAO {
  private EntityManager entityManager;

  @Autowired
  public BaseDAO(EntityManager entityManager) {
    this.entityManager = entityManager;
  }

  public EntityManager getEntityManager() {
    return entityManager;
  }
  @Autowired
  public void setEntityManager(EntityManager entityManager) {
    this.entityManager = entityManager;
  }
}
