package com.nymblelabs.travelagency.service.destination;

import com.nymblelabs.travelagency.Entity.activity.Activity;
import com.nymblelabs.travelagency.Entity.destination.Destination;
import com.nymblelabs.travelagency.Entity.passenger.Passenger;
import com.nymblelabs.travelagency.Entity.travelPackage.TravelPackage;

import java.util.List;

public interface DestinationService {

  List<Destination> findAllDestination();
  Destination findDestinationById(Integer destinationId);

  Destination saveDestination(Destination theDestination);

  Integer deleteDestinationById(Integer destinationId);

  List <TravelPackage> getTravelPackageByDestination(String destinationId);
  Destination addDestinationActivity(Destination destination, Activity theActivity);
}
