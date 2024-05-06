package base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import pages.InventoryPage;
import pages.LoginPage;
import utlis.Constants;

public class UiBaseTest extends CommonBaseTest{

  protected LoginPage loginPage;
  protected InventoryPage inventoryPage;


  private WebDriver  getBrowser() {
    switch (Constants.BROWSER) {
      case "chrome": driver = new ChromeDriver(); break;
      case "firefox": driver = new FirefoxDriver(); break;
    default : throw new IllegalArgumentException("Invalid browser: " + Constants.BROWSER);
    }
    return driver;
  }

  private void initPages(){
    loginPage = new LoginPage(driver);
    inventoryPage = new InventoryPage(driver);
  }

  @BeforeMethod
  protected void beforeMethodSetup(){
    super.beforeMethodSetup();
    driver = getBrowser();
    driver.manage().window().maximize();
    initPages();
  }

  @AfterMethod
  protected void afterMethod(){
    if(driver != null){
      driver.close();
    }
  }

}
