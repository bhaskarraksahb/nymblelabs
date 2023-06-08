package com.nymblelabs.travelagency.Entity.travelPackage;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.nymblelabs.travelagency.Entity.destination.Destination;
import com.nymblelabs.travelagency.Entity.passenger.AbstractPassenger;
import com.nymblelabs.travelagency.Entity.passenger.Passenger;
import jakarta.annotation.Nonnull;
import jakarta.persistence.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "travelPackageDestinations", "travelPackagePassenger"})
public class TravelPackage {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Integer id;

  @NotEmpty(message = "travel package name Cannot be Null")
  private String travelPackageName;

  private Integer totalCapacity;
  @NotEmpty(message = "passenger capacity Cannot be Null")
  private Integer travelPackagePassengerCapacity;
  @ManyToMany(mappedBy = "travelPackageList", cascade = CascadeType.ALL)
  private List<Destination> travelPackageDestinations = new ArrayList<>();

  @ManyToMany(mappedBy = "travelPackageList", cascade = CascadeType.ALL)
  private List<Passenger> travelPackagePassenger = new ArrayList<>();
  public TravelPackage(String travelPackageName, Integer travelPackagePassengerCapacity, List<Destination> travelPackageDestinations, List<Passenger> travelPackagePassenger) {
    this.travelPackageName = travelPackageName;
    this.travelPackagePassengerCapacity = travelPackagePassengerCapacity;
    this.totalCapacity = travelPackagePassengerCapacity;
    this.travelPackageDestinations = travelPackageDestinations;
    this.travelPackagePassenger = travelPackagePassenger;
  }
  @Autowired
  public TravelPackage(List<Passenger> travelPackagePassenger) {
    this.travelPackagePassenger = travelPackagePassenger;
  }

  public TravelPackage(String travelPackageName, Integer travelPackagePassengerCapacity) {
    this.travelPackageName = travelPackageName;
    this.travelPackagePassengerCapacity = travelPackagePassengerCapacity;
    this.totalCapacity = travelPackagePassengerCapacity;
  }

  public TravelPackage() {
  }
  public Integer getTotalCapacity() {
    return totalCapacity;
  }

  public void setTotalCapacity(Integer totalCapacity) {
    this.totalCapacity = totalCapacity;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public List<Destination> getTravelPackageDestinations() {
    return travelPackageDestinations;
  }

  public void setTravelPackageDestinations(List<Destination> travelPackageDestinations) {
    this.travelPackageDestinations = travelPackageDestinations;
  }

  public List<Passenger> getTravelPackagePassenger() {
    return travelPackagePassenger;
  }

  public void setTravelPackagePassenger(List<Passenger> travelPackagePassenger) {
    this.travelPackagePassenger = travelPackagePassenger;
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
}
