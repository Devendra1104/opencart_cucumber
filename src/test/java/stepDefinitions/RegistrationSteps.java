package stepDefinitions;

import java.util.HashMap;
import java.util.Map;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;

import factory.BaseClass;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pageObjects.HomePage;
import pageObjects.RegistrationPage;

public class RegistrationSteps {
	
	HomePage hp;
	RegistrationPage rp;
	
	@Given("User redirects to rgistration page")
	public void user_redirects_to_rgistration_page() {
		hp = new HomePage(BaseClass.getDriver());
		hp.clickMyacc();
		hp.clickRegister();
	}

	@When("enters the below credentials in the fields")
	public void enters_the_below_credentials_in_the_fields(DataTable dataTable) {
		
		rp = new RegistrationPage(BaseClass.getDriver());
		Map<String,String> dataMap = dataTable.asMap(String.class,String.class);
		rp.enterFirstName(dataMap.get("firstname"));
		rp.enterLastName(dataMap.get("lastname"));
		rp.enterEmail(dataMap.get("email"));
		rp.enterTelephone(dataMap.get("telephone"));
		rp.enterPassword(dataMap.get("password"));
		rp.confirmPassword(dataMap.get("password"));
	}

	@When("checks the privacy policy")
	public void checks_the_privacy_policy() {
		rp.checkTerms();
	}

	@When("clicks on continue button")
	public void clicks_on_continue_button() {
		rp.confirmDetails();
	}

	@Then("validate the welcome message on home page")
	public void validate_the_welcome_message_on_home_page() {
	    String confirm_reg_msg= rp.confirmMessage();
	    Assert.assertEquals(confirm_reg_msg,"Your Account Has Been Created!" );
	}

}
