package tests.UI;

import base.UiBaseTest;
import dataProvider.DataProvider;
import org.testng.Assert;
import org.testng.annotations.Test;
import utlis.Constants;

public class LoginTest extends UiBaseTest {



  @Test
  public void standardUserLogin() {
    driver.get(Constants.BASEURL);
    loginPage.enterCredentialsAndLogin(Constants.STANDARD_USER_USERNAME, Constants.PASSWORD);
    Assert.assertTrue(inventoryPage.getCurrentUrl().contains("/inventory.html"));
  }

  @Test(dataProviderClass = DataProvider.class, dataProvider = "emptyCredentials")
  public void invalidLogin(String username, String password, String errorMessage) {
    driver.get(Constants.BASEURL);
    loginPage.enterCredentialsAndLogin(username, password);
    Assert.assertEquals(loginPage.getErrorMessage(), errorMessage);
  }

  @Test
  public void lockedOutUserLogin() {
    driver.get(Constants.BASEURL);
    loginPage.enterCredentialsAndLogin(Constants.LOCKED_OUT_USER_USERNAME, Constants.PASSWORD);
    Assert.assertEquals(loginPage.getErrorMessage(), Constants.LOCKED_OUT_USER_ERROR);
  }
  @Test
  public void performanceGlitchUserLogin() {
    driver.get(Constants.BASEURL);
    loginPage.enterUsername(Constants.PERFORMANCE_GLITCH_USER_USERNAME);
    loginPage.enterPassword(Constants.PASSWORD);
    long startTime = System.currentTimeMillis();
    loginPage.clickLoginBtn();
    inventoryPage.waitForURL(Constants.BASEURL+"/inventory.html");
    long endTime = System.currentTimeMillis();
    long pageLoadTime = endTime - startTime;
    Assert.assertTrue(pageLoadTime < Constants.MAX_PAGE_LOAD_TIMEOUT, "Page load took too long - " + pageLoadTime + "ms");
  }
}

