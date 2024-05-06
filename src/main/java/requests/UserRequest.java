package requests;

import java.util.Map;

import base.BaseRequest;
import enums.RequestMethod;
import io.restassured.response.Response;
import utlis.Constants;

public class UserRequest extends BaseRequest {

  String url;

  public Response getUser(Map<String, String> params) {
    url = Constants.API_URL + Constants.PATH_USERS;
    return sendRequest(RequestMethod.GET, url, params, null);
  }

  public Response createUser(Map<String, String> data) {
    url = Constants.API_URL + Constants.PATH_USERS;
    return sendRequest(RequestMethod.POST, url, null, data);
  }

}
