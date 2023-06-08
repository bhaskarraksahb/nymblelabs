package com.nymblelabs.travelagency.Entity.passenger;

import jakarta.persistence.Enumerated;

public enum PassengerType {
  STANDARD("Standard"),
  GOLD("Gold"),
  PREMIUM("Premium");

  private String passengerType;
  PassengerType(String passengerType) {
    this.passengerType = passengerType;
  }

  @Override
  public String toString() {
    return "PassengerType{ " + this.passengerType + "}";
  }
}
