package base;

import java.util.Map;

import enums.RequestMethod;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class BaseRequest {


  protected Response sendRequest(RequestMethod method, String url, Map<String, String> params, Map<String, String> data){
    RequestSpecification request = RestAssured.given().header("Content-Type", "application/json");

    if (params != null && !params.isEmpty()) {
      request.params(params);
    }

    if (data != null && !data.isEmpty()) {
      request.body(data);
    }

    switch (method) {
      case GET:
        return request.get(url);
      case POST:
        return request.post(url);
      default:
        throw new IllegalArgumentException("Invalid method: " + method);
    }
  }

}
