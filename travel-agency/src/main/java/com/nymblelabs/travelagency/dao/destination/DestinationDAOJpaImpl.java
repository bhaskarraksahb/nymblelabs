package com.nymblelabs.travelagency.dao.destination;

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
public class DestinationDAOJpaImpl extends BaseDAO implements DestinationDAO{
  @Autowired
  public DestinationDAOJpaImpl(EntityManager entityManager) {
    super(entityManager);
  }

  @Override
  public List<Destination> findAllDestination() {
    Query theQuery= (Query) getEntityManager().createQuery("from destination");
    return theQuery.getResultList();
  }

  @Override
  public Destination findDestinationById(Integer destinationId) {
    return getEntityManager().find(Destination.class, destinationId);
  }

  @Override
  public Destination saveDestination(Destination theDestination) {
    Destination destination = super.getEntityManager().merge(theDestination);
    theDestination.setId(destination.getId());
    return theDestination;
  }

  @Override
  public Integer deleteDestinationById(Integer destinationId) {
    Query theQuery = (Query) getEntityManager().createQuery("delete from destination where id=:destinationId");
    theQuery.setParameter("destinationId", destinationId);
    return theQuery.executeUpdate();
  }

  @Override
  public List<TravelPackage> getTravelPackageByDestination(String destinationId) {
    Query theQuery = (Query) getEntityManager().createQuery("from destination_travel_package_list where destination_id=:destinationId");
    theQuery.setParameter("destinationId", destinationId);
    return theQuery.getResultList();
  }

  @Override
  public Destination addDestinationActivity(Destination destination, Activity theActivity) {
    destination.getActivityList().add(theActivity);
    if(theActivity.getDestination() == null) {
      theActivity.setDestination(destination);
    }
    destination = getEntityManager().merge(destination);
    return destination;
  }

  @Override
  public List<Activity> getActivitiesByDestination(Integer destinationId) {
    Query theQuery = (Query) getEntityManager().createQuery("from Destination where Id=:destinationId");
    theQuery.setParameter("destinationId", destinationId);
    return theQuery.getResultList();
  }
}
