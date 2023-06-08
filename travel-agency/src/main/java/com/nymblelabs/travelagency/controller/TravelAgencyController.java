package com.nymblelabs.travelagency.controller;

import com.nymblelabs.travelagency.Entity.activity.Activity;
import com.nymblelabs.travelagency.Entity.destination.Destination;
import com.nymblelabs.travelagency.Entity.passenger.Passenger;
import com.nymblelabs.travelagency.Entity.travelPackage.TravelPackage;
import com.nymblelabs.travelagency.Exception.InvalidPostBodyException;
import com.nymblelabs.travelagency.dto.ActivityDTO;
import com.nymblelabs.travelagency.dto.PassengerDTO;
import com.nymblelabs.travelagency.dto.TravelPackageDTO;
import com.nymblelabs.travelagency.service.activity.ActivityService;
import com.nymblelabs.travelagency.service.destination.DestinationService;
import com.nymblelabs.travelagency.service.passenger.PassengerService;
import com.nymblelabs.travelagency.service.travelpackage.TravelPackageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class TravelAgencyController {
  private ActivityService activityService;
  private DestinationService destinationService;
  private PassengerService passengerService;
  private TravelPackageService travelPackageService;
  @Autowired
  public TravelAgencyController(ActivityService activityService, DestinationService destinationService, PassengerService passengerService, TravelPackageService travelPackageService) {
    this.activityService = activityService;
    this.destinationService = destinationService;
    this.passengerService = passengerService;
    this.travelPackageService = travelPackageService;
  }

  /**
   * To create a new travel package
   * @param travelPackage
   * @return response entity travelPackage
   */
  @PostMapping(value = "/travelPackage/create")
  public ResponseEntity<TravelPackage> createNewTravelPackage(@RequestBody @Valid TravelPackage travelPackage) {
    if(travelPackage == null || travelPackage.getTravelPackageName() == null || travelPackage.getTravelPackageName().isBlank() ||
                      travelPackage.getTravelPackagePassengerCapacity() == null ||
                      travelPackage.getTravelPackagePassengerCapacity() == 0) {
      throw new InvalidPostBodyException("Invalid name or capacity In the request \n");
    }
    return new ResponseEntity<>(this.travelPackageService.saveTravelPackage(travelPackage), HttpStatus.CREATED);
  }

  /**
   * Adds a destination to a travel package to existing travel package.
   *
   * @param travelPackageId .
   * @param destination The destination to add.
   */
  @PostMapping("/travelPackage/{travelPackageId}/destination")
  public ResponseEntity<TravelPackage> addDestinationByObject(
          @PathVariable Integer travelPackageId,
          @RequestBody Destination destination
  ) {
    if(travelPackageId == null) {
      throw new InvalidPostBodyException("Travel Package Id cannot be null");
    } else if(destination == null || destination.getDestinationName() == null) {
      throw new InvalidPostBodyException("Destination and Destination Name cannot be null \n");
    }
    TravelPackage travelPackage = this.travelPackageService.findTravelPackageById(travelPackageId);
    if(travelPackage == null) {
      throw new InvalidPostBodyException("Invalid Travel Package Id, No travel Package exist!!!\n");
    }
    return new ResponseEntity<>(this.travelPackageService.addDestination(travelPackage, destination), HttpStatus.CREATED);
  }

  /**
   * Adds destination to existing travel package by id
   * @param travelPackageId
   * @param destinationId
   * @return
   */
  @PostMapping("/travelPackage/{travelPackageId}/destination/{destinationId}")
  public ResponseEntity<TravelPackage> addDestinationById(
          @PathVariable Integer travelPackageId,
          @PathVariable Integer destinationId
  ) {
    if(travelPackageId == null) {
      throw new InvalidPostBodyException("Travel Package Id cannot be null");
    } else if(destinationId == null) {
      throw new InvalidPostBodyException("Destination Id cannot be null \n");
    }
    TravelPackage travelPackage = this.travelPackageService.findTravelPackageById(travelPackageId);
    if(travelPackage == null) {
      throw new InvalidPostBodyException("Invalid Travel Package Id, No travel Package exist!!!\n");
    }
    Destination destination = this.destinationService.findDestinationById(destinationId);
    if(destination == null) {
      throw new InvalidPostBodyException("Invalid Destination Id, No Destination exists!!!\n");
    }
    return new ResponseEntity<>(this.travelPackageService.addDestination(travelPackage, destination), HttpStatus.CREATED);
  }

  /**
   * Creates a new destination
   * @param destination
   * @return
   */
  @PostMapping("/destination/create")
  public ResponseEntity<Destination> createDestination(
          @RequestBody Destination destination
  ) {
    if(destination == null || destination.getDestinationName() == null) {
      throw new InvalidPostBodyException("Destination and Destination Name cannot be null!!!!\n");
    }
    return new ResponseEntity<>(this.destinationService.saveDestination(destination), HttpStatus.CREATED);
  }

  /**
   * Adds an activity to already existing travel package and destination
   * @param travelPackageId
   * @param destinationId
   * @param activity
   * @return
   */
  @PostMapping("/travelPackage/{travelPackageId}/destinations/{destinationId}/activities")
  public ResponseEntity<Destination> addActivityToDestination(
          @PathVariable Integer travelPackageId,
          @PathVariable Integer destinationId,
          @RequestBody Activity activity
  ) {
    if(travelPackageId == null) {
      throw new InvalidPostBodyException("Invalid TravelPackageId, TravelPackageId cannot be null\n");
    } else if(destinationId == null) {
      throw new InvalidPostBodyException("Invalid DestinationId, DestinationId cannot be null\n");
    } else if(activity == null) {
      throw new InvalidPostBodyException("Activity cannot be empty!!! \n");
    } else if(activity.getActivityCost() == null || activity.getCapacity() == null) {
      throw new InvalidPostBodyException("Activity must have cost associated and capacity!!! \n");
    }
    TravelPackage travelPackage = this.travelPackageService.findTravelPackageById(travelPackageId);
    Destination destination = this.destinationService.findDestinationById(destinationId);
    if(travelPackage == null) {
      throw new InvalidPostBodyException("Invalid TravelPackage!!!! \n");
    } else if(destination == null) {
      throw new InvalidPostBodyException("Invalid Destination!!!! \n");
    } else if(activity.getDestination() != null && destination.getId() == activity.getDestination().getId()) {
      throw new InvalidPostBodyException("Activity can be associated with only one destination!!!!\n");
    }
    return new ResponseEntity<>(this.destinationService.addDestinationActivity(destination, activity), HttpStatus.CREATED);
  }

  /**
   * Adds existing activity to destination
   * @param travelPackageId
   * @param destinationId
   * @param activityId
   * @return
   */
  @PostMapping("/destinations/{destinationId}/activities/{activityId}")
  public ResponseEntity<Destination> addActivityToDestination(
          @PathVariable Integer destinationId,
          @PathVariable Integer activityId
  ) {
    if(destinationId == null) {
      throw new InvalidPostBodyException("Invalid DestinationId, DestinationId cannot be null\n");
    } else if(activityId == null) {
      throw new InvalidPostBodyException("Activity cannot be empty!!! \n");
    }
    Destination destination = this.destinationService.findDestinationById(destinationId);
    Activity activity = this.activityService.findActivityById(activityId);
    if(destination == null) {
      throw new InvalidPostBodyException("Invalid Destination!!!! \n");
    } else if(activity.getDestination() != null && destination.getId() == activity.getDestination().getId()) {
      throw new InvalidPostBodyException("Activity can be associated with only one destination!!!!\n");
    }
    return new ResponseEntity<>(this.destinationService.addDestinationActivity(destination, activity), HttpStatus.CREATED);
  }

  /**
   * Creates a new passenger
   * @param passenger
   * @return
   */
  @PostMapping("/passenger/create")
  public ResponseEntity<Passenger> createPassenger(
          @RequestBody Passenger passenger
  ) {
    if (passenger == null || passenger.getPassengerName() == null || passenger.getPassengerName().isBlank()) {
      throw new InvalidPostBodyException("Passenger Name cannot be empty or null!!!!\n");
    } else if (passenger.getPassengerNumber() == null) {
      throw new InvalidPostBodyException("Passenger Number cannot be empty");
    }
    return new ResponseEntity<>(this.passengerService.savePassenger(passenger), HttpStatus.CREATED);
  }

  /**
   * Adds a new Passenger to travelpackage
   * @param travelPackageId
   * @param passenger
   * @return
   */
  @PostMapping("/travelPackage/{travelPackageId}/passengers")
  public ResponseEntity<TravelPackage> addPassengerToTravelPackage(
          @PathVariable Integer travelPackageId,
          @RequestBody Passenger passenger
  ) {
    if(travelPackageId == null) {
      throw new InvalidPostBodyException("Travel Package Id cannot be NULL!!!\n");
    } else if(passenger.getPassengerName() == null || passenger.getPassengerName().isBlank()) {
      throw new InvalidPostBodyException("Passenger Name cannot be empty or null!!!!\n");
    } else if(passenger.getPassengerNumber() == null) {
      throw new InvalidPostBodyException("Passenger Number cannot be empty");
    }
    TravelPackage travelPackage = this.travelPackageService.findTravelPackageById(travelPackageId);
    if(travelPackage == null) {
      throw new InvalidPostBodyException("Invalid Travel Package Id!!!!\n");
    }
    return new ResponseEntity<>(this.travelPackageService.addPassenger(travelPackage, passenger), HttpStatus.CREATED);
  }

  /**
   * Adds existing passenger to travel package
   * @param travelPackageId
   * @param passengerId
   * @return
   */
  @PostMapping("/travelPackage/{travelPackageId}/passengers/{passengerId}")
  public ResponseEntity<TravelPackage> addPassengerToTravelPackage(
          @PathVariable Integer travelPackageId,
          @PathVariable Integer passengerId
  ) {
    if(travelPackageId == null) {
      throw new InvalidPostBodyException("Travel Package Id cannot be NULL!!!\n");
    } else if(passengerId == null) {
      throw new InvalidPostBodyException("Passenger Id is null!!!!!\n");
    }
    TravelPackage travelPackage = this.travelPackageService.findTravelPackageById(travelPackageId);
    Passenger passenger = this.passengerService.findPassengerById(passengerId);
    if(travelPackage == null) {
      throw new InvalidPostBodyException("Invalid Travel Package Id!!!!\n");
    } else if(passenger == null) {
      throw new InvalidPostBodyException("Invalid Passenger Id!!!!\n");
    }
    return new ResponseEntity<>(this.travelPackageService.addPassenger(travelPackage, passenger), HttpStatus.CREATED);
  }

  /**
   * Creates a new Activity
   * @param activity
   * @return
   */
  @PostMapping("/activity/create")
  public ResponseEntity<Activity> createActivity(
          @RequestBody Activity activity
  ) {
    if (activity == null || activity.getActivityName() == null || activity.getActivityName().isBlank()) {
      throw new InvalidPostBodyException("activity Name cannot be empty or null!!!!\n");
    } else if (activity.getActivityCost() == null || activity.getCapacity() == null) {
      throw new InvalidPostBodyException("activity cost and capacity cannot be empty");
    }
    return new ResponseEntity<>(this.activityService.saveActivity(activity), HttpStatus.CREATED);
  }

  /**
   * Returns activities with the provided destination
   * @param destinationId
   * @return
   */
  @GetMapping("/activity/destination/{destinationId}")
  public ResponseEntity<List<Activity>> getActivityByDestinationId(
          @PathVariable Integer destinationId
  ) {
    if(destinationId == null) {
      throw new InvalidPostBodyException("destinationId Id cannot be null");
    }
    return new ResponseEntity<>(this.activityService.findAllActivityByDestination(destinationId), HttpStatus.OK);
  }

  /**
   * Adds activity to a passenger
   * @param passengerId
   * @param activityId
   * @param destinationId
   * @param travelPackageId
   * @return
   */
  @PostMapping("/passengers/{passengerId}/activities")
  public ResponseEntity<Passenger> signUpPassengerForActivity(
          @PathVariable Integer passengerId,
          @RequestParam Integer activityId,
          @RequestParam Integer destinationId,
          @RequestParam Integer travelPackageId
  ) {
    if(passengerId == null) {
      throw new InvalidPostBodyException("PassengerId cannot be null \n");
    } else if(activityId == null) {
      throw new InvalidPostBodyException("ActivityId cannot be null\n");
    } else if(destinationId == null) {
      throw new InvalidPostBodyException("DestinationId cannot be null\n");
    } else if(travelPackageId == null) {
      throw new InvalidPostBodyException("Travel PackageId cannot be null\n");
    }
    Passenger passenger = this.passengerService.findPassengerById(passengerId);
    Activity activity = this.activityService.findActivityById(activityId);
    Destination destination = this.destinationService.findDestinationById(destinationId);
    TravelPackage travelPackage = this.travelPackageService.findTravelPackageById(travelPackageId);
    if(passenger == null) {
      throw new InvalidPostBodyException("Passenger is invalid!!!\n");
    } else if(activity == null) {
      throw new InvalidPostBodyException("Activity is invalid!!!\n");
    } else if(destination == null) {
      throw new InvalidPostBodyException("Destination is invalid!!!\n");
    } else if(travelPackage == null) {
      throw new InvalidPostBodyException("travel package is invalid!!!\n");
    }
    return new ResponseEntity<>(this.passengerService.signUpToActivity(passenger, travelPackage, destination, activity), HttpStatus.CREATED);
  }

  /**
   * Prints all the destinations for the travelpackageId
   *
   * @param packageName The name of the travel package.
   */
  @GetMapping("/travelPackage/{travelPackageId}/itinerary")
  public ResponseEntity<TravelPackageDTO> getItinerarys(@PathVariable Integer travelPackageId) {
    if(travelPackageId == null) {
      throw new InvalidPostBodyException("Travel Package Id cannot be null!!!\n");
    }
    TravelPackage travelPackage = this.travelPackageService.findTravelPackageById(travelPackageId);
    if(travelPackage == null) {
      throw new InvalidPostBodyException("Travel Package Id is invalid!!!\n");
    }
    return new ResponseEntity<>(this.travelPackageService.getDestinationByTravelPackage(travelPackage.getId()), HttpStatus.OK);
  }

  /**
   * Prints passengers for a travelPackageId
   *
   * @param packageName    The name of the travel package.
   * @param destinationName The name of the destination.
   */
  @GetMapping("/travelPackage/{travelPackageId}/passengerList")
  public ResponseEntity<TravelPackageDTO> printPassengerList(
          @PathVariable Integer travelPackageId
  ) {
    if(travelPackageId == null) {
      throw new InvalidPostBodyException("Travel Package Id cannot be NULL!!!\n");
    }
    TravelPackage travelPackage = this.travelPackageService.findTravelPackageById(travelPackageId);
    if(travelPackage == null) {
      throw new InvalidPostBodyException("Travel Package Id is Invalid !!!\n");
    }
    return new ResponseEntity<>(this.travelPackageService.getPassengersByTravelPackage(travelPackage.getId()), HttpStatus.OK);
  }

  /**
   * Prints the details of a passenger.
   *
   * @param passengerNumber The passenger number.
   */
  @GetMapping("/passengers/{passengerId}")
  public ResponseEntity<PassengerDTO> printPassengerDetails(@PathVariable Integer passengerId) {
    if(passengerId == null) {
      throw new InvalidPostBodyException("Passenger Id cannot be null!!!\n");
    }
    Passenger passenger = this.passengerService.findPassengerById(passengerId);
    if(passenger == null) {
      throw new InvalidPostBodyException("Passenger Id is Invalid!!!\n");
    }
    return new ResponseEntity<>(this.passengerService.getPassengerDetails(passengerId), HttpStatus.OK);
  }

  /**
   * Prints passengers for given TravelPackage
   * @param travelPackageId
   * @return
   */
  @GetMapping("/travelPackage/{travelPackageId}/passengers")
  public ResponseEntity<TravelPackageDTO> printTravelPassengerDetails(
          @PathVariable Integer travelPackageId
  ) {
    if(travelPackageId == null) {
      throw new InvalidPostBodyException("Passenger Id cannot be null!!!\n");
    }
    TravelPackage travelPackage = this.travelPackageService.findTravelPackageById(travelPackageId);
    if(travelPackage == null) {
      throw new InvalidPostBodyException("Invalid travel package id provided!!!\n");
    }
    return new ResponseEntity<>(this.travelPackageService.getPassengersByTravelPackage(travelPackageId), HttpStatus.OK);
  }

  /**
   * Prints all the open activities
   * @return
   */
  @GetMapping("/activity/open")
  public ResponseEntity <List<ActivityDTO>> printActivityBookingStillPossible() {
    return new ResponseEntity<>(this.activityService.getAllActivities(), HttpStatus.OK);
  }
}
