package com.nymblelabs.travelagency.dao.passenger;

import com.nymblelabs.travelagency.Entity.activity.Activity;
import com.nymblelabs.travelagency.Entity.destination.Destination;
import com.nymblelabs.travelagency.Entity.passenger.Passenger;
import com.nymblelabs.travelagency.Entity.travelPackage.TravelPackage;

import java.util.List;

public interface PassengerDAO {
  List<Passenger> findAllPassengers();
  Passenger findPassengerById(Integer passengerId);

  Passenger savePassenger(Passenger thePassenger);

  Integer deletePassengerById(Integer PassengerId);

  List <TravelPackage> getTravelPackageByPassenger(Integer passengerId);
  Passenger signUpActivity(Passenger passenger, TravelPackage travelPackage, Destination destination, Activity activity);
}
