package com.nymblelabs.travelagency.service.activity;

import com.nymblelabs.travelagency.Entity.activity.Activity;
import com.nymblelabs.travelagency.Entity.destination.Destination;
import com.nymblelabs.travelagency.converter.ActivityConverter;
import com.nymblelabs.travelagency.dao.activity.ActivityDAO;
import com.nymblelabs.travelagency.dao.activity.ActivityDAOJpaImpl;
import com.nymblelabs.travelagency.dto.ActivityDTO;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
@Transactional
public class ActivityServiceImpl implements ActivityService{

  private ActivityDAOJpaImpl activityDAOJpaImpl;

  @Autowired
  public ActivityServiceImpl(ActivityDAOJpaImpl activityDAOJpaImpl) {
    this.activityDAOJpaImpl = activityDAOJpaImpl;
  }

  @Override
  public List<Activity> findAllActivityByDestination(Integer DestinationId) {
    return this.activityDAOJpaImpl.findAllActivityByDestination(DestinationId);
  }

  @Override
  public Activity findActivityById(Integer activityId) {
    return this.activityDAOJpaImpl.findActivityById(activityId);
  }

  @Override
  public Activity saveActivity(Activity theActivity) {
    return this.activityDAOJpaImpl.saveActivity(theActivity);
  }

  @Override
  public Integer deleteActivityById(Integer activityId) {
    return this.activityDAOJpaImpl.deleteActivityById(activityId);
  }

  @Override
  public Destination getDestinationByActivity(String activityId) {
    return this.activityDAOJpaImpl.getDestinationByActivity(activityId);
  }

  @Override
  public List<ActivityDTO> getAllActivities() {
    List <Activity> activityList = this.activityDAOJpaImpl.getAllActivities();
    List <ActivityDTO> activityDTOList = new ArrayList<>();
    for(Activity activity: activityList) {
      if(activity.getCapacity() > 0) {
        ActivityDTO activityDTO = new ActivityConverter().entityToDTO(activity);
        activityDTO.setOpenSpaces(activity.getCapacity());
        activityDTOList.add(activityDTO);
      }
    }
    return activityDTOList;
  }
}
