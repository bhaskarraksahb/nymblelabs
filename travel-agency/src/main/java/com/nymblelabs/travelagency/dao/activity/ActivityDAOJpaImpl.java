package com.nymblelabs.travelagency.dao.activity;

import com.nymblelabs.travelagency.Entity.activity.Activity;
import com.nymblelabs.travelagency.Entity.destination.Destination;
import com.nymblelabs.travelagency.dao.BaseDAO;
import jakarta.persistence.EntityManager;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public class ActivityDAOJpaImpl extends BaseDAO implements ActivityDAO{
  @Autowired
  public ActivityDAOJpaImpl(EntityManager entityManager) {
    super(entityManager);
  }

  @Override
  public List<Activity> findAllActivityByDestination(Integer destinationId) {
    Query theQuery = (Query) getEntityManager().createQuery("from Activity where destination.id=:destinationId");
    theQuery.setParameter("destinationId", destinationId);
    System.out.println("HERE With the result " + theQuery.getResultList());
    return theQuery.getResultList();
  }

  @Override
  public Activity findActivityById(Integer activityId) {
    return getEntityManager().find(Activity.class, activityId);
  }

  @Override
  public Activity saveActivity(Activity theActivity) {
    Activity activity = super.getEntityManager().merge(theActivity);
    theActivity.setId(activity.getId());
    return theActivity;
  }

  @Override
  public Integer deleteActivityById(Integer activityId) {
    Query theQuery = (Query) getEntityManager().createQuery("delete from activity where id=:activityId");
    theQuery.setParameter("activityId", activityId);
    return theQuery.executeUpdate();
  }

  @Override
  public Destination getDestinationByActivity(String activityId) {
    Query theQuery = (Query) getEntityManager().createQuery("from destination d, activity a where a.id=:activityId and a.destination_id=d.id");
    theQuery.setParameter("activityId", activityId);
    return (Destination) theQuery.getSingleResult();
  }

  @Override
  public List<Activity> getAllActivities() {
    Query theQuery= (Query) getEntityManager().createQuery("from Activity");
    return theQuery.getResultList();
  }
}
