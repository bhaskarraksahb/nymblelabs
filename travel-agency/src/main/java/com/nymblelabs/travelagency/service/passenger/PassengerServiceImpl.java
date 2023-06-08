package com.nymblelabs.travelagency.service.passenger;

import com.nymblelabs.travelagency.Entity.PassengerTravelPackageActivity;
import com.nymblelabs.travelagency.Entity.activity.Activity;
import com.nymblelabs.travelagency.Entity.destination.Destination;
import com.nymblelabs.travelagency.Entity.passenger.Passenger;
import com.nymblelabs.travelagency.Entity.passenger.PassengerType;
import com.nymblelabs.travelagency.Entity.travelPackage.TravelPackage;
import com.nymblelabs.travelagency.Exception.InvalidPostBodyException;
import com.nymblelabs.travelagency.converter.ActivityConverter;
import com.nymblelabs.travelagency.converter.DestinationConverter;
import com.nymblelabs.travelagency.converter.PassengerConverter;
import com.nymblelabs.travelagency.dao.activity.ActivityDAOJpaImpl;
import com.nymblelabs.travelagency.dao.destination.DestinationDAOJpaImpl;
import com.nymblelabs.travelagency.dao.passenger.PassengerDAOJpaImpl;
import com.nymblelabs.travelagency.dao.passengertravelpackageactivity.PassengerTravelPackageActivityDAOJpaImpl;
import com.nymblelabs.travelagency.dto.ActivityDTO;
import com.nymblelabs.travelagency.dto.DestinationDTO;
import com.nymblelabs.travelagency.dto.PassengerDTO;
import com.nymblelabs.travelagency.service.passengertravelpackageaactivity.PassengerTravelPackageService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
@Service
@Transactional
public class PassengerServiceImpl implements PassengerService{
  private PassengerDAOJpaImpl passengerDAOJpaImpl;
  private PassengerTravelPackageActivityDAOJpaImpl passengerTravelPackageActivityDAOJpaImpl;
  private DestinationDAOJpaImpl destinationDAOJpaImpl;
  private ActivityDAOJpaImpl activityDAOJpaImpl;

  @Autowired
  public PassengerServiceImpl(PassengerDAOJpaImpl passengerDAOJpaImpl, PassengerTravelPackageActivityDAOJpaImpl passengerTravelPackageActivityDAOJpaImpl, DestinationDAOJpaImpl destinationDAOJpaImpl, ActivityDAOJpaImpl activityDAOJpaImpl) {
    this.passengerDAOJpaImpl = passengerDAOJpaImpl;
    this.passengerTravelPackageActivityDAOJpaImpl = passengerTravelPackageActivityDAOJpaImpl;
    this.destinationDAOJpaImpl = destinationDAOJpaImpl;
    this.activityDAOJpaImpl = activityDAOJpaImpl;
  }

  @Override
  public List<Passenger> findAllPassengers() {
    return this.passengerDAOJpaImpl.findAllPassengers();
  }

  @Override
  public Passenger findPassengerById(Integer passengerId) {
    return this.passengerDAOJpaImpl.findPassengerById(passengerId);
  }

  @Override
  public Passenger savePassenger(Passenger thePassenger) {
    return this.passengerDAOJpaImpl.savePassenger(thePassenger);
  }

  @Override
  public Integer deletePassengerById(Integer passengerId) {
    return this.passengerDAOJpaImpl.deletePassengerById(passengerId);
  }

  @Override
  public List<TravelPackage> getTravelPackageByPassenger(Integer passengerId) {
    return this.passengerDAOJpaImpl.getTravelPackageByPassenger(passengerId);
  }

  @Override
  public Passenger signUpToActivity(Passenger passenger, TravelPackage travelPackage, Destination destination, Activity activity) {
    if (activity.getDestination().getId() != destination.getId()) {
      throw new InvalidPostBodyException("Destination does not have the activity!!!!");
    }
    boolean isPassengerPartOfTravelPackage = false;
    boolean isDestinationPartOfTravelPackage = false;
    boolean isActivityPartOfDestination = false;
    for (Passenger currentPassenger : travelPackage.getTravelPackagePassenger()) {
      if (currentPassenger.getId() == passenger.getId()) {
        isPassengerPartOfTravelPackage = true;
        break;
      }
    }
    for (Destination currentDestination : travelPackage.getTravelPackageDestinations()) {
      if (currentDestination.getId() == destination.getId()) {
        isDestinationPartOfTravelPackage = true;
        break;
      }
    }
    for (Activity currentActivity : destination.getActivityList()) {
      if (currentActivity.getId() == activity.getId()) {
        isActivityPartOfDestination = true;
        break;
      }
    }
    if (!isPassengerPartOfTravelPackage) {
      throw new InvalidPostBodyException("Passenger is not part of travel package!!!\n");
    } else if (!isDestinationPartOfTravelPackage) {
      throw new InvalidPostBodyException("Destination Not part of Travel!!!\n");
    } else if (!isActivityPartOfDestination) {
      throw new InvalidPostBodyException("Activity not part of Destination!!!!\n");
    } else if (activity.getCapacity() <= 0) {
      throw new InvalidPostBodyException("Activity capacity is full!!!\n");
    }
    Double costToDeduct = passenger.signUpForActivity(activity);
    if (costToDeduct >= 0 && activity.signUpPassenger()) {
      PassengerTravelPackageActivity passengerTravelPackageActivity = new PassengerTravelPackageActivity();
      passengerTravelPackageActivity.setDestinationId(destination.getId());
      passengerTravelPackageActivity.setActivityId(activity.getId());
      passengerTravelPackageActivity.setPassengerId(passenger.getId());
      passengerTravelPackageActivity.setPricePaid(costToDeduct);
      this.passengerTravelPackageActivityDAOJpaImpl.savePassengerTravelPackageActivity(passengerTravelPackageActivity);
      return this.passengerDAOJpaImpl.signUpActivity(passenger, travelPackage, destination, activity);
    } else {
      throw new InvalidPostBodyException("Passenger balance is less than required activity cost!!!!\n");
    }
  }

  @Override
  public PassengerDTO getPassengerDetails(Integer passengerId) {
    Passenger passenger = this.passengerDAOJpaImpl.findPassengerById(passengerId);
    PassengerDTO passengerDTO = new PassengerConverter().entityToDTO(passenger);
    List <PassengerTravelPackageActivity> passengerTravelPackageActivities = this.passengerTravelPackageActivityDAOJpaImpl.getPassengerTravelPackageActivity(passengerId);
    List <DestinationDTO> destinationList = new ArrayList<>();
    HashMap <Integer, DestinationDTO> destinationDTOHashMap = new HashMap<>();
    for(PassengerTravelPackageActivity passengerTravelPackageActivity: passengerTravelPackageActivities) {
      destinationList.add(new DestinationConverter().entityToDTO(this.destinationDAOJpaImpl.findDestinationById(passengerTravelPackageActivity.getDestinationId())));
      ActivityDTO activityDTO = new ActivityConverter().entityToDTO(this.activityDAOJpaImpl.findActivityById(passengerTravelPackageActivity.getActivityId()));
      activityDTO.setPricePaid(passengerTravelPackageActivity.getPricePaid());
      DestinationDTO destinationDTO;
      if(destinationDTOHashMap.containsKey(passengerTravelPackageActivity.getDestinationId())) {
        destinationDTO = destinationDTOHashMap.get(passengerTravelPackageActivity.getDestinationId());
      } else {
        destinationDTO = new DestinationConverter().entityToDTO(this.destinationDAOJpaImpl.findDestinationById(passengerTravelPackageActivity.getDestinationId()));
        destinationDTO.setActivityList(new ArrayList<>());
      }
      destinationDTO.getActivityList().add(activityDTO);
      destinationDTOHashMap.put(passengerTravelPackageActivity.getDestinationId(), destinationDTO);
      System.out.println(passengerTravelPackageActivity.getPassengerId() + " " + passengerTravelPackageActivity.getActivityId() + " " + passengerTravelPackageActivity.getDestinationId());
    }

    for (HashMap.Entry<Integer, DestinationDTO> entry : destinationDTOHashMap.entrySet()) {
      if(passengerDTO.getDestinationDTOList() == null) {
        passengerDTO.setDestinationDTOList(new ArrayList<>());
      }
      passengerDTO.getDestinationDTOList().add(entry.getValue());
    }
    return passengerDTO;
  }
}
