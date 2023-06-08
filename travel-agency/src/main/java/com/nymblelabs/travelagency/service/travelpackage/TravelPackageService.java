package com.nymblelabs.travelagency.service.travelpackage;

import com.nymblelabs.travelagency.Entity.destination.Destination;
import com.nymblelabs.travelagency.Entity.passenger.Passenger;
import com.nymblelabs.travelagency.Entity.travelPackage.TravelPackage;
import com.nymblelabs.travelagency.dto.PassengerDTO;
import com.nymblelabs.travelagency.dto.TravelPackageDTO;

import java.util.List;

public interface TravelPackageService {
  List<TravelPackage> findAllTravelPackage();

  TravelPackage findTravelPackageById(Integer travelPackageId);

  TravelPackage saveTravelPackage(TravelPackage theTravelPackage);

  Integer deleteTravelPackageById(Integer travelPackageId);

  TravelPackage addDestination(TravelPackage travelPackage, Destination theDestination);

  TravelPackage addPassenger(TravelPackage travelPackage, Passenger thePassenger);

  TravelPackageDTO getPassengersByTravelPackage(Integer travelPackageId);

  TravelPackageDTO getDestinationByTravelPackage(Integer destinationId);
}
