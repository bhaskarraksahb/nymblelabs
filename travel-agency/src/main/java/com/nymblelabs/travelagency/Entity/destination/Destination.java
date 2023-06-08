package com.nymblelabs.travelagency.Entity.destination;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.nymblelabs.travelagency.Entity.activity.Activity;
import com.nymblelabs.travelagency.Entity.travelPackage.TravelPackage;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "travelPackageList", "activityList"})
public class Destination {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Integer id;
  private String destinationName;

  @ManyToMany(cascade = CascadeType.ALL)
  private List <TravelPackage> travelPackageList = new ArrayList<>();
  @OneToMany(cascade = CascadeType.ALL)
  private List<Activity> activityList = new ArrayList<>();
  public Destination() {
  }

  public Destination(String destinationName) {
    this.destinationName = destinationName;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public List<TravelPackage> getTravelPackageList() {
    return travelPackageList;
  }

  public void setTravelPackageList(List<TravelPackage> travelPackageList) {
    this.travelPackageList = travelPackageList;
  }

  public List<Activity> getActivityList() {
    return activityList;
  }

  public void setActivityList(List<Activity> activityList) {
    this.activityList = activityList;
  }




  public String getDestinationName() {
    return destinationName;
  }

  public void setDestinationName(String destinationName) {
    this.destinationName = destinationName;
  }
}
