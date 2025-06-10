package factory;

import java.io.FileReader;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.time.Duration;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

public class BaseClass {

	static WebDriver driver;
	static Properties p;
	static Logger log;
	
	
	public static WebDriver initializeDriver() throws IOException, URISyntaxException {
		
		// Code to initialize the driver, select the os and browser from properties file
		p = getProperties();
		
		String executionEnv = p.getProperty("execution_env");
		String os = p.getProperty("os");
		String browser = p.getProperty("browser");
		
		
		if(executionEnv.equalsIgnoreCase("local")) {
			
			switch(p.getProperty("browser").toLowerCase()) {
				case("chrome"):
						driver = new ChromeDriver();
						break;
				
				case("edge"):
					driver = new EdgeDriver();
					break;
					
				case("firefox"):
					driver = new FirefoxDriver();
					break;
					
				default:
					System.out.println("Invalid browser entry");
					return null;
			}
			
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
			driver.manage().deleteAllCookies();
		}
		
		else if(executionEnv.equalsIgnoreCase("remote")) {
			
			DesiredCapabilities capabilities = new DesiredCapabilities();
			
			switch(os.toLowerCase()) {
			
			case "windows":
				capabilities.setPlatform(Platform.WIN11);
				break;
				
			case "mac":
				capabilities.setPlatform(Platform.MAC);
				break;
				
			case "linux":
				capabilities.setPlatform(Platform.LINUX);
				break;
				
			default:
				System.out.println("Invalid OS detected");
				return null;
			}
			
			switch(browser.toLowerCase()) {
			
			case "chrome":
				capabilities.setBrowserName("chrome");
				break;
				
			case "edge":
				capabilities.setBrowserName("MicrosoftEdge");
				break;
				
			case "firefox":
				capabilities.setBrowserName("firefox");
				break;
				
			default:
				System.out.println("Invalid browser detected");
				return null;
			}
			
			driver = new RemoteWebDriver(new URI("http://192.168.0.241:4444/wd/hub").toURL(),capabilities);
			
		}
		
		return driver;
	}
	
	
	public static WebDriver getDriver() {
		return driver;
	}
	
	public static Properties getProperties() throws IOException {
		p = new Properties();
		FileReader file = new FileReader((System.getProperty("user.dir")+"\\src\\test\\resources\\config.properties"));
		p.load(file);
		return p;
	}
	
	public static Logger getlogger() {
		log = LogManager.getLogger();
		return log;
	}
}
