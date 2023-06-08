package com.nymblelabs.travelagency.dao.activity;

import com.nymblelabs.travelagency.Entity.activity.Activity;
import com.nymblelabs.travelagency.Entity.destination.Destination;

import java.util.List;
/**
 * Interface for ActivityDAO
 */
public interface ActivityDAO {
  List<Activity> findAllActivityByDestination(Integer destinationId);
  Activity findActivityById(Integer activityId);

  Activity saveActivity(Activity theActivity);

  Integer deleteActivityById(Integer activityId);

  Destination getDestinationByActivity(String activityId);
  List<Activity> getAllActivities();
}
