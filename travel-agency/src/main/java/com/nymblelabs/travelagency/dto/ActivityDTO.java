package com.nymblelabs.travelagency.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.nymblelabs.travelagency.Entity.destination.Destination;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class ActivityDTO {
  private String activityName;
  private String activityDescription;
  private Double activityCost;
  private Integer capacity;

  private Integer totalCapacity;
  private DestinationDTO destination;
  private Double pricePaid;
  private Integer openSpaces;

  public Integer getOpenSpaces() {
    return openSpaces;
  }

  public void setOpenSpaces(Integer openSpaces) {
    this.openSpaces = openSpaces;
  }

  public Double getPricePaid() {
    return pricePaid;
  }

  public void setPricePaid(Double pricePaid) {
    this.pricePaid = pricePaid;
  }

  public DestinationDTO getDestination() {
    return destination;
  }

  public Integer getTotalCapacity() {
    return totalCapacity;
  }

  public void setTotalCapacity(Integer totalCapacity) {
    this.totalCapacity = totalCapacity;
  }

  public void setDestination(DestinationDTO destination) {
    this.destination = destination;
  }

  public String getActivityName() {
    return activityName;
  }

  public void setActivityName(String activityName) {
    this.activityName = activityName;
  }

  public String getActivityDescription() {
    return activityDescription;
  }

  public void setActivityDescription(String activityDescription) {
    this.activityDescription = activityDescription;
  }

  public Double getActivityCost() {
    return activityCost;
  }

  public void setActivityCost(Double activityCost) {
    this.activityCost = activityCost;
  }

  public Integer getCapacity() {
    return capacity;
  }

  public void setCapacity(Integer capacity) {
    this.capacity = capacity;
  }
}
