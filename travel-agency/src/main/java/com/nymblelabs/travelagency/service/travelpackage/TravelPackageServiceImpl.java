package com.nymblelabs.travelagency.service.travelpackage;

import com.nymblelabs.travelagency.Entity.activity.Activity;
import com.nymblelabs.travelagency.Entity.destination.Destination;
import com.nymblelabs.travelagency.Entity.passenger.Passenger;
import com.nymblelabs.travelagency.Entity.travelPackage.TravelPackage;
import com.nymblelabs.travelagency.Exception.InvalidPostBodyException;
import com.nymblelabs.travelagency.converter.DestinationConverter;
import com.nymblelabs.travelagency.converter.PassengerConverter;
import com.nymblelabs.travelagency.converter.TravelPackageConverter;
import com.nymblelabs.travelagency.dao.destination.DestinationDAOJpaImpl;
import com.nymblelabs.travelagency.dao.travelpackage.TravelPackageDAOJpaImpl;
import com.nymblelabs.travelagency.dto.ActivityDTO;
import com.nymblelabs.travelagency.dto.DestinationDTO;
import com.nymblelabs.travelagency.dto.PassengerDTO;
import com.nymblelabs.travelagency.dto.TravelPackageDTO;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
@Transactional
public class TravelPackageServiceImpl implements TravelPackageService {
  private TravelPackageDAOJpaImpl travelPackageDAOJpaImpl;

  private DestinationDAOJpaImpl destinationDAOJpaImpl;
  @Autowired
  public TravelPackageServiceImpl(TravelPackageDAOJpaImpl travelPackageDAOJpaImpl, DestinationDAOJpaImpl destinationDAOJpaImpl) {
    this.travelPackageDAOJpaImpl = travelPackageDAOJpaImpl;
    this.destinationDAOJpaImpl = destinationDAOJpaImpl;
  }
  @Override
  public List<TravelPackage> findAllTravelPackage() {
    return this.travelPackageDAOJpaImpl.findAllTravelPackage();
  }

  @Override
  public TravelPackage findTravelPackageById(Integer travelPackageId) {
    return this.travelPackageDAOJpaImpl.findTravelPackageById(travelPackageId);
  }

  @Override
  public TravelPackage saveTravelPackage(TravelPackage theTravelPackage) {
    return this.travelPackageDAOJpaImpl.saveTravelPackage(theTravelPackage);
  }

  @Override
  public Integer deleteTravelPackageById(Integer travelPackageId) {
    return this.travelPackageDAOJpaImpl.deleteTravelPackageById(travelPackageId);
  }

  @Override
  public TravelPackage addDestination(TravelPackage travelPackage, Destination theDestination) {
    return this.travelPackageDAOJpaImpl.addDestination(travelPackage, theDestination);
  }

  @Override
  public TravelPackage addPassenger(TravelPackage travelPackage, Passenger thePassenger) {
    if (travelPackage.getTravelPackagePassengerCapacity() - 1 < 0) {
      throw new InvalidPostBodyException("Travel Package capacity is full!!!!\n");
    }
    return this.travelPackageDAOJpaImpl.addPassenger(travelPackage, thePassenger);
  }

  @Override
  public TravelPackageDTO getPassengersByTravelPackage(Integer travelPackageId) {
    List<Passenger> passengerList = this.travelPackageDAOJpaImpl.getPassengersByTravelPackage(travelPackageId);
    List <PassengerDTO> passengerDTOList = new ArrayList<>();
    TravelPackage travelPackage = this.travelPackageDAOJpaImpl.findTravelPackageById(travelPackageId);
    TravelPackageDTO travelPackageDTO = new TravelPackageConverter().entityToDTO(travelPackage);
    for(Passenger passenger: passengerList) {
      passengerDTOList.add(new PassengerConverter().entityToDTO(passenger));
    }
    travelPackageDTO.setTravelPackageDestinations(null);
    travelPackageDTO.setTravelPackagePassenger(passengerDTOList);
    return travelPackageDTO;
  }

  @Override
  public TravelPackageDTO getDestinationByTravelPackage(Integer travelPackageId) {
    TravelPackage travelPackage = this.travelPackageDAOJpaImpl.findTravelPackageById(travelPackageId);
    TravelPackageDTO travelPackageDTO = new TravelPackageConverter().entityToDTO(travelPackage);
    travelPackageDTO.setTravelPackagePassenger(null);
    travelPackageDTO.setTravelPackagePassengerCapacity(null);
    List <Destination> destinationList = this.travelPackageDAOJpaImpl.getDestinationByTravelPackage(travelPackageId);
    List <DestinationDTO> destinationDTOList = new ArrayList<>();
    for(Destination destination: destinationList) {
      DestinationDTO destinationDTO = new DestinationConverter().entityToDTO(destination);
      destinationDTOList.add(destinationDTO);
    }
    travelPackageDTO.setTravelPackageDestinations(destinationDTOList);
    return travelPackageDTO;
  }
}
