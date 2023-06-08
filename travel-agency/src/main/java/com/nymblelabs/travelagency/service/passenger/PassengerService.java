package com.nymblelabs.travelagency.service.passenger;

import com.nymblelabs.travelagency.Entity.activity.Activity;
import com.nymblelabs.travelagency.Entity.destination.Destination;
import com.nymblelabs.travelagency.Entity.passenger.Passenger;
import com.nymblelabs.travelagency.Entity.travelPackage.TravelPackage;
import com.nymblelabs.travelagency.dto.PassengerDTO;

import java.util.List;

public interface PassengerService {
  List<Passenger> findAllPassengers();
  Passenger findPassengerById(Integer passengerId);

  Passenger savePassenger(Passenger thePassenger);

  Integer deletePassengerById(Integer PassengerId);

  List <TravelPackage> getTravelPackageByPassenger(Integer passengerId);

  Passenger signUpToActivity(Passenger passenger, TravelPackage travelPackage, Destination destination, Activity activity);

  PassengerDTO getPassengerDetails(Integer passengerId);
}
