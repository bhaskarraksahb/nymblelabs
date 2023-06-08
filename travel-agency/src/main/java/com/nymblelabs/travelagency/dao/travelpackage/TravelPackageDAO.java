package com.nymblelabs.travelagency.dao.travelpackage;

import com.nymblelabs.travelagency.Entity.destination.Destination;
import com.nymblelabs.travelagency.Entity.passenger.Passenger;
import com.nymblelabs.travelagency.Entity.travelPackage.TravelPackage;

import java.util.List;

public interface TravelPackageDAO {
  List<TravelPackage> findAllTravelPackage();

  TravelPackage findTravelPackageById(Integer travelPackageId);

  TravelPackage saveTravelPackage(TravelPackage theTravelPackage);

  Integer deleteTravelPackageById(Integer travelPackageId);

  TravelPackage addDestination(TravelPackage travelPackage, Destination theDestination);

  TravelPackage addPassenger(TravelPackage travelPackage, Passenger thePassenger);

  List <Passenger> getPassengersByTravelPackage(Integer travelPackageId);

  List<Destination> getDestinationByTravelPackage(Integer travelPackageId);
}
