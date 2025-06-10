package stepDefinitions;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.junit.Assert;

import factory.BaseClass;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import utilities.DataReader;

public class LoginSteps {
	
	HomePage hp;
	LoginPage lp;
	MyAccountPage mp;
	List<HashMap<String, String>> datamap; //Data driven
	
	@Given("the user is on the TutorialsNinja login page")
	public void the_user_is_on_the_tutorials_ninja_login_page() {
		hp = new HomePage(BaseClass.getDriver());
		hp.clickMyacc();
		hp.clickLogin();
	}
	
	@When("user enters email as {string} and password as {string}")
	public void user_enters_email_as_and_password_as(String id, String pwd) {
		lp = new LoginPage(BaseClass.getDriver());
		lp.enterUserName(id);
		lp.enterPassword(pwd);
	}

//	@When("the user enters valid credentials \\(username: {string}, password: {string})")
//	public void the_user_enters_valid_credentials_username_password(String id, String pwd) {
//	    
//		lp = new LoginPage(BaseClass.getDriver());
//		lp.enterUserName(id);
//		lp.enterPassword(pwd);
//	}

	@When("the user clicks on the Login button")
	public void the_user_clicks_on_the_login_button() {
		
		lp = new LoginPage(BaseClass.getDriver());
		lp.clickLogin();
	    
	}

	@Then("the user should be redirected to the My Account page")
	public void the_user_should_be_redirected_to_the_my_account_page() {
	   
		mp = new MyAccountPage(BaseClass.getDriver());
		boolean confirm_message = mp.confirmMessage();
		Assert.assertEquals(confirm_message, true);
	}
	
	//*******   Data Driven test **************
	
	@Given("the user navigates to login page")
	public void the_user_navigates_to_login_page() {
		
		hp = new HomePage(BaseClass.getDriver());
		hp.clickMyacc();
		hp.clickLogin();

	}

    @Then("the user should be redirected to the MyAccount Page by passing email and password with excel row {string}")
    public void check_user_navigates_to_my_account_page_by_passing_email_and_password_with_excel_data(String rows)
    {
        try {
			datamap=DataReader.data(System.getProperty("user.dir")+"\\testdata\\Opencart_LoginData.xlsx", "Sheet1");
		} 
        catch (IOException e) 
        {
			e.printStackTrace();
		}

        int index=Integer.parseInt(rows)-1;
        String email= datamap.get(index).get("username");
        String pwd= datamap.get(index).get("password");
        String exp_res= datamap.get(index).get("res");

        lp=new LoginPage(BaseClass.getDriver());
        lp.enterUserName(email);
        lp.enterPassword(pwd);

        lp.clickLogin();
        mp=new MyAccountPage(BaseClass.getDriver());
        try
        {
            boolean targetpage=mp.confirmMessage();
            System.out.println("target page: "+ targetpage);
            if(exp_res.equalsIgnoreCase("Valid"))
            {
                if(targetpage==true)
                {
                    MyAccountPage myaccpage=new MyAccountPage(BaseClass.getDriver());
                    myaccpage.clickLogout();
                    Assert.assertTrue(true);
                }
                else
                {
                    Assert.assertTrue(false);
                }
            }

            if(exp_res.equalsIgnoreCase("Invalid"))
            {
                if(targetpage==true)
                {
                   mp.clickLogout();
                    Assert.assertTrue(false);
                }
                else
                {
                    Assert.assertTrue(true);
                }
            }


        }
        catch(Exception e)
        {

            Assert.assertTrue(false);
        }
      }

}
