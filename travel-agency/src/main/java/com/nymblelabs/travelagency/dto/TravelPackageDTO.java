package com.nymblelabs.travelagency.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.nymblelabs.travelagency.Entity.destination.Destination;
import com.nymblelabs.travelagency.Entity.passenger.Passenger;
import lombok.Data;

import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TravelPackageDTO {
  private String travelPackageName;
  private Integer travelPackagePassengerCapacity;
  private List<DestinationDTO> travelPackageDestinations;

  private Integer totalCapacity;

  public Integer getTotalCapacity() {
    return totalCapacity;
  }

  public void setTotalCapacity(Integer totalCapacity) {
    this.totalCapacity = totalCapacity;
  }

  public String getTravelPackageName() {
    return travelPackageName;
  }

  public void setTravelPackageName(String travelPackageName) {
    this.travelPackageName = travelPackageName;
  }

  public Integer getTravelPackagePassengerCapacity() {
    return travelPackagePassengerCapacity;
  }

  public void setTravelPackagePassengerCapacity(Integer travelPackagePassengerCapacity) {
    this.travelPackagePassengerCapacity = travelPackagePassengerCapacity;
  }

  public List<DestinationDTO> getTravelPackageDestinations() {
    return travelPackageDestinations;
  }

  public void setTravelPackageDestinations(List<DestinationDTO> travelPackageDestinations) {
    this.travelPackageDestinations = travelPackageDestinations;
  }

  public List<PassengerDTO> getTravelPackagePassenger() {
    return travelPackagePassenger;
  }

  public void setTravelPackagePassenger(List<PassengerDTO> travelPackagePassenger) {
    this.travelPackagePassenger = travelPackagePassenger;
  }

  private List<PassengerDTO> travelPackagePassenger;
}
