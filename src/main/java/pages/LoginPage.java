package pages;

import base.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage extends BasePage {

  @FindBy(id = "user-name")
  private WebElement usernameInput;

  @FindBy(id = "password")
  private WebElement passwordInput;

  @FindBy(id = "login-button")
  private WebElement loginButton;

  @FindBy(xpath = "//h3[@data-test='error']")
  private WebElement errorMessage;

  public LoginPage(WebDriver driver) {
    super(driver);
    PageFactory.initElements(driver, this);
  }

  public void enterUsername(String username) {
    sendKeys(usernameInput, username);
  }

  public void enterPassword(String password) {
    sendKeys(passwordInput, password);
  }

  public void clickLoginBtn() {
    click(loginButton);
  }

  public void enterCredentialsAndLogin(String username, String password) {
    enterUsername(username);
    enterPassword(password);
    clickLoginBtn();
  }

  public String getErrorMessage() {
    return getElementText(errorMessage);
  }

}
