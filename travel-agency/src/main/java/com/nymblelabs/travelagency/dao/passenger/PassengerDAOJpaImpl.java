package com.nymblelabs.travelagency.dao.passenger;

import com.nymblelabs.travelagency.Entity.activity.Activity;
import com.nymblelabs.travelagency.Entity.destination.Destination;
import com.nymblelabs.travelagency.Entity.passenger.Passenger;
import com.nymblelabs.travelagency.Entity.travelPackage.TravelPackage;
import com.nymblelabs.travelagency.dao.BaseDAO;
import jakarta.persistence.EntityManager;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public class PassengerDAOJpaImpl extends BaseDAO implements PassengerDAO{
  @Autowired
  public PassengerDAOJpaImpl(EntityManager entityManager) {
    super(entityManager);
  }

  @Override
  public List<Passenger> findAllPassengers() {
    Query theQuery= (Query) getEntityManager().createQuery("from passenger");
    return theQuery.getResultList();

  }

  @Override
  public Passenger findPassengerById(Integer passengerId) {
    return getEntityManager().find(Passenger.class, passengerId);
  }

  @Override
  public Passenger savePassenger(Passenger thePassenger) {
    Passenger passenger = super.getEntityManager().merge(thePassenger);
    thePassenger.setId(passenger.getId());
    return thePassenger;
  }

  @Override
  public Integer deletePassengerById(Integer passengerId) {
    Query theQuery = (Query) getEntityManager().createQuery("delete from passenger where id=:passengerId");
    theQuery.setParameter("passengerId", passengerId);
    return theQuery.executeUpdate();
  }

  @Override
  public List<TravelPackage> getTravelPackageByPassenger(Integer passengerId) {
    Query theQuery = (Query) getEntityManager().createQuery("from Passenger t join t.travelPackageList u where u.Id=:passengerId");
    theQuery.setParameter("passengerId", passengerId);
    return theQuery.getResultList();
  }

  @Override
  public Passenger signUpActivity(Passenger passenger, TravelPackage travelPackage, Destination destination, Activity activity) {
    passenger = getEntityManager().merge(passenger);
    activity = getEntityManager().merge(activity);
    return passenger;
  }
}
