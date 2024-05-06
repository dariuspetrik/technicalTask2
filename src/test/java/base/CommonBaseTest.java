package base;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeMethod;
import org.testng.asserts.SoftAssert;
import requests.UserRequest;

public class CommonBaseTest {

  protected WebDriver driver;
  protected SoftAssert softAssert;
  protected UserRequest userRequest;

  public void initializeTestComponents(){
    softAssert = new SoftAssert();
    userRequest = new UserRequest();
  }

  @BeforeMethod
  protected void beforeMethodSetup(){
    initializeTestComponents();
  }


}
