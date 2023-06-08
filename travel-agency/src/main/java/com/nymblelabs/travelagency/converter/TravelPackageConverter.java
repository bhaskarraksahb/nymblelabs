package com.nymblelabs.travelagency.converter;

import com.nymblelabs.travelagency.Entity.destination.Destination;
import com.nymblelabs.travelagency.Entity.passenger.Passenger;
import com.nymblelabs.travelagency.Entity.travelPackage.TravelPackage;
import com.nymblelabs.travelagency.dto.DestinationDTO;
import com.nymblelabs.travelagency.dto.PassengerDTO;
import com.nymblelabs.travelagency.dto.TravelPackageDTO;

import java.util.ArrayList;
import java.util.List;
/**
 * Converts TravelPackage to TravelPackageDTO
 */
public class TravelPackageConverter {
  public TravelPackageDTO entityToDTO(TravelPackage travelPackage) {
    TravelPackageDTO travelPackageDTO = new TravelPackageDTO();
    List<DestinationDTO> destinationDTOList = new ArrayList<>();
    for(Destination destination: travelPackage.getTravelPackageDestinations()) {
      destinationDTOList.add(new DestinationConverter().entityToDTO(destination));
    }
    travelPackageDTO.setTravelPackageDestinations(destinationDTOList);
    travelPackageDTO.setTravelPackageName(travelPackage.getTravelPackageName());
    List<PassengerDTO> passengerDTOList = new ArrayList<>();
    for(Passenger passenger: travelPackage.getTravelPackagePassenger()) {
      passengerDTOList.add(new PassengerConverter().entityToDTO(passenger));
    }
    travelPackageDTO.setTravelPackagePassenger(passengerDTOList);
    travelPackageDTO.setTravelPackagePassengerCapacity(travelPackage.getTravelPackagePassengerCapacity());
    travelPackageDTO.setTotalCapacity(travelPackage.getTotalCapacity());
    return travelPackageDTO;
  }
}
