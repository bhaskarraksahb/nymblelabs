package com.nymblelabs.travelagency.Entity.passenger;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.nymblelabs.travelagency.Entity.activity.Activity;
import com.nymblelabs.travelagency.Entity.travelPackage.TravelPackage;
import jakarta.persistence.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
@MappedSuperclass
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "travelPackageList"})
public abstract class AbstractPassenger {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;
  @Enumerated(EnumType.STRING)
  private PassengerType passengerType;
  private String passengerName;
  private String passengerNumber;
  @ManyToMany(cascade = CascadeType.ALL)
  private List<TravelPackage> travelPackageList = new ArrayList<>();

  public AbstractPassenger() {
  }
  @Autowired
  public AbstractPassenger(PassengerType passengerType, String passengerName, String passengerNumber) {
    this.passengerName = passengerName;
    this.passengerNumber = passengerNumber;
    this.passengerType = passengerType;
  }
  @Autowired
  public AbstractPassenger(PassengerType passengerType, String passengerName, String passengerNumber, List<TravelPackage> travelPackageList) {
    this.passengerType = passengerType;
    this.passengerName = passengerName;
    this.passengerNumber = passengerNumber;
    this.travelPackageList = travelPackageList;
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
  public PassengerType getPassengerType() {
    return passengerType;
  }

  public String getPassengerName() {
    return passengerName;
  }

  public String getPassengerNumber() {
    return passengerNumber;
  }

  public void setPassengerType(PassengerType passengerType) {
    this.passengerType = passengerType;
  }

  public void setPassengerName(String passengerName) {
    this.passengerName = passengerName;
  }

  public abstract Double signUpForActivity(Activity activity);
  public void setPassengerNumber(String passengerNumber) {
    this.passengerNumber = passengerNumber;
  }
}
