package com.nymblelabs.travelagency.Entity.passenger;

import com.nymblelabs.travelagency.Entity.activity.Activity;
import com.nymblelabs.travelagency.Entity.travelPackage.TravelPackage;
import jakarta.persistence.*;

import java.util.List;

@Entity
public class Passenger extends AbstractPassenger{


  private Double passengerBalance;

  public Passenger() {
  }
  public Passenger(Double passengerBalance) {
    this.passengerBalance = passengerBalance;
  }

  public Passenger(PassengerType passengerType, String passengerName, String passengerNumber) {
    super(passengerType, passengerName, passengerNumber);
    this.passengerBalance = null;
  }
  public Passenger(PassengerType passengerType, String passengerName, String passengerNumber, Double passengerBalance) {
    super(passengerType, passengerName, passengerNumber);
    this.passengerBalance = passengerBalance;
  }


  public Double getPassengerBalance() {
    return passengerBalance;
  }

  public void setPassengerBalance(Double passengerBalance) {
    this.passengerBalance = passengerBalance;
  }

  public Passenger(PassengerType passengerType, String passengerName, String passengerNumber, List<TravelPackage> travelPackageList, Double passengerBalance) {
    super(passengerType, passengerName, passengerNumber, travelPackageList);
    this.passengerBalance = passengerBalance;
  }

  public Passenger(PassengerType passengerType, String passengerName, String passengerNumber, List<TravelPackage> travelPackageList) {
    super(passengerType, passengerName, passengerNumber, travelPackageList);
  }

  @Override
  public Double signUpForActivity(Activity activity) {
    Double costToMinus = activity.getActivityCost();
    if (this.getPassengerType() == PassengerType.GOLD) {
      costToMinus = (activity.getActivityCost() * 0.9);
    } else if (this.getPassengerType() == PassengerType.PREMIUM) {
      costToMinus = Double.valueOf(0);
    }
    if (this.getPassengerBalance() - costToMinus >= 0) {
      this.setPassengerBalance(this.getPassengerBalance() - costToMinus);
      return costToMinus;
    }
    costToMinus = Double.valueOf(-1);
    return costToMinus;
  }
}
