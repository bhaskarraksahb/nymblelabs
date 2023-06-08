package com.nymblelabs.travelagency.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.nymblelabs.travelagency.Entity.activity.Activity;
import com.nymblelabs.travelagency.Entity.travelPackage.TravelPackage;
import lombok.Data;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class DestinationDTO {
  private String destinationName;
  private List<TravelPackageDTO> travelPackageList;

  public String getDestinationName() {
    return destinationName;
  }

  public void setDestinationName(String destinationName) {
    this.destinationName = destinationName;
  }

  public List<TravelPackageDTO> getTravelPackageList() {
    return travelPackageList;
  }

  public void setTravelPackageList(List<TravelPackageDTO> travelPackageList) {
    this.travelPackageList = travelPackageList;
  }

  public List<ActivityDTO> getActivityList() {
    return activityList;
  }

  public void setActivityList(List<ActivityDTO> activityList) {
    this.activityList = activityList;
  }

  private List<ActivityDTO> activityList;
}
