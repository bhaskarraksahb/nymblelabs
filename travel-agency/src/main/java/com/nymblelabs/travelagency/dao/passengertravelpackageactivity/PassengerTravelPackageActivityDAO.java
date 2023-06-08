package com.nymblelabs.travelagency.dao.passengertravelpackageactivity;

import com.nymblelabs.travelagency.Entity.PassengerTravelPackageActivity;

import java.util.List;

public interface PassengerTravelPackageActivityDAO {
  PassengerTravelPackageActivity savePassengerTravelPackageActivity(PassengerTravelPackageActivity theTravelPackage);

  List<PassengerTravelPackageActivity> getPassengerTravelPackageActivity(Integer passengerId);
}
