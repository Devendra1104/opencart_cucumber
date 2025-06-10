package stepDefinitions;


import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Properties;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import factory.BaseClass;
import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

public class Hooks {
	
	WebDriver driver;
	Properties p;
	
	@Before
	public void startup() throws IOException, URISyntaxException {
		
		driver = BaseClass.initializeDriver();
		p = BaseClass.getProperties();
		
		driver.get(p.getProperty("URL1"));
		driver.manage().window().maximize();
	}
	
	@After
	public void teardown() {
		
		driver.quit();	
	}
	
	//This method will add screenshots to cucumber report on failure of any step.
	@AfterStep
	public void takeScreenshot(Scenario sc) {
		
		if(sc.isFailed()){
			TakesScreenshot ts =(TakesScreenshot) driver;
			byte[] screenshot = ts.getScreenshotAs(OutputType.BYTES);
			sc.attach(screenshot, "image/jpg/png", sc.getName());
		}
	}

}
