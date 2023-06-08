package com.nymblelabs.travelagency.Entity.activity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.nymblelabs.travelagency.Entity.destination.Destination;
import com.nymblelabs.travelagency.Entity.passenger.Passenger;
import jakarta.persistence.*;

@Entity
public class Activity {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Integer id;
  private String activityName;
  private String activityDescription;
  private Double activityCost;
  private Integer capacity;

  private Integer totalCapacity;

  @OneToOne(cascade = CascadeType.ALL)
  private Destination destination;

  public Integer getId() {
    return id;
  }

  public Activity() {
  }

  public Activity(Integer id, String activityName, String activityDescription, Double activityCost, Integer capacity, Destination destination) {
    this.id = id;
    this.activityName = activityName;
    this.activityDescription = activityDescription;
    this.activityCost = activityCost;
    this.capacity = capacity;
    this.destination = destination;
  }
  public Activity(String activityName, String activityDescription, Double activityCost, Integer capacity) {
    this.activityName = activityName;
    this.activityDescription = activityDescription;
    this.activityCost = activityCost;
    this.capacity = capacity;
    this.totalCapacity = capacity;
  }
  public void setId(Integer id) {
    this.id = id;
  }

  public Destination getDestination() {
    return destination;
  }

  public void setDestination(Destination destination) {
    this.destination = destination;
  }



  public Integer getTotalCapacity() {
    return totalCapacity;
  }

  public void setTotalCapacity(Integer totalCapacity) {
    this.totalCapacity = totalCapacity;
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

  public boolean signUpPassenger() {
    if(this.getCapacity() - 1 >= 0) {
      this.setCapacity(this.getCapacity() - 1);
      return true;
    }
    return false;
  }
}
