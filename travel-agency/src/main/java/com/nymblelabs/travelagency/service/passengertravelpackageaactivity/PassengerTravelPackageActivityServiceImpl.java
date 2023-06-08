package com.nymblelabs.travelagency.service.passengertravelpackageaactivity;

import com.nymblelabs.travelagency.Entity.PassengerTravelPackageActivity;
import com.nymblelabs.travelagency.dao.passengertravelpackageactivity.PassengerTravelPackageActivityDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PassengerTravelPackageActivityServiceImpl implements PassengerTravelPackageService{

  private PassengerTravelPackageActivityDAO passengerTravelPackageActivityDAO;

  @Autowired
  public PassengerTravelPackageActivityServiceImpl(PassengerTravelPackageActivityDAO passengerTravelPackageActivityDAO) {
    this.passengerTravelPackageActivityDAO = passengerTravelPackageActivityDAO;
  }

  @Override
  public PassengerTravelPackageActivity savePassengerTravelPackageActivity(PassengerTravelPackageActivity thePassengerTravelPackageActivity) {
    return this.passengerTravelPackageActivityDAO.savePassengerTravelPackageActivity(thePassengerTravelPackageActivity);
  }
}
