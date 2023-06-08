package com.nymblelabs.travelagency.converter;

import com.nymblelabs.travelagency.Entity.activity.Activity;
import com.nymblelabs.travelagency.Entity.destination.Destination;
import com.nymblelabs.travelagency.Entity.passenger.Passenger;
import com.nymblelabs.travelagency.Entity.travelPackage.TravelPackage;
import com.nymblelabs.travelagency.dto.ActivityDTO;
import com.nymblelabs.travelagency.dto.DestinationDTO;
import com.nymblelabs.travelagency.dto.PassengerDTO;
import com.nymblelabs.travelagency.dto.TravelPackageDTO;

import java.util.ArrayList;
import java.util.List;

/**
 * Converts destination to DestinationDTO
 */
public class DestinationConverter {
  public DestinationDTO entityToDTO(Destination destination) {
    DestinationDTO destinationDTO = new DestinationDTO();
    List<ActivityDTO> activityDTOList = new ArrayList<>();
    for(Activity activity: destination.getActivityList()) {
      ActivityDTO activityDTO = new ActivityConverter().entityToDTO(activity);
      activityDTOList.add(activityDTO);
    }
    destinationDTO.setDestinationName(destination.getDestinationName());
    destinationDTO.setActivityList(activityDTOList);
    return destinationDTO;
  }
}
