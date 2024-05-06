package base;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePage {

  protected WebDriver driver;
  protected WebDriverWait wait;

  public BasePage(WebDriver driver) {
    this.driver = driver;
    this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
  }

  public void click(WebElement element) {
      wait.until(ExpectedConditions.elementToBeClickable(element)).click();
  }

  public void sendKeys(WebElement element, String text) {
    element.clear();
    element.sendKeys(text);
  }

  public String getElementText(WebElement element) {
    return element.getText();
  }

  public String getCurrentUrl() {
    return driver.getCurrentUrl();
  }

  public void waitForURLContains(String url) {
    wait.until(ExpectedConditions.urlContains(url));
  }

  public void waitForURL(String url) {
    wait.until(ExpectedConditions.urlToBe(url));
  }


}
