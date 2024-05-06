package tests.API;

import java.util.List;
import java.util.Map;

import base.ApiBaseTest;
import dataProvider.DataProvider;
import io.restassured.response.Response;
import org.testng.annotations.Test;

public class ApiTest extends ApiBaseTest {

  @Test(dataProviderClass = DataProvider.class,dataProvider = "userPage")
  public void getUsersList(String paramName,String paramValue){
    //create map with params
    Map<String, String> params = new java.util.HashMap<>();
    params.put(paramName,paramValue);
    //send request with params
    Response response =userRequest.getUser(params);

    System.out.println(response.getBody().prettyPrint());
    //get values from json
    int total = response.jsonPath().get("total");
    String firstUserLasName = response.jsonPath().get("data[0].last_name");
    String secondUserLasName = response.jsonPath().get("data[1].last_name");
    int numberOfUsers = response.jsonPath().getList("data").size();
    //assertions on values
    softAssert.assertEquals(firstUserLasName,"Lawson","Wrong first user last name ");
    softAssert.assertEquals(secondUserLasName,"Ferguson","Wrong second user last name ");
    softAssert.assertEquals(total,numberOfUsers,"Total number is not the same as number of users, " + total + " vs " + numberOfUsers);
    softAssert.assertTrue(response.jsonPath().get("total") instanceof Integer, "total is not an Integer");
    softAssert.assertTrue(response.jsonPath().get("data") instanceof List, "data is not a List");
    softAssert.assertTrue(response.jsonPath().get("data[0]") instanceof Map, "first element of data is not Object");
    softAssert.assertTrue(response.jsonPath().get("data[0].last_name") instanceof String, "last_name is not an String");
    softAssert.assertAll();
  }

  @Test(dataProviderClass = DataProvider.class,dataProvider = "user")
  public void createUser(String name, String job){
    long limit = 100;
    //create map with data
    Map<String, String> data = new java.util.HashMap<>();
    data.put("name",name);
    data.put("job",job);
    //send request with data
    long startTime = System.currentTimeMillis();
    Response response =userRequest.createUser(data);
    long endTime = System.currentTimeMillis();
    //map api response to json
    //assertions on values
    softAssert.assertEquals(response.statusCode(),201);
    softAssert.assertFalse(response.jsonPath().getString("id").isEmpty(),  "Id is empty");
    softAssert.assertNotNull(response.jsonPath().get("createdAt"));
    softAssert.assertTrue((endTime - startTime) < limit, "Request loading time is higher than limit " + limit + "ms");
    softAssert.assertAll();
  }


}
