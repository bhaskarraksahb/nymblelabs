package com.nymblelabs.travelagency.converter;

import com.nymblelabs.travelagency.Entity.activity.Activity;
import com.nymblelabs.travelagency.dto.ActivityDTO;
import com.nymblelabs.travelagency.dto.DestinationDTO;

/**
 * Converts Activity to ActivityDTO
 */
public class ActivityConverter {
  public ActivityDTO entityToDTO(Activity activity) {
    ActivityDTO activityDTO = new ActivityDTO();
    activityDTO.setActivityCost(activity.getActivityCost());
    activityDTO.setActivityName(activity.getActivityName());
    activityDTO.setActivityDescription(activity.getActivityDescription());
    activityDTO.setCapacity(activity.getCapacity());
    //activityDTO.setDestination(new DestinationConverter().entityToDTO(activity.getDestination()));
    activityDTO.setTotalCapacity(activity.getTotalCapacity());
    return activityDTO;
  }
}
