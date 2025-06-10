package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage {
	
	
	//Constructor
	public LoginPage(WebDriver driver){
		
		super(driver);
		
	}
	
	//Locators
	@FindBy(xpath = "//input[@id='input-email']")
	WebElement txtUsername;
	
	@FindBy(xpath = "//input[@id='input-password']")
	WebElement txtPassword;
	
	@FindBy(xpath = "//input[@value='Login']")
	WebElement btnLogin;
	
	//Actions
	public void enterUserName(String uname) {
		txtUsername.sendKeys(uname);
	}
	
	public void enterPassword(String pwd) {
		txtPassword.sendKeys(pwd);
	}
	
	public void clickLogin() {
		btnLogin.click();
	}

}
