package steps;

import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import org.jbehave.core.steps.Steps;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import static org.junit.Assert.*;				

public class JBehaveSteps extends Steps {

	RemoteWebDriver driver;	
	@Given("I open a browser")
	public void openingBrowser() {
		

		if (driver == null) {
			System.setProperty("webdriver.chrome.driver", "C:\\Dan_Dell_Backup\\driveE\\ProdQA\\POC-JBehave\\JBehave\\chromedriver_win32\\chromedriver.exe");
			ChromeOptions options = new ChromeOptions();
			options.addArguments("--disable-extensions");
			driver = new ChromeDriver(options);
			(driver).manage().window().maximize();
		}
	}
	
	@When("I enter $url")
	public void inputURL(String url) {
		( driver).get(url);
	}
	
	@Then("$title is displayed")
	public void searchTitle(String title) {
		assertEquals(title, driver.getTitle());
	}

	
}
