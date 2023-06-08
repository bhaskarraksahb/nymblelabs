package com.nymblelabs.travelagency.service.activity;

import com.nymblelabs.travelagency.Entity.activity.Activity;
import com.nymblelabs.travelagency.Entity.destination.Destination;
import com.nymblelabs.travelagency.Entity.travelPackage.TravelPackage;
import com.nymblelabs.travelagency.dto.ActivityDTO;

import java.util.List;

public interface ActivityService {
  List<Activity> findAllActivityByDestination(Integer destinationId);
  Activity findActivityById(Integer activityId);

  Activity saveActivity(Activity theActivity);

  Integer deleteActivityById(Integer activityId);

  Destination getDestinationByActivity(String activityId);
  List <ActivityDTO> getAllActivities();
}
