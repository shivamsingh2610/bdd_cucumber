package StepDefinitions;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;

public class login {
    WebDriver driver;
    ExtentTest logger;
    public login(Common_step step)
    {
        this.driver = step.getdriver();
        this.logger = step.getlogger();
    }

    @Given("user is on login page")
    public void user_is_on_login_page() {
        driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
    }
    @When("user fill id and passord and click on login button")
    public void user_fill_id_and_passord_and_click_on_login_button() {
        Common_step.borderwithsendkeys("//input[@placeholder='Username']","Admin");
        Common_step.borderwithsendkeys("//input[@placeholder='Password']","admin123");
        Common_step.clickwithborder("//button[@type='submit']");
    }
    @Then("user redirect to welcome page")
    public void user_redirect_to_welcome_page() {
        String expected_title = "OrangeHRM";
        String actual_title = driver.getTitle();
        if (expected_title.equalsIgnoreCase(actual_title))
            logger.log(Status.PASS, "expected title is same as actual title");
        else {
            logger.log(Status.FAIL, "expected title");
        }
    }


}
