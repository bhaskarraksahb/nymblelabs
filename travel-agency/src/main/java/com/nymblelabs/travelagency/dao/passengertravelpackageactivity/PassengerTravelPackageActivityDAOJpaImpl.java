package com.nymblelabs.travelagency.dao.passengertravelpackageactivity;

import com.nymblelabs.travelagency.Entity.PassengerTravelPackageActivity;
import com.nymblelabs.travelagency.dao.BaseDAO;
import jakarta.persistence.EntityManager;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PassengerTravelPackageActivityDAOJpaImpl extends BaseDAO implements PassengerTravelPackageActivityDAO {
  @Autowired
  public PassengerTravelPackageActivityDAOJpaImpl(EntityManager entityManager) {
    super(entityManager);
  }

  @Override
  public PassengerTravelPackageActivity savePassengerTravelPackageActivity(PassengerTravelPackageActivity thePassengerTravelPackageActivity) {
    PassengerTravelPackageActivity passengerTravelPackageActivity = super.getEntityManager().merge(thePassengerTravelPackageActivity);
    thePassengerTravelPackageActivity.setId(passengerTravelPackageActivity.getId());
    return thePassengerTravelPackageActivity;
  }

  @Override
  public List<PassengerTravelPackageActivity> getPassengerTravelPackageActivity(Integer passengerId) {
    org.hibernate.query.Query theQuery = (Query) getEntityManager().createQuery("from PassengerTravelPackageActivity where passengerId=:passengerId");
    theQuery.setParameter("passengerId", passengerId);
    return theQuery.getResultList();

  }
}
