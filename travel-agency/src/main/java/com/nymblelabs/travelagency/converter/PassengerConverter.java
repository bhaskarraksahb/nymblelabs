package com.nymblelabs.travelagency.converter;

import com.nymblelabs.travelagency.Entity.passenger.Passenger;
import com.nymblelabs.travelagency.dto.PassengerDTO;
/**
 * Converts Passenger to PassengerDTO
 */
public class PassengerConverter {
  public PassengerDTO entityToDTO(Passenger passenger) {
    PassengerDTO passengerDTO = new PassengerDTO();
    passengerDTO.setPassengerName(passenger.getPassengerName());
    passengerDTO.setPassengerType(passenger.getPassengerType());
    //passengerDTO.setTravelPackageList(passenger.getTravelPackageList());
    passengerDTO.setPassengerNumber(passenger.getPassengerNumber());
    return passengerDTO;
  }
}
