package com.nymblelabs.travelagency.service.destination;

import com.nymblelabs.travelagency.Entity.activity.Activity;
import com.nymblelabs.travelagency.Entity.destination.Destination;
import com.nymblelabs.travelagency.Entity.travelPackage.TravelPackage;
import com.nymblelabs.travelagency.dao.destination.DestinationDAOJpaImpl;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@Transactional
public class DestinationServiceImpl implements DestinationService{

  private DestinationDAOJpaImpl destinationDAOJpaImpl;
  @Autowired
  public DestinationServiceImpl(DestinationDAOJpaImpl destinationDAOJpaImpl) {
    this.destinationDAOJpaImpl = destinationDAOJpaImpl;
  }

  @Override
  public List<Destination> findAllDestination() {
    return this.destinationDAOJpaImpl.findAllDestination();
  }

  @Override
  public Destination findDestinationById(Integer destinationId) {
    return this.destinationDAOJpaImpl.findDestinationById(destinationId);
  }

  @Override
  public Destination saveDestination(Destination theDestination) {
    return this.destinationDAOJpaImpl.saveDestination(theDestination);
  }

  @Override
  public Integer deleteDestinationById(Integer destinationId) {
    return this.destinationDAOJpaImpl.deleteDestinationById(destinationId);
  }

  @Override
  public List<TravelPackage> getTravelPackageByDestination(String destinationId) {
    return this.destinationDAOJpaImpl.getTravelPackageByDestination(destinationId);
  }

  @Override
  public Destination addDestinationActivity(Destination destination, Activity theActivity) {
    return this.destinationDAOJpaImpl.addDestinationActivity(destination, theActivity);
  }
}
