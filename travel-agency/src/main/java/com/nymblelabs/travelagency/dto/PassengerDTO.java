package com.nymblelabs.travelagency.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.nymblelabs.travelagency.Entity.destination.Destination;
import com.nymblelabs.travelagency.Entity.passenger.PassengerType;
import com.nymblelabs.travelagency.Entity.travelPackage.TravelPackage;
import lombok.Data;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class PassengerDTO {
  private PassengerType passengerType;
  private String passengerName;
  private String passengerNumber;
  private List<TravelPackageDTO> travelPackageList;

  private List<ActivityDTO> activityDTOList;
  private List<DestinationDTO> destinationDTOList;
  public PassengerType getPassengerType() {
    return passengerType;
  }

  public List<DestinationDTO> getDestinationDTOList() {
    return destinationDTOList;
  }

  public void setDestinationDTOList(List<DestinationDTO> destinationDTOList) {
    this.destinationDTOList = destinationDTOList;
  }

  public void setPassengerType(PassengerType passengerType) {
    this.passengerType = passengerType;
  }

  public String getPassengerName() {
    return passengerName;
  }

  public void setPassengerName(String passengerName) {
    this.passengerName = passengerName;
  }

  public String getPassengerNumber() {
    return passengerNumber;
  }

  public void setPassengerNumber(String passengerNumber) {
    this.passengerNumber = passengerNumber;
  }

  public List<TravelPackageDTO> getTravelPackageList() {
    return travelPackageList;
  }

  public void setTravelPackageList(List<TravelPackageDTO> travelPackageList) {
    this.travelPackageList = travelPackageList;
  }
}
