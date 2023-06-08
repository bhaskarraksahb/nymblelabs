package com.nymblelabs.travelagency;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nymblelabs.travelagency.Entity.activity.Activity;
import com.nymblelabs.travelagency.Entity.destination.Destination;
import com.nymblelabs.travelagency.Entity.passenger.Passenger;
import com.nymblelabs.travelagency.Entity.passenger.PassengerType;
import com.nymblelabs.travelagency.Entity.travelPackage.TravelPackage;
import com.nymblelabs.travelagency.controller.TravelAgencyController;
import com.nymblelabs.travelagency.dto.ActivityDTO;
import com.nymblelabs.travelagency.dto.PassengerDTO;
import com.nymblelabs.travelagency.dto.TravelPackageDTO;
import com.nymblelabs.travelagency.service.travelpackage.TravelPackageService;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class TravelPackageTest {
  private final String CREATE_TRAVEL_PACKAGE_URL_POST = "/travelPackage/create";
  private final String CREATE_DESTINATION_POST = "/destination/create";
  private final String ADD_DESTINATION_TO_TRAVEL_PACKAGE_POST = "/travelPackage/{travelPackageId}/destination/{destinationId}";
  private final String ADD_ACTIVITY_TO_DESTINATION_POST = "/destinations/{destinationId}/activities/{activityId}";
  private final String ADD_PASSENGER_POST = "/passenger/create";
  private final String ADD_PASSENGER_TO_TRAVEL_PACKAGE_POST = "/travelPackage/{travelPackageId}/passengers/{passengerId}";
  private final String ADD_ACTIVITY_POST = "/activity/create";
  private final String SIGN_UP_PASSENGER_FOR_ACTIVITY_POST = "/passengers/{passengerId}/activities";
  private final String ACTIVITY_BY_DESTINATION_GET = "/activity/destination/{destinationId}";

  private final String TRAVEL_PACKAGE_DETAILS_GET = "/travelPackage/{travelPackageId}/itinerary";

  private final String TRAVEL_PASSENGER_DETAILS_GET = "/travelPackage/{travelPackageId}/passengers";
  private final String PASSENGER_BY_ID_GET = "/passengers/{passengerId}";
  private final String ACTIVITY_OPEN_ALL_GET = "/activity/open";
  @Autowired
  MockMvc mockMvc;
  TravelPackage travelPackage = new TravelPackage("India", 20);
  Destination destination = new Destination("Delhi");
  List <Activity> activityList = new ArrayList<>();
  List <Passenger> passengerList = new ArrayList<>();
  @Test
  public void createTravelPackage() throws Exception{
    travelPackage = new TravelPackage("India", 20);
    ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.post(CREATE_TRAVEL_PACKAGE_URL_POST)
                                                          .content(asJsonString(travelPackage))
                                                          .contentType(MediaType.APPLICATION_JSON)
                                                          .accept(MediaType.APPLICATION_JSON)).andExpect(status().isCreated())
                                                          .andExpect(MockMvcResultMatchers.jsonPath("$.id").exists())
                                                          .andExpect(MockMvcResultMatchers.jsonPath("$.travelPackageName")
                                                          .value(travelPackage.getTravelPackageName()));
  }

  @Test
  public void createTravelPackageAndAddDestination() throws Exception {

    MvcResult travelPackageResponse = mockMvc.perform(MockMvcRequestBuilders.post(CREATE_TRAVEL_PACKAGE_URL_POST)
                                                          .content(asJsonString(travelPackage))
                                                          .contentType(MediaType.APPLICATION_JSON)
                                                          .accept(MediaType.APPLICATION_JSON)).andExpect(status().isCreated())
                                                          .andExpect(MockMvcResultMatchers.jsonPath("$.id").exists())
                                                          .andExpect(MockMvcResultMatchers.jsonPath("$.travelPackageName").value(travelPackage.getTravelPackageName())).andReturn();
    MvcResult destinationResponse = mockMvc.perform(MockMvcRequestBuilders.post(CREATE_DESTINATION_POST)
                                                          .content(asJsonString(destination))
                                                          .contentType(MediaType.APPLICATION_JSON)
                                                          .accept(MediaType.APPLICATION_JSON)).andExpect(status().isCreated())
                                                          .andExpect(MockMvcResultMatchers.jsonPath("$.id").exists())
                                                          .andExpect(MockMvcResultMatchers.jsonPath("$.destinationName").value(destination.getDestinationName())).andReturn();
    travelPackage = new ObjectMapper().readValue(travelPackageResponse.getResponse().getContentAsString(), TravelPackage.class);
    destination = new ObjectMapper().readValue(destinationResponse.getResponse().getContentAsString(), Destination.class);
    mockMvc.perform(MockMvcRequestBuilders.post(ADD_DESTINATION_TO_TRAVEL_PACKAGE_POST,
                                                          String.valueOf(travelPackage.getId()),
                                                          String.valueOf(destination.getId()))
                                                          .contentType(MediaType.APPLICATION_JSON)
                                                          .accept(MediaType.APPLICATION_JSON))
                                                          .andExpect(status().isCreated())
                                                          .andExpect(MockMvcResultMatchers.jsonPath("$.id").exists());
  }

  @Test
  public void createTravelPackageAndDestinationAndAddActivity() throws Exception {
    this.createTravelPackageAndAddDestination();
    for (Integer i = 0; i < 2; i += 1) {
      Activity activity = new Activity("Activity " + i, "Test Activity for " + i, Double.valueOf(10), 15);
      MvcResult activityResponse = mockMvc.perform(MockMvcRequestBuilders.post(ADD_ACTIVITY_POST)
                                                    .content(asJsonString(activity))
                                                    .contentType(MediaType.APPLICATION_JSON)
                                                    .accept(MediaType.APPLICATION_JSON))
                                                    .andExpect(status().isCreated())
                                                    .andExpect(MockMvcResultMatchers.jsonPath("$.id").exists()).andReturn();
      activity = new ObjectMapper().readValue(activityResponse.getResponse().getContentAsString(), Activity.class);
      mockMvc.perform(MockMvcRequestBuilders.post(ADD_ACTIVITY_TO_DESTINATION_POST,
                                                            String.valueOf(destination.getId()),
                                                            String.valueOf(activity.getId()))
                                                            .contentType(MediaType.APPLICATION_JSON)
                                                            .accept(MediaType.APPLICATION_JSON))
                                                            .andExpect(status().isCreated())
                                                            .andExpect(MockMvcResultMatchers.jsonPath("$.id").exists()).andReturn();
      activityList.add(activity);
      System.out.println("HERE ACTIVITY "+ activityResponse.getResponse().getContentAsString());
    }
  }
  @Test
  public void createPassengerAndAddToTravelPackage() throws Exception {
    this.createTravelPackageAndDestinationAndAddActivity();
    for(Integer i = 0; i < 6; i += 1) {
      PassengerType passengerType;
      if(i < 2) {
        passengerType = PassengerType.STANDARD;
      } else if(i < 4) {
        passengerType = PassengerType.GOLD;
      } else {
        passengerType = PassengerType.PREMIUM;
      }
      Passenger passenger = new Passenger(passengerType, "Passenger " + i, String.valueOf(i), Double.valueOf(30));
      MvcResult passengerResponse = mockMvc.perform(MockMvcRequestBuilders.post(ADD_PASSENGER_POST)
                                                            .content(asJsonString(passenger))
                                                            .contentType(MediaType.APPLICATION_JSON)
                                                            .accept(MediaType.APPLICATION_JSON))
                                                            .andExpect(status().isCreated())
                                                            .andExpect(MockMvcResultMatchers.jsonPath("$.id").exists()).andReturn();
      passenger = new ObjectMapper().readValue(passengerResponse.getResponse().getContentAsString(), Passenger.class);
      passengerList.add(passenger);
      passengerResponse = mockMvc.perform(MockMvcRequestBuilders.post(ADD_PASSENGER_TO_TRAVEL_PACKAGE_POST, travelPackage.getId(), passenger.getId())
                                                            .contentType(MediaType.APPLICATION_JSON)
                                                            .accept(MediaType.APPLICATION_JSON))
                                                            .andExpect(status().isCreated())
                                                            .andExpect(MockMvcResultMatchers.jsonPath("$.id").exists()).andReturn();
    }
  }
  @Test
  public void testSignUpActivity() throws Exception {
    this.createPassengerAndAddToTravelPackage();
    for(Passenger passenger: passengerList) {
      for(Activity activity: activityList) {
        Double balanceAfterSignUp = passenger.getPassengerBalance();
        if(passenger.getPassengerType() == PassengerType.STANDARD) {
          balanceAfterSignUp = passenger.getPassengerBalance() - activity.getActivityCost();
        } else if(passenger.getPassengerType() == PassengerType.GOLD) {
          balanceAfterSignUp = passenger.getPassengerBalance() - (activity.getActivityCost() * 0.9);
        }
        mockMvc.perform(MockMvcRequestBuilders.post(SIGN_UP_PASSENGER_FOR_ACTIVITY_POST, passenger.getId())
                                .param("activityId", String.valueOf(activity.getId()))
                                .param("destinationId", String.valueOf(destination.getId()))
                                .param("travelPackageId", String.valueOf(travelPackage.getId()))
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON))
                                .andExpect(status().isCreated())
                                .andExpect(MockMvcResultMatchers.jsonPath("$.passengerBalance").value(balanceAfterSignUp));
        passenger.setPassengerBalance(balanceAfterSignUp);
      }
    }
    MvcResult mockResponse = mockMvc.perform(MockMvcRequestBuilders.get(ACTIVITY_BY_DESTINATION_GET, String.valueOf(destination.getId()))
                              .contentType(MediaType.APPLICATION_JSON)
                              .accept(MediaType.APPLICATION_JSON))
                              .andExpect(status().isOk())
                              .andReturn();
    activityList = new ObjectMapper().readValue(mockResponse.getResponse().getContentAsString(), new TypeReference<List<Activity>>() {});
    Assert.assertEquals(activityList.size(), 2);
    Assert.assertEquals(activityList.get(0).getCapacity(), Integer.valueOf(9));
    Assert.assertEquals(activityList.get(1).getCapacity(), Integer.valueOf(9));
  }

  @Test
  public void testSignUpActivityExhaustForStandard() throws Exception{
    this.testSignUpActivity();
    for(Passenger passenger: passengerList) {
      if(passenger.getPassengerType() == PassengerType.STANDARD) {
        mockMvc.perform(MockMvcRequestBuilders.post(SIGN_UP_PASSENGER_FOR_ACTIVITY_POST, passenger.getId())
                                .param("activityId", String.valueOf(activityList.get(0).getId()))
                                .param("destinationId", String.valueOf(destination.getId()))
                                .param("travelPackageId", String.valueOf(travelPackage.getId()))
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON))
                                .andExpect(status().isCreated())
                                .andExpect(MockMvcResultMatchers.jsonPath("$.passengerBalance").value(0));
        mockMvc.perform(MockMvcRequestBuilders.post(SIGN_UP_PASSENGER_FOR_ACTIVITY_POST, passenger.getId())
                                .param("activityId", String.valueOf(activityList.get(0).getId()))
                                .param("destinationId", String.valueOf(destination.getId()))
                                .param("travelPackageId", String.valueOf(travelPackage.getId()))
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON))
                                .andExpect(status().isBadRequest());
      }
    }
  }
  @Test
  public void testSignUpActivityExhaustForGold() throws Exception {
    this.testSignUpActivity();
    for (Passenger passenger : passengerList) {
      if (passenger.getPassengerType() == PassengerType.GOLD) {
        mockMvc.perform(MockMvcRequestBuilders.post(SIGN_UP_PASSENGER_FOR_ACTIVITY_POST, passenger.getId())
                                .param("activityId", String.valueOf(activityList.get(0).getId()))
                                .param("destinationId", String.valueOf(destination.getId()))
                                .param("travelPackageId", String.valueOf(travelPackage.getId()))
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON))
                                .andExpect(status().isCreated())
                                .andExpect(MockMvcResultMatchers.jsonPath("$.passengerBalance").value(3));
        mockMvc.perform(MockMvcRequestBuilders.post(SIGN_UP_PASSENGER_FOR_ACTIVITY_POST, passenger.getId())
                                .param("activityId", String.valueOf(activityList.get(0).getId()))
                                .param("destinationId", String.valueOf(destination.getId()))
                                .param("travelPackageId", String.valueOf(travelPackage.getId()))
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON))
                                .andExpect(status().isBadRequest());
      }
    }
  }
  @Test
  public void testSignUpActivityExhaustForPremium() throws Exception {
    this.testSignUpActivity();
    for (Passenger passenger : passengerList) {
      if (passenger.getPassengerType() == PassengerType.PREMIUM) {
        for(Integer i = 0; i < 4; i += 1) {
          mockMvc.perform(MockMvcRequestBuilders.post(SIGN_UP_PASSENGER_FOR_ACTIVITY_POST, passenger.getId())
                                  .param("activityId", String.valueOf(activityList.get(0).getId()))
                                  .param("destinationId", String.valueOf(destination.getId()))
                                  .param("travelPackageId", String.valueOf(travelPackage.getId()))
                                  .contentType(MediaType.APPLICATION_JSON)
                                  .accept(MediaType.APPLICATION_JSON))
                                  .andExpect(status().isCreated())
                                  .andExpect(MockMvcResultMatchers.jsonPath("$.passengerBalance").value(30));
        }
      }
    }
    MvcResult mockResponse = mockMvc.perform(MockMvcRequestBuilders.get(ACTIVITY_BY_DESTINATION_GET, String.valueOf(destination.getId()))
                                                     .contentType(MediaType.APPLICATION_JSON)
                                                     .accept(MediaType.APPLICATION_JSON))
                                     .andExpect(status().isOk())
                                     .andReturn();
    activityList = new ObjectMapper().readValue(mockResponse.getResponse().getContentAsString(), new TypeReference<List<Activity>>() {});
    Assert.assertTrue(activityList.get(0).getCapacity() == 1);
    Assert.assertTrue(activityList.get(1).getCapacity() == 9);
  }

  @Test
  public void testPrintTravelPackageDetails() throws Exception {
    this.testSignUpActivity();
    MvcResult mockResponse = mockMvc.perform(MockMvcRequestBuilders.get(TRAVEL_PACKAGE_DETAILS_GET, travelPackage.getId())
                            .contentType(MediaType.APPLICATION_JSON)
                            .accept(MediaType.APPLICATION_JSON)).andReturn();
    TravelPackageDTO travelPackageDTO = new ObjectMapper().readValue(mockResponse.getResponse().getContentAsString(), TravelPackageDTO.class);
    Assert.assertTrue(travelPackageDTO.getTravelPackageName().equals("India"));
    Assert.assertTrue(travelPackageDTO.getTravelPackageDestinations().size() == 1);
    Assert.assertTrue(travelPackageDTO.getTravelPackageDestinations().get(0).getDestinationName().equals("Delhi"));
    Assert.assertTrue(travelPackageDTO.getTravelPackageDestinations().get(0).getActivityList().size() == 2);
  }

  @Test
  public void testPrintTravelPackagePassengers() throws Exception {
    this.testSignUpActivity();
    MvcResult mockResponse = mockMvc.perform(MockMvcRequestBuilders.get(TRAVEL_PASSENGER_DETAILS_GET, travelPackage.getId())
                                                     .contentType(MediaType.APPLICATION_JSON)
                                                     .accept(MediaType.APPLICATION_JSON)).andReturn();
    TravelPackageDTO travelPackageDTO = new ObjectMapper().readValue(mockResponse.getResponse().getContentAsString(), TravelPackageDTO.class);
    Assert.assertTrue(travelPackageDTO.getTravelPackageName().equals("India"));
    Assert.assertTrue(travelPackageDTO.getTravelPackagePassengerCapacity() == 14);
    Assert.assertTrue(travelPackageDTO.getTotalCapacity() == 20);
    Assert.assertTrue(travelPackageDTO.getTravelPackagePassenger().size() == 6);
  }

  @Test
  public void testPassengerDetails() throws Exception {
    this.testSignUpActivity();
    for (Passenger passenger : passengerList) {
      MvcResult mockResponse = mockMvc.perform(MockMvcRequestBuilders.get(PASSENGER_BY_ID_GET, passenger.getId())
                                                       .contentType(MediaType.APPLICATION_JSON)
                                                       .accept(MediaType.APPLICATION_JSON)).andReturn();
      PassengerDTO passengerDTO = new ObjectMapper().readValue(mockResponse.getResponse().getContentAsString(), PassengerDTO.class);
      Assert.assertTrue(passengerDTO.getPassengerName().equals(passenger.getPassengerName()));
      Assert.assertTrue(passengerDTO.getPassengerNumber().equals(passenger.getPassengerNumber()));
      Assert.assertTrue(passengerDTO.getDestinationDTOList().size() == 1);
      Assert.assertTrue(passengerDTO.getDestinationDTOList().get(0).getActivityList().size() == 2);
    }
  }
  @Test
  public void testAllOpenActivities() throws Exception {
    this.testSignUpActivity();
    MvcResult mockResponse = mockMvc.perform(MockMvcRequestBuilders.get(ACTIVITY_OPEN_ALL_GET)
                                                     .contentType(MediaType.APPLICATION_JSON)
                                                     .accept(MediaType.APPLICATION_JSON)).andReturn();
    List <ActivityDTO> activityDTOList = new ObjectMapper().readValue(mockResponse.getResponse().getContentAsString(), new TypeReference<List<ActivityDTO>>() {});
    Assert.assertTrue(activityDTOList.size() > 0);
  }
  private static String asJsonString(final Object obj) {
    try {
      return new ObjectMapper().writeValueAsString(obj);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }
}
