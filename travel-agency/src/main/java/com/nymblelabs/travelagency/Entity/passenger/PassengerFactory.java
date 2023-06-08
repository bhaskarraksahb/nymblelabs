package com.nymblelabs.travelagency.Entity.passenger;

public class PassengerFactory {
  public static AbstractPassenger getPassengerForType(String passengerName, String passengerNumber, PassengerType passengerType, Double passengerBalance) {
    switch (passengerType) {
      case GOLD, STANDARD -> { return new Passenger(passengerType, passengerName, passengerNumber, passengerBalance);}
      case PREMIUM -> { return new Passenger(passengerType, passengerName, passengerNumber);}
    }
    return null;
  }
}
