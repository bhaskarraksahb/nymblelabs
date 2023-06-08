package com.nymblelabs.travelagency.dao.travelpackage;

import com.nymblelabs.travelagency.Entity.destination.Destination;
import com.nymblelabs.travelagency.Entity.passenger.Passenger;
import com.nymblelabs.travelagency.Entity.travelPackage.TravelPackage;
import com.nymblelabs.travelagency.dao.BaseDAO;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public class TravelPackageDAOJpaImpl extends BaseDAO implements TravelPackageDAO{

  @Autowired
  public TravelPackageDAOJpaImpl(EntityManager entityManager) {
    super(entityManager);
  }

  @Override
  public List<TravelPackage> findAllTravelPackage() {
    Query theQuery= (Query) getEntityManager().createQuery("from travel_package");
    return theQuery.getResultList();
  }

  @Override
  public TravelPackage findTravelPackageById(Integer travelPackageId) {
    return getEntityManager().find(TravelPackage.class, travelPackageId);
  }

  @Override
  public TravelPackage saveTravelPackage(TravelPackage theTravelPackage) {
    TravelPackage travelPackage = super.getEntityManager().merge(theTravelPackage);
    theTravelPackage.setId(travelPackage.getId());
    return theTravelPackage;
  }

  @Override
  public Integer deleteTravelPackageById(Integer travelPackageId) {
    Query theQuery = (Query) getEntityManager().createQuery("delete from travel_package where id=:travelPackageId");
    theQuery.setParameter("travelPackageId", travelPackageId);
    return theQuery.executeUpdate();
  }

  @Override
  public TravelPackage addDestination(TravelPackage travelPackage, Destination theDestination) {
    travelPackage.getTravelPackageDestinations().add(theDestination);
    theDestination.getTravelPackageList().add(travelPackage);
    travelPackage = getEntityManager().merge(travelPackage);
    return travelPackage;
  }

  @Override
  public TravelPackage addPassenger(TravelPackage travelPackage, Passenger thePassenger) {
    travelPackage.setTravelPackagePassengerCapacity(travelPackage.getTravelPackagePassengerCapacity() - 1);
    travelPackage.getTravelPackagePassenger().add(thePassenger);
    thePassenger.getTravelPackageList().add(travelPackage);
    travelPackage = getEntityManager().merge(travelPackage);
    return travelPackage;
  }

  @Override
  public List<Passenger> getPassengersByTravelPackage(Integer travelPackageId) {
    Query theQuery = (Query) getEntityManager().createQuery("from Passenger p join p.travelPackageList u where u.id=:travelPackageId");
    theQuery.setParameter("travelPackageId", travelPackageId);
    return theQuery.getResultList();
  }

  @Override
  public List<Destination> getDestinationByTravelPackage(Integer destinationId) {
    Query theQuery = (Query) getEntityManager().createQuery("from Destination d join d.travelPackageList t where t.id=:destinationId");
    theQuery.setParameter("destinationId", destinationId);
    return theQuery.getResultList();
  }
}
