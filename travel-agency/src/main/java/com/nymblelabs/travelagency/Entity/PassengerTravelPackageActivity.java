package com.nymblelabs.travelagency.Entity;

import jakarta.persistence.*;

@Entity
public class PassengerTravelPackageActivity {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Integer id;

  private Integer destinationId;
  private Integer passengerId;
  private Integer activityId;
  private Double pricePaid;

  public Double getPricePaid() {
    return pricePaid;
  }

  public void setPricePaid(Double pricePaid) {
    this.pricePaid = pricePaid;
  }

  public Integer getDestinationId() {
    return destinationId;
  }

  public void setDestinationId(Integer destinationId) {
    this.destinationId = destinationId;
  }

  public Integer getPassengerId() {
    return passengerId;
  }

  public void setPassengerId(Integer passengerId) {
    this.passengerId = passengerId;
  }

  public Integer getActivityId() {
    return activityId;
  }

  public void setActivityId(Integer activityId) {
    this.activityId = activityId;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }
}
